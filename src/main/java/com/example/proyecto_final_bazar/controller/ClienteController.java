package com.example.proyecto_final_bazar.controller;

import com.example.proyecto_final_bazar.model.Cliente;
import com.example.proyecto_final_bazar.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    IClienteService cliServ;
    
    @PostMapping("/clientes/crear")
    public String altaCliente(@RequestBody Cliente cli){
        cliServ.saveCliente(cli);
        return "El cliente fue creado correctamente";
    }
    
    @GetMapping("/clientes")
    public List<Cliente> getCliente(){
        return cliServ.getCliente();
    }
    
    @GetMapping("/clientes/{id_cliente}")
    public Cliente getClienteById(@PathVariable Long id_cliente){
        return cliServ.getClienteById(id_cliente);
    }
    
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String borrarCliente(@PathVariable Long id_cliente){
        cliServ.deleteCliente(id_cliente);
        return "El cliente fue borrado correctamente";
    }
    
    //PUT
    @PutMapping("/clientes/editar/{id_cliente}")
    public String findCliente(@PathVariable Long id_cliente,
                              @RequestParam (required=false, name="nombre") String nombre,
                              @RequestParam (required=false, name="apellido") String apellido,
                              @RequestParam (required=false, name="dni") String dni)
                              {
                            cliServ.editCliente(id_cliente, nombre, apellido, dni);
                            return "El cliente fue actualizado correctamente";
    }
    
}
