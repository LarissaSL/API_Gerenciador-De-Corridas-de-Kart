package com.manascode.api_sgk.aplicacao.logintemporario;


import com.manascode.api_sgk.aplicacao.logintemporario.validacoes.IValidadorLoginTemporario;
import com.manascode.api_sgk.dominio.logintemporario.LoginTemporario;
import com.manascode.api_sgk.dominio.usuario.Usuario;
import com.manascode.api_sgk.infraestrutura.excecao.aplicacao.LoginTemporarioException;
import com.manascode.api_sgk.infraestrutura.persistencia.LoginTemporarioRepository;
import com.manascode.api_sgk.infraestrutura.persistencia.UsuarioRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
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
    private EnviarEmailService enviarEmailService;

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

    public ResponseEntity criarLoginTemporario(SolicitarLoginTemporarioDTO loginDTO) {
        validadores.forEach(v -> v.validar(loginDTO));

        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.email());
        if (usuario == null) {
            throw new LoginTemporarioException("Usuário não encontrado.");
        }

        // Gera código temporário
        String codigoTemporario = gerarCodigoUnico();

        // Cria o login temporário no banco
        LoginTemporario loginTemporario = new LoginTemporario();
        loginTemporario.setUsuario(usuario);
        loginTemporario.setCodigoTemporario(codigoTemporario);
        loginTemporarioRepository.save(loginTemporario);

        // Envia o e-mail com o código temporário
        try {
            String destinatario = usuario.getEmail();
            String assunto = "Seu Código Temporário";
            String mensagem = "Seu código temporário é: " + codigoTemporario;
            enviarEmailService.enviarCodigoEmail(destinatario, assunto, mensagem);

            return ResponseEntity.ok().build();

        } catch (MailAuthenticationException e) {
            throw new LoginTemporarioException("Erro nas credenciais de autentificação do Gmail, cheque elas por favor.");

        } catch (Exception e) {
            throw new LoginTemporarioException("Erro no envio de E-mail: " + e.getMessage());
        }
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
