package com.eldar.logistica.delivery.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Tag(name = "Correo Argentino", description = "API para Ver estado de envios de Correo Argentino")
@RequestMapping("/api/CorreoArg")
public class CorreoArgController {

    @GetMapping("/test")
    public String test() {
        return "CorreoArgController works!";
    }

}
