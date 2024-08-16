package com.eldar.logistica.providers.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Tag(name = "Providers", description = "API para administrar proveedores")
@RequestMapping("/api/providers")
public class ProvidersController {

}
