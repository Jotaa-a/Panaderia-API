package com.jotaa.panaderia_api.controller;

import com.jotaa.panaderia_api.dto.request.ProductoRequest;
import com.jotaa.panaderia_api.dto.response.ProductoResponse;
import com.jotaa.panaderia_api.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<ProductoResponse> listar(
            @RequestParam(
                    required = false
            ) Integer categoriaId
    ) {
        return service.listar(categoriaId);
    }

    @GetMapping("/{id}")
    public ProductoResponse obtener(
            @PathVariable Integer id
    ) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(
            @Valid @RequestBody ProductoRequest dto
    ) {
        ProductoResponse creado = service.crear(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(creado.id()).toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/{id}")
    public ProductoResponse actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoRequest dto
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
