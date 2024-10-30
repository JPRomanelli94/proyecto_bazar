package com.example.proyecto_final_bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MayorVentaDto {
    
    private Long codigo_venta;
    private Double total_venta;
    private Double cant_prod;
    private String nombre_cliente;
    private String apellido_cliente;

    public MayorVentaDto() {
    }

    public MayorVentaDto(Long codigo_venta, Double total_venta, Double cant_prod, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total_venta = total_venta;
        this.cant_prod = cant_prod;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
    
}
