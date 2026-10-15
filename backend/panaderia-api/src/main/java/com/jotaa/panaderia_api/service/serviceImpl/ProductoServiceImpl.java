package com.jotaa.panaderia_api.service.serviceImpl;

import com.jotaa.panaderia_api.dto.request.ProductoRequest;
import com.jotaa.panaderia_api.dto.response.ProductoResponse;
import com.jotaa.panaderia_api.exception.RecursoNoEncontradoExcepcion;
import com.jotaa.panaderia_api.modelo.Categoria;
import com.jotaa.panaderia_api.modelo.Producto;
import com.jotaa.panaderia_api.repository.CategoriaRepository;
import com.jotaa.panaderia_api.repository.ProductoRepository;
import com.jotaa.panaderia_api.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> listar(Integer categoriaId) {
        List<Producto> productos = (categoriaId == null)
                ? productoRepository.listarActivos()
                : productoRepository.listarActivosPorCategoria(categoriaId);
        return productos.stream().map(ProductoResponse::desde).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse obtener(Integer id) {
        return ProductoResponse.desde(buscar(id));
    }

    @Override
    @Transactional
    public ProductoResponse crear(ProductoRequest dto) {
        Producto p = new Producto();
        aplicar(p, dto);
        return ProductoResponse.desde(productoRepository.save(p));
    }

    @Override
    @Transactional
    public ProductoResponse actualizar(Integer id, ProductoRequest dto) {
        Producto p = buscar(id);
        aplicar(p, dto);
        return ProductoResponse.desde(p);
    }

    @Override
    public void desactivar(Integer id) {
        buscar(id).setActivo(false);
    }

    @Override
    public void aplicar(Producto p, ProductoRequest dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .filter(Categoria::getActivo)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "Categoria no encontrada o inactiva: " + dto.categoriaId())
                );
        p.setNombre(dto.nombre().trim());
        p.setDescripcion(dto.descripcion());
        p.setCategoria(categoria);
        p.setPrecio(dto.precio());
        p.setStock(dto.stock());
        p.setImagenUrl(dto.imagenUrl());
        p.setUnidad(dto.unidad());
    }

    @Override
    public Producto buscar(Integer id) {
        return productoRepository.buscarConCategoria(id).orElseThrow(() ->
                new RecursoNoEncontradoExcepcion("Producto no encontrado: " + id));
    }
}
