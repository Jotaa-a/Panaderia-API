package com.jotaa.panaderia_api.service.serviceImpl;

import com.jotaa.panaderia_api.dto.request.CategoriaRequest;
import com.jotaa.panaderia_api.dto.response.CategoriaResponse;
import com.jotaa.panaderia_api.exception.*;
import com.jotaa.panaderia_api.modelo.Categoria;
import com.jotaa.panaderia_api.repository.*;
import com.jotaa.panaderia_api.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;


    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponse> listar() {
        return categoriaRepository.findByActivoTrueOrderByNombre()
                .stream().map(CategoriaResponse::desde).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponse obtener(Integer id) {
        return CategoriaResponse.desde(buscar(id));
    }

    @Override
    @Transactional
    public CategoriaResponse crear(CategoriaRequest dto) {
        if (categoriaRepository.existsByNombreIgnoreCase(dto.nombre())){
            throw new ReglaNegocioExcepcion("Ya existe una categoria con ese nombre");
        }
        Categoria c = new Categoria();
        c.setNombre(dto.nombre().trim());
        return CategoriaResponse.desde(categoriaRepository.save(c));
    }

    @Override
    @Transactional
    public CategoriaResponse actualizar(Integer id, CategoriaRequest dto) {
        Categoria c = buscar(id);
        if (categoriaRepository.existsByNombreIgnoreCaseAndIdNot(dto.nombre(), id)) {
            throw new ReglaNegocioExcepcion("Ya existe una categoria con ese nombre");
        }
        c.setNombre(dto.nombre().trim());
        return CategoriaResponse.desde(c);
    }

    @Override
    @Transactional
    public void desactivar(Integer id) {
        Categoria c = buscar(id);
        if (productoRepository.existsByCategoriaIdAndActivoTrue(id)) {
            throw new ReglaNegocioExcepcion(
                    "No se puede desactivar: tiene productos activos"
            );
        }
        c.setActivo(false);
    }

    @Override
    public Categoria buscar(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                    new RecursoNoEncontradoExcepcion(
                            "Categoría no encontrada: " + id
                    )
                );
    }
}
