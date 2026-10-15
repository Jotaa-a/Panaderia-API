package com.jotaa.panaderia_api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequest(
        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 100) String nombre,

        @Size(max = 255) String descripcion,

        @NotNull(message = "La categoria es obligatoria")
        Integer categoriaId,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
        @Digits(integer = 8, fraction = 2) BigDecimal precio,

        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock,

        @Size(max = 255) String imagenUrl,

        @Size(max = 20) String unidad
        ) {
}
