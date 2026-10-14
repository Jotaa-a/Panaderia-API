package com.jotaa.panaderia_api.dto.response;

import com.jotaa.panaderia_api.modelo.Categoria;
import com.jotaa.panaderia_api.modelo.Producto;

import java.math.BigDecimal;

public record ProductoResponse(
        Integer id,
        String nombre,
        String descripcion,
        Categoria categoria,
        BigDecimal precio,
        Integer stock,
        String imagenUrl,
        String unidad,
        Boolean activo
) {
    public static ProductoResponse desde(Producto p){
        return new ProductoResponse(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getCategoria(),
                p.getPrecio(),
                p.getStock(),
                p.getImagenUrl(),
                p.getUnidad(),
                p.getActivo()
        );
    }
}
