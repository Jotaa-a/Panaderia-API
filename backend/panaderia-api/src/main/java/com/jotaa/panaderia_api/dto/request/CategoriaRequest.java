package com.jotaa.panaderia_api.dto.request;

import jakarta.validation.constraints.*;

public record CategoriaRequest(
        @NotBlank(message = "El nombre de la categoria es obligatorio")
        @Size(max = 100, message = "Maximo 100 caracteres")
        String nombre
) {
}
