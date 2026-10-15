package com.jotaa.panaderia_api.service;

import com.jotaa.panaderia_api.dto.request.ProductoRequest;
import com.jotaa.panaderia_api.dto.response.ProductoResponse;
import com.jotaa.panaderia_api.modelo.Producto;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> listar(Integer categoriaId);
    ProductoResponse obtener(Integer id);
    ProductoResponse crear(ProductoRequest dto);
    ProductoResponse actualizar(Integer id, ProductoRequest dto);
    void desactivar(Integer id);
    void aplicar(Producto p, ProductoRequest dto);
    Producto buscar(Integer id);
}
