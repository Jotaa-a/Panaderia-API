package com.jotaa.panaderia_api.controller;

import com.jotaa.panaderia_api.dto.request.CategoriaRequest;
import com.jotaa.panaderia_api.dto.response.CategoriaResponse;
import com.jotaa.panaderia_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping("/{id}")
    public CategoriaResponse obtener (
            @PathVariable Integer id
    ) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(
            @Valid
            @RequestBody
            CategoriaRequest dto
    ) {
        CategoriaResponse creada = service.crear(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(creada.id()).toUri();
        return ResponseEntity.created(location).body(creada);
    }

    @PutMapping("/{id}")
    public CategoriaResponse actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaRequest dto
    ) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(
            @PathVariable Integer id
    ) {
        service.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
