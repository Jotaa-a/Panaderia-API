package com.jotaa.panaderia_api.dto.response;

import com.jotaa.panaderia_api.modelo.Categoria;

public record CategoriaResponse(
        Integer id,
        String nombre,
        Boolean activo
) {
    public static CategoriaResponse desde(Categoria c){
        return new CategoriaResponse(
                c.getId(), c.getNombre(), c.getActivo()
        );
    }
}
