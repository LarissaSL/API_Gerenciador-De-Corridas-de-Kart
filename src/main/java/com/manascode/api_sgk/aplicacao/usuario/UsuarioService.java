package com.manascode.api_sgk.aplicacao.usuario;

import com.manascode.api_sgk.aplicacao.usuario.validacoes.IValidadorDeUsuario;
import com.manascode.api_sgk.aplicacao.usuario.validacoes.ValidadorCpf;
import com.manascode.api_sgk.dominio.usuario.TipoUsuario;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.UsuarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    @Autowired
    List<IValidadorDeUsuario> validadores;

    public ResponseEntity cadastrar(CriarUsuarioDTO dados) {
        // Passando os dados enviados para os validadores
        validadores.forEach(v -> v.validar(dados));

        Usuario usuario = UsuarioMapper.INSTANCE.converteDTOParaUsuario(dados);
        Usuario usuarioSalvo = repositorio.save(usuario);

        DetalharUsuarioDTO usuarioDetalhado = UsuarioMapper.INSTANCE.converteUsuarioParaDTODetalhamento(usuarioSalvo);

        var uri = UriComponentsBuilder
                .fromPath("/usuario/{id}")
                .buildAndExpand(usuarioSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioDetalhado);
    }

    public ResponseEntity<DetalharUsuarioDTO> detalhar(Long id) {
        Usuario usuarioSalvo = repositorio.findById(id).orElseThrow(() -> new UsuarioException("Usuário não encontrado"));
        DetalharUsuarioDTO usuarioDetalhado = UsuarioMapper.INSTANCE.converteUsuarioParaDTODetalhamento(usuarioSalvo);

        return ResponseEntity.ok(usuarioDetalhado);
    }

    public ResponseEntity<Void> excluir(Long id) {
        Usuario usuario = repositorio.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.excluir();
        repositorio.save(usuario);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<ListarUsuarioDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Usuario> page = repositorio.findAllByAtivoTrue(paginacao);
        Page<ListarUsuarioDTO> listaDeUsuariosDTO = page.map(UsuarioMapper.INSTANCE::converteUsuarioParaDTOListar);

        return ResponseEntity.ok(listaDeUsuariosDTO);
    }

    public ResponseEntity<?> atualizar(AtualizarUsuarioDTO dados) {
        return ResponseEntity.ok().build();
    }
}
