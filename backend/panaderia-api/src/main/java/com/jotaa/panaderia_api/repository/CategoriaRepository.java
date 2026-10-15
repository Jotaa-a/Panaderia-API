package com.jotaa.panaderia_api.repository;

import com.jotaa.panaderia_api.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository {
    List<Categoria> findByActivoTrueOrderByNombre();
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
}
