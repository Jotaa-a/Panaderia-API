package com.jotaa.panaderia_api.service;

import com.jotaa.panaderia_api.dto.request.CategoriaRequest;
import com.jotaa.panaderia_api.dto.response.CategoriaResponse;
import com.jotaa.panaderia_api.modelo.Categoria;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> listar();
    CategoriaResponse obtener(Integer id);
    CategoriaResponse crear(CategoriaRequest dto);
    CategoriaResponse actualizar(Integer id, CategoriaRequest dto);
    void desactivar(Integer id);
    Categoria buscar(Integer id);
}
