package com.jotaa.panaderia_api.repository;

import com.jotaa.panaderia_api.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("select p from Producto p join fetch p.categoria where p.activo = true order by p.nombre")
    List<Producto> listarActivos();

    @Query("select p from Producto p join fetch pcategoria where p.activo = true and p.categoria.id = :categoriaId order by p.nombre")
    List<Producto> listarActivosPorCategoria(Integer categoriaId);

    @Query("select p from Produco p join fetch p.categoria where p.id = :id")
    java.util.Optional<Producto> buscarConCategoria(Integer categoriaId);

    boolean existsByCategoriaIdAndActivoTrue(Integer categoriaId);
}
