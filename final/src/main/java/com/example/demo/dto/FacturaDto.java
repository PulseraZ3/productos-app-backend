package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class FacturaDto {
    private Integer idFactura;
    private LocalDate fecha;
    private double total;
    private String cliente;
    private String metodoPago;
    private List<DetalleFacturaDto> detalles;
    
    public Integer getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public List<DetalleFacturaDto> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetalleFacturaDto> detalles) {
        this.detalles = detalles;
    }
    
}
