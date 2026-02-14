package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detallefactura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idDetalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "idFactura", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Productos producto;

    private Integer cantidad;
    private double precioUnitario;
    private double subtotal;
    public Integer getIdDetalle() {
        return idDetalle;
    }
    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }
    public Factura getFactura() {
        return factura;
    }
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public Productos getProducto() {
        return producto;
    }
    public void setProducto(Productos producto) {
        this.producto = producto;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    
}
