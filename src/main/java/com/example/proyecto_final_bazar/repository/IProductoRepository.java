package com.example.proyecto_final_bazar.repository;

import com.example.proyecto_final_bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long>{
    
    @Query("SELECT v FROM Producto v WHERE v.costo = (SELECT MAX(v2.costo) FROM Producto v2)")
    Producto findProductoConMayorMonto();
    
}
