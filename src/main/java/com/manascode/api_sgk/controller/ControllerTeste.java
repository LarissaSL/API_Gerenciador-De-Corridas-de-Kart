package com.manascode.api_sgk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class ControllerTeste {

    @GetMapping
    public String teste() {
        return "Tudo certo!";
    }
}
