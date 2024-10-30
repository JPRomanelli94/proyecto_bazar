package com.example.proyecto_final_bazar.service;

import com.example.proyecto_final_bazar.controller.ProductoController;
import com.example.proyecto_final_bazar.dto.MayorVentaDto;
import com.example.proyecto_final_bazar.model.Cliente;
import com.example.proyecto_final_bazar.model.Producto;
import com.example.proyecto_final_bazar.model.Venta;
import com.example.proyecto_final_bazar.repository.IProductoRepository;
import com.example.proyecto_final_bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    IVentaRepository venRep;
    
    @Autowired
    IProductoRepository prodRepo;
    
    @Autowired
    ProductoController prodCont;
    
    @Override
    public String altaVenta(Venta venta) {
        System.out.println("arranca el alta");
        String flag = "Ok";
        Double cantidadRestante = 0.0;
        Long id_prod = 0L;
        Venta id_venta = null;
        List<Producto> productosExistentes = prodRepo.findAll();
        
        for (Producto prod2: venta.getListaProductos()){
            for (Producto prod: productosExistentes){
                System.out.println("entra al for");
                if (prod.getCodigo_producto().equals(prod2.getCodigo_producto())){
                    System.out.println("entra al primer if");
                    if (prod.getCantidad_disponible() < venta.getTotal()){
                        flag = "nOk";
                    }
                    else{
                        System.out.println("entra al else");
                        id_prod = prod.getCodigo_producto();
                        cantidadRestante = (prod.getCantidad_disponible() - venta.getTotal());
                        prod.setCantidad_disponible(cantidadRestante);
                        
                         if (flag.equals("Ok")){
                            venRep.save(venta);
                            System.out.println(venta.getCodigo_venta());
                            id_venta = this.getVentaById(venta.getCodigo_venta());
                            System.out.println("id_venta: " + id_venta);
                            System.out.println(id_prod);
                            prodCont.updateCantidad(id_prod, cantidadRestante,id_venta);
                            }
                    }
                }
                
        }
        }
        
        
        return flag;
    }

    @Override
    public List<Venta> getVenta() {
        return venRep.findAll();
    }
    
    @Override
    public Venta getVentaById(Long id_venta){
        return venRep.findById(id_venta).orElse(null);
    }

    @Override
    public void deleteVenta(Long id_venta) {
        venRep.deleteById(id_venta);
    }

    @Override
    public void editVenta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        
        Venta ventaExistente = this.getVentaById(codigo_venta);
        
        ventaExistente.setFecha_venta(fecha_venta);
        ventaExistente.setTotal(total);
        ventaExistente.setListaProductos(listaProductos);
        ventaExistente.setUnCliente(unCliente);
        
        venRep.save(ventaExistente);
    }

    @Override
    public String getDatosByFecha(LocalDate fecha_venta) {
        Double montoTotal = 0.0;
        Double cantidadTotal = 0.0;
        String resultados;
        
        List<Venta> ventasExistentes = this.getVenta();
        List<Producto> productosExistentes = prodCont.findProduct();
        
        for (Venta ven: ventasExistentes){
            if (ven.getFecha_venta().equals(fecha_venta)){
                cantidadTotal+= ven.getTotal();
                for (Producto prod: productosExistentes){
                    if (prod.getId_venta().getCodigo_venta().equals(ven.getCodigo_venta())){
                        montoTotal += prod.getCosto();
                }
            }
            }
            
        }
        
        resultados = "El día " + fecha_venta + ", se vendió " + cantidadTotal + " producto/s, con un monto total de " + montoTotal + " pesos.";
        return resultados;
    }

    
}
