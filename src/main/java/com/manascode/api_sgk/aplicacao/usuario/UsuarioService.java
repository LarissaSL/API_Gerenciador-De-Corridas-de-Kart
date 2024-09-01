package com.manascode.api_sgk.aplicacao.usuario;

import com.manascode.api_sgk.aplicacao.usuario.validacoes.IValidadorDeUsuario;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.UsuarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import com.manascode.api_sgk.interfaceAdaptadores.mapper.UsuarioMapper;
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
    private UsuarioMapper usuarioMapper;

    @Autowired
    List<IValidadorDeUsuario> validadores;

    public ResponseEntity<DetalharUsuarioDTO> cadastrar(CriarUsuarioDTO dados) {
        // Passando os dados enviados para os validadores
        validadores.forEach(v -> v.validar(dados));

        Usuario usuario =usuarioMapper.converteDTOParaUsuario(dados);
        Usuario usuarioSalvo = repositorio.save(usuario);

        DetalharUsuarioDTO usuarioDetalhado = usuarioMapper.converteUsuarioParaDTODetalhamento(usuarioSalvo);

        var uri = UriComponentsBuilder
                .fromPath("/usuario/{id}")
                .buildAndExpand(usuarioSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioDetalhado);
    }

    public ResponseEntity<DetalharUsuarioDTO> detalhar(Long id) {
        Usuario usuarioSalvo = repositorio.findById(id).orElseThrow(() -> new UsuarioException("Usuário não encontrado"));
        DetalharUsuarioDTO usuarioDetalhado = usuarioMapper.converteUsuarioParaDTODetalhamento(usuarioSalvo);

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
        Page<ListarUsuarioDTO> listaDeUsuariosDTO = page.map(usuarioMapper::converteUsuarioParaListarUsuarioDTO);

        return ResponseEntity.ok(listaDeUsuariosDTO);
    }

    public ResponseEntity<DetalharUsuarioDTO> atualizar(AtualizarUsuarioDTO dados) {
        Usuario usuarioSalvo = repositorio.findById(dados.id()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        validadores.forEach(v -> v.validar(dados));

        usuarioSalvo.atualizar(dados);

        DetalharUsuarioDTO usuarioDetalhado = usuarioMapper.converteUsuarioParaDTODetalhamento(usuarioSalvo);

        return ResponseEntity.ok(usuarioDetalhado);
    }
}
