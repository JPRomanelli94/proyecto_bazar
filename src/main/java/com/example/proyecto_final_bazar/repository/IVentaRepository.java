package com.example.proyecto_final_bazar.repository;

import com.example.proyecto_final_bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
    
    
}
