package com.example.proyecto_final_bazar.service;

import com.example.proyecto_final_bazar.dto.MayorVentaDto;
import com.example.proyecto_final_bazar.model.Cliente;
import com.example.proyecto_final_bazar.model.Producto;
import com.example.proyecto_final_bazar.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {
    
    //POST
    public String altaVenta(Venta venta);
    
    //GET
    public List<Venta> getVenta();
    
    //GET BY ID
    public Venta getVentaById(Long id_venta);
    
    //GET BY DATE
    public String getDatosByFecha(LocalDate fecha_venta);
    
    //DELETE
    public void deleteVenta(Long id_venta);
    
    //PUT
    public void editVenta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente);
    
}
