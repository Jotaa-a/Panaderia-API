package com.jotaa.panaderia_api.repository;

import com.jotaa.panaderia_api.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p join fetch p.categoria where p.activo = true order by p.nombre")
    List<Producto> listarActivos();

    @Query("select p from Producto p join fetch p.categoria where p.activo = true and p.categoria.id = :categoriaId order by p.nombre")
    List<Producto> listarActivosPorCategoria(@Param("categoriaId") Integer categoriaId);

    @Query("select p from Producto p join fetch p.categoria where p.id = :id")
    Optional<Producto> buscarConCategoria(@Param("id") Integer id);

    boolean existsByCategoriaIdAndActivoTrue(Integer categoriaId);
}