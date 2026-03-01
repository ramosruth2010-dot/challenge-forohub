package com.example.forohub.demo.controller;

import com.example.forohub.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestParam String usuario,
                        @RequestParam String password){

        // Simulación login (challenge simplificado)
        if(usuario.equals("admin") && password.equals("123")){
            return tokenService.generarToken(usuario);
        }

        throw new RuntimeException("Credenciales invalidas");
    }
}
