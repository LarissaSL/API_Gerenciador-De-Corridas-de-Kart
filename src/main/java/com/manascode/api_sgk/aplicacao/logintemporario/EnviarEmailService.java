package com.manascode.api_sgk.aplicacao.logintemporario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCodigoEmail(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(assunto);
        email.setText(mensagem);
        mailSender.send(email);
    }
}
