package com.manascode.api_sgk.aplicacao.logintemporario;


import com.manascode.api_sgk.aplicacao.logintemporario.validacoes.IValidadorLoginTemporario;
import com.manascode.api_sgk.dominio.logintemporario.LoginTemporario;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.LoginTemporarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.LoginTemporarioRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginTemporarioService {

    @Autowired
    private LoginTemporarioRepository loginTemporarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<IValidadorLoginTemporario> validadores;

    private final SecureRandom random = new SecureRandom();

    // Função para gerar código único de 5 dígitos
    private String gerarCodigoUnico() {
        String codigo;
        do {
            // Gera um número aleatório de 5 dígitos
            codigo = String.format("%05d", random.nextInt(100000));
        } while (loginTemporarioRepository.existsByCodigoTemporario(codigo));
        return codigo;
    }

    public void criarLoginTemporario(SolicitarLoginTemporarioDTO loginDTO) {
        validadores.forEach(v -> v.validar(loginDTO));

        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.email());
        if (usuario == null) {
            throw new LoginTemporarioException("Usuário não encontrado.");
        }

        // Cria o login temporário
        LoginTemporario loginTemporario = new LoginTemporario();
        loginTemporario.setUsuario(usuario);
        loginTemporario.setCodigoTemporario(gerarCodigoUnico()); // Gera código temporário
        loginTemporarioRepository.save(loginTemporario);
    }

    public void marcarComoUtilizado(Long id) {
        LoginTemporario loginTemporario = loginTemporarioRepository.findById(id)
                .orElseThrow(() -> new LoginTemporarioException("Login temporário não encontrado"));

        if (loginTemporario.getUtilizado()) {
            throw new LoginTemporarioException("Login temporário já foi utilizado.");
        }

        loginTemporario.setUtilizado(true);
        loginTemporario.setDataUtilizado(LocalDateTime.now());
        loginTemporarioRepository.save(loginTemporario);
    }
}
