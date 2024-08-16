package com.eldar.logistica.terminals.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Tag(name ="Terminal", description = "API para administrar terminales")
@RequestMapping("/api/terminals")
public class TerminalController {



}
