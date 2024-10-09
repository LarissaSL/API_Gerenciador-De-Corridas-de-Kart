package com.manascode.api_sgk.aplicacao.sorteador;

import com.manascode.api_sgk.aplicacao.sorteador.validacoes.DetalharInformacaoGeralDoSorteioDTO;
import com.manascode.api_sgk.aplicacao.sorteador.validacoes.IValidadorNumerosDoSorteio;
import com.manascode.api_sgk.aplicacao.sorteador.validacoes.IValidadorSorteio;
import com.manascode.api_sgk.aplicacao.usuario.DetalharNomeESobrenomeUsuarioProjecao;
import com.manascode.api_sgk.dominio.check.Check;
import com.manascode.api_sgk.dominio.corrida.Corrida;
import com.manascode.api_sgk.dominio.inscricao.Inscricao;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.SorteioException;
import com.manascode.api_sgk.infraestrutura.persistencia.CheckRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.InscricaoRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.SorteadorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SorteadorService {

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private SorteadorMapper mapper;

    @Autowired
    private List<IValidadorSorteio> validadoresDoSorteio;

    @Autowired
    private List<IValidadorNumerosDoSorteio> validadoresNumerosDoSorteio;

    public ResponseEntity<DetalharInformacaoGeralDoSorteioDTO> realizarSorteioDeNumerosDeKart(ConfiguracoesDoSorteioDTO configuracoesDoSorteioDTO) {
        // Validar se está tudo certo para fazer o Sorteio para aquela corrida
        validadoresDoSorteio.forEach(v -> v.validar(configuracoesDoSorteioDTO.idCorrida()));

        // Obtendo os usuarios com Check-in para o sorteio
        List<DetalharNomeESobrenomeUsuarioProjecao> usuarios = inscricaoRepository.listaDeUsuarioPorCorridaECheckInFeitoOrdenadoPorDataInscricao(configuracoesDoSorteioDTO.idCorrida());

        // Calculando o número de karts disponíveis
        int totalNumerosDisponiveis = configuracoesDoSorteioDTO.maiorNumeroDeKart() - configuracoesDoSorteioDTO.numerosForaDoSorteio().size();

        // Verificar se terá números suficientes para todos os usuários
        if (totalNumerosDisponiveis < usuarios.size()) {
            int totalUsuarios = usuarios.size();
            int totalNumerosRetirados = configuracoesDoSorteioDTO.numerosForaDoSorteio().size();
            int maiorNumeroKart = configuracoesDoSorteioDTO.maiorNumeroDeKart();
            throw new SorteioException(
                    String.format("Não é possível fazer o Sorteio, pois não tem números suficientes para todos os " +
                            "usuários(%d). Verifique a quantidade de números retirados(%d) e se o maior " +
                            "número de Kart(%d) está correto.", totalUsuarios, totalNumerosRetirados, maiorNumeroKart));
        }


        Random sorteador = new Random();
        List<Integer> listaNumerosSorteados = new ArrayList<>();
        List<DetalharInformacaoPilotoDoSorteioDTO> listaDeInformacoesDoSorteio = new ArrayList<>();

        // Realiza o sorteio para cada usuário
        for (DetalharNomeESobrenomeUsuarioProjecao usuario : usuarios) {
            Integer numeroSorteado = null;

            // Continua sorteando até que um número válido seja encontrado
            while (numeroSorteado == null) {
                int numeroAleatorioSorteado = sorteador.nextInt(configuracoesDoSorteioDTO.maiorNumeroDeKart()) + 1;

                // Verifica os validadores de números do sorteio
                boolean numeroValido = validadoresNumerosDoSorteio.stream()
                        .noneMatch(v -> v.validar(numeroAleatorioSorteado, configuracoesDoSorteioDTO.numerosForaDoSorteio(), listaNumerosSorteados));

                // Se o número é válido, checa Inscricao, encontra o Check-in e Atribui o número
                if (numeroValido) {
                    // Encontra a Inscrição do Usuario
                    Inscricao inscricao = inscricaoRepository.findByUsuarioIdAndCorridaId(usuario.getId(), configuracoesDoSorteioDTO.idCorrida());

                    if (inscricao == null) {
                        throw new SorteioException("Inscrição não encontrada para o usuário: " + usuario.getNome() + " " + usuario.getSobrenome());
                    }

                    // Encontra Check-in do Usuario
                    Check check = checkRepository.encontrarPorInscricao(inscricao.getId());
                    if (check == null) {
                        throw new SorteioException("Check-in não encontrado para a inscrição de ID: " + inscricao.getId());
                    }

                    // Inserir o número de kart, inserir ele na lista e parar o Loop de sorteio para o usuário da vez
                    check.inserirNumeroDeKart(numeroAleatorioSorteado);
                    listaNumerosSorteados.add(numeroAleatorioSorteado);
                    numeroSorteado = numeroAleatorioSorteado;
                    checkRepository.save(check);

                    // Adiciona o usuário sorteado à lista
                    DetalharInformacaoPilotoDoSorteioDTO informacaoDoSorteioDoUsuario = mapper.converteInscricaoENumeroDoKartEmDetalharInformacaoDoSorteioDto(inscricao, numeroSorteado);
                    listaDeInformacoesDoSorteio.add(informacaoDoSorteioDoUsuario);
                }
            }
        }

        // Separando as informações da Conclusão do Sorteio
        // Criando a lista de números restantes
        List<Integer> listaNumerosRestantes = criarListaDeNumerosRestantes(
                configuracoesDoSorteioDTO.maiorNumeroDeKart(),
                listaNumerosSorteados,
                configuracoesDoSorteioDTO.numerosForaDoSorteio());


        // Mapeia as informações do sorteio em um DTO
        DetalharInformacaoGeralDoSorteioDTO resultadoSorteio = mapper.criarDetalharInformacaoGeralDoSorteioDtoComDetalharInformacaoDoPilotoSorteioNumeroDeKartListaSorteados(
                listaDeInformacoesDoSorteio.size(), listaNumerosRestantes, listaDeInformacoesDoSorteio, configuracoesDoSorteioDTO.numerosForaDoSorteio());

        return ResponseEntity.ok(resultadoSorteio);
    }

    public List<Integer> criarListaDeNumerosRestantes(int maiorNumeroDeKart, List<Integer> numerosSorteados, List<Integer> numerosForaDoSorteio) {
        List<Integer> listaNumerosRestantes = new ArrayList<>();

        // Percorre de 1 até o maior número de kart
        for (int i = 1; i <= maiorNumeroDeKart; i++) {
            // Adiciona o número à lista se não estiver entre os números sorteados e não estiver na lista de números fora do sorteio
            if (!numerosSorteados.contains(i) && !numerosForaDoSorteio.contains(i)) {
                listaNumerosRestantes.add(i);
            }
        }

        return listaNumerosRestantes;
    }

    public ResponseEntity<Page<ListarUsuariosParaSorteioDTO>> listarUsuariosParaSorteio(Pageable paginacao, Long idCorrida) {
        Page<DetalharNomeESobrenomeUsuarioProjecao> page = inscricaoRepository.listaDeUsuarioPorCorridaECheckInFeitoParaSorteio(idCorrida, paginacao);

        // Mapear a página de usuários para uma página de DTOs
        Page<ListarUsuariosParaSorteioDTO> listarUsuariosParaSorteio = page.map(mapper::converteParaListarUsuariosParaSorteioDTO);

        return ResponseEntity.ok(listarUsuariosParaSorteio);
    }

    public ResponseEntity excluir(Long idCorrida) {
        boolean existeCheckInPorCorrida = checkRepository.existsCheckInsByCorridaId(idCorrida);

        if (!existeCheckInPorCorrida) {
            throw new SorteioException("Nenhum check-in encontrado para essa Corrida.");
        }

        int registrosAfetados = checkRepository.colocarNumeroDeKartComoNulo(idCorrida);

        if (registrosAfetados <= 0) {
            throw new SorteioException("Nenhum registro excluido.");
        }

        return ResponseEntity.noContent().build();
    }
}
