package com.example.proyecto_final_bazar.repository;

import com.example.proyecto_final_bazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente,Long>{
    
}
