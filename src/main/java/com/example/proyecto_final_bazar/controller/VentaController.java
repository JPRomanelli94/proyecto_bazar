package com.example.proyecto_final_bazar.controller;

import com.example.proyecto_final_bazar.model.Cliente;
import com.example.proyecto_final_bazar.model.Producto;
import com.example.proyecto_final_bazar.model.Venta;
import com.example.proyecto_final_bazar.service.IVentaService;
import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    IVentaService venServ;
    
    @PostMapping("/ventas/crear")
    public String altaVenta(@RequestBody Venta ven){
        String flag = venServ.altaVenta(ven);
        
        if (flag.equals("Ok")){
            return "La venta fue realizada correctamente";
        }
        else{
            return "La venta no pudo realizarse, revise el stock disponible";
        }
        
    }
    
    @GetMapping("/ventas")
    public List<Venta> getVentas(){
        return venServ.getVenta();
    }
    
    @GetMapping("/ventas/{id_venta}")
    public Venta getVentaById(@PathVariable Long id_venta){
        return venServ.getVentaById(id_venta);
    }
    
    @GetMapping("/ventas/fecha/{fecha_venta}")
    public String getVentaByFecha(@PathVariable LocalDate fecha_venta){
        System.out.println("llama metodo de service");
        return venServ.getDatosByFecha(fecha_venta);
    }
    
    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String deleteVentas(@PathVariable Long codigo_venta){
        venServ.deleteVenta(codigo_venta);
        
        return "La venta fue eliminada correctamente";
    }
    
     //PUT
    @PutMapping("/ventas/editar/{codigo_venta}")
    public String findProduct(@PathVariable Long codigo_venta, 
                              @RequestParam (required=false, name="codigo_nuevo")Long idNuevo,
                              @RequestParam (required=false, name="fecha") LocalDate fechaVenta,
                              @RequestParam (required=false, name="total") Double total,
                              @RequestParam (required=false, name="prod") List<Producto> listadoProductos,
                              @RequestParam (required=false, name="cli") Cliente unCliente){
        
        venServ.editVenta(codigo_venta, fechaVenta, total, listadoProductos, unCliente);
        
        return "La venta fue actualizada correctamente";
    }
   
    
}
