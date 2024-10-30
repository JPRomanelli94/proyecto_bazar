package com.example.proyecto_final_bazar.service;

import com.example.proyecto_final_bazar.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    //Post
    public void saveCliente(Cliente cli);
    
    //Get
    public List<Cliente> getCliente();
    
    //GetById
    public Cliente getClienteById(Long id);
    
    //Delete
    public void deleteCliente(Long id);
    
    //Edit
    public void editCliente(Long id_cliente, String nombre, String apellido, String dni);
    
}
