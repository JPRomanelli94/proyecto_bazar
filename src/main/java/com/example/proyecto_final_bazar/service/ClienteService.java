package com.example.proyecto_final_bazar.service;

import com.example.proyecto_final_bazar.model.Cliente;
import com.example.proyecto_final_bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {
    
    @Autowired
    IClienteRepository cliRepo;
    
    @Override
    public void saveCliente(Cliente cli) {
        cliRepo.save(cli);
    }

    @Override
    public List<Cliente> getCliente() {
        return cliRepo.findAll();
    }

    @Override
    public void deleteCliente(Long id) {
        cliRepo.deleteById(id);
    }
    
    
    @Override
    public void editCliente(Long id_cliente, String nombre, String apellido, String dni) {
        
        Cliente cliExistente = this.getClienteById(id_cliente);
        
        cliExistente.setNombre(nombre);
        cliExistente.setApellido(apellido);
        cliExistente.setDni(dni);
        
        this.saveCliente(cliExistente);
    }

    @Override
    public Cliente getClienteById(Long id) {
        return cliRepo.findById(id).orElse(null);
    }
    
}
