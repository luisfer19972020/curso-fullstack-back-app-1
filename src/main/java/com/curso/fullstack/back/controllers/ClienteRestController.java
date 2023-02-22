package com.curso.fullstack.back.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
    @GetMapping("/nombre")
    public Map<String,String> get(){
        final Map<String,String> response = new HashMap<>();
        response.put("Nombre","Luis");
        response.put("Apellido","Correa");
        return response;
    }
}
