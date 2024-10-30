package com.example.proyecto_final_bazar.controller;

import com.example.proyecto_final_bazar.dto.MayorVentaDto;
import com.example.proyecto_final_bazar.model.Producto;
import com.example.proyecto_final_bazar.model.Venta;
import com.example.proyecto_final_bazar.service.IProductoService;
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
public class ProductoController {
    
    @Autowired
    private IProductoService prodServ;
    
    //POST
    @PostMapping("/productos/crear")
    public String createProduct(@RequestBody Producto produc){
        prodServ.saveProduct(produc);
        return "El producto fue creado correctamente";
    }
    
    //GET
    @GetMapping("/productos")
    public List<Producto> findProduct(){
        return prodServ.getProduct();
    }
    
    //GET
    @GetMapping("/productos/falta_stock")
    public List<Producto> findProductLessFive(){
        return prodServ.getProductoCant();
    }
    
    //GET
    @GetMapping("ventas/productos/{codigo_venta}")
    public List<Producto> findProductoVendido(@PathVariable Long codigo_venta){
        return prodServ.getProductoVendido(codigo_venta);
    }
    
    //GET
    @GetMapping("ventas/dto/mayor_venta")
    public MayorVentaDto findMayorVenta(){
        return prodServ.getMayorVenta();
    }
    
    
    //PUT
    @PutMapping("/productos/editar/{codigo_producto}")
    public String findProduct(@PathVariable Long codigo_producto, 
                              @RequestParam (required=false, name="codigo_nuevo")Long idNuevo,
                              @RequestParam (required=false, name="cantidad_disponible") Double cant,
                              @RequestParam (required=false, name="costo") Double costo,
                              @RequestParam (required=false, name="marca") String marca,
                              @RequestParam (required=false, name="nombre") String nombre){
        
        prodServ.saveNewProduct(codigo_producto, cant, costo, marca, nombre);
        
        return "El producto fue actualizado correctamente";
    }
    
    //PUT - Actualizo cantidad cuando vendo
     @PutMapping("/productos/editarCantidad/{codigo_producto}")
    public void updateCantidad(@PathVariable Long codigo_producto,
                              @RequestParam (required=false, name="cantidad_disponible") Double cant,
                              @RequestParam (required=false, name="codigo_venta") Venta id_venta)
                        {
        
        prodServ.saveCantidadRestante(codigo_producto, cant, id_venta);
       
    }
    
 
    //DELETE
    @DeleteMapping("/producto/eliminar/{codigo_producto}")
    public String deleteProduct(@PathVariable Long codigo_producto){
        prodServ.deleteProduct(codigo_producto);
        return "El producto fue eliminado correctamente";
    }
    
    
    
    
}
