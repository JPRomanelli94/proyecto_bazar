package com.example.proyecto_final_bazar.service;

import com.example.proyecto_final_bazar.dto.MayorVentaDto;
import com.example.proyecto_final_bazar.model.Producto;
import com.example.proyecto_final_bazar.model.Venta;
import java.util.List;

public interface IProductoService {
    
    //Post
    public void saveProduct(Producto produc);
    
    //Get
    public List<Producto> getProduct();
    
    //Get individual
    public Producto getProductoById(Long idBuscado);
    
    //Get producto con cantidad menor a 5
    public List<Producto> getProductoCant();
    
    //Get producto de un determinado id de venta
    public List<Producto> getProductoVendido(Long id_venta);
    
    //Get producto de mayor monto de venta
    public MayorVentaDto getMayorVenta();
    
    //Delete
    public void deleteProduct(Long id);
    
    //Put
    public void saveNewProduct(Long codigo_producto, Double cant, Double costo, String marca, String nombre);
    
    //Put - Actualizo cantidad cuando vendo
    public void saveCantidadRestante(Long codigo_producto, Double cant, Venta id_venta);
}
