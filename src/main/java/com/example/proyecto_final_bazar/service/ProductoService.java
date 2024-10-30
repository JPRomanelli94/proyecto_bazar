package com.example.proyecto_final_bazar.service;

import com.example.proyecto_final_bazar.dto.MayorVentaDto;
import com.example.proyecto_final_bazar.model.Producto;
import com.example.proyecto_final_bazar.model.Venta;
import com.example.proyecto_final_bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public void saveProduct(Producto produc) {
        prodRepo.save(produc);
    }

    @Override
    public List<Producto> getProduct() {
        return prodRepo.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        prodRepo.deleteById(id);
    }

    
    @Override
    public void saveNewProduct(Long codigo_producto, Double cant, Double costo, String marca, String nombre) {
        
        Producto prodExistente = this.getProductoById(codigo_producto);
        
        prodExistente.setCantidad_disponible(cant);
        prodExistente.setCosto(costo);
        prodExistente.setMarca(marca);
        prodExistente.setNombre(nombre);
        
        this.saveProduct(prodExistente);
     
    }

    @Override
    public Producto getProductoById(Long idBuscado) {
        return prodRepo.findById(idBuscado).orElse(null);
    }

    @Override
    public void saveCantidadRestante(Long codigo_producto, Double cant, Venta id_venta) {
        Producto prodExistente = this.getProductoById(codigo_producto);
        
        prodExistente.setCantidad_disponible(cant);
        prodExistente.setId_venta(id_venta);
        
        this.saveProduct(prodExistente);
    }

    @Override
    public List<Producto> getProductoCant() {
        List<Producto> todosProductos = this.getProduct();
        List<Producto> productosAMostrar = new ArrayList<>();
        for (Producto prod: todosProductos){
            if (prod.getCantidad_disponible()<5){
                productosAMostrar.add(prod);
            }
        }
        
        return productosAMostrar;
    }

    @Override
    public List<Producto> getProductoVendido(Long id_venta) {
        List<Producto> todosProductos2 = this.getProduct();
        System.out.println(todosProductos2);
        List<Producto> productosAMostrar2 = new ArrayList<>();
        for (Producto prod: todosProductos2){
            System.out.println("entra al for");
            if (prod.getId_venta().getCodigo_venta().equals(id_venta)){
                System.out.println("entra al if");
                productosAMostrar2.add(prod);
            }
        }
        
        return productosAMostrar2;
    }
    
    @Override
    public MayorVentaDto getMayorVenta() {
        Producto mayorProd = prodRepo.findProductoConMayorMonto();
        
        MayorVentaDto newDto= new MayorVentaDto();
        newDto.setCodigo_venta(mayorProd.getId_venta().getCodigo_venta());
        newDto.setTotal_venta(mayorProd.getId_venta().getTotal());
        newDto.setCant_prod(mayorProd.getCantidad_disponible());
        newDto.setNombre_cliente(mayorProd.getId_venta().getUnCliente().getNombre());
        newDto.setApellido_cliente(mayorProd.getId_venta().getUnCliente().getApellido());
        
        return newDto;
    }
}
