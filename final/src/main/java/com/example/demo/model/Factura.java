package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "factura")
@Getter
@Setter
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private Integer idFactura;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total")
    private Double total;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles = new ArrayList<>();

    public void agregarDetalle(DetalleFactura d) {
        detalles.add(d);
        d.setFactura(this);
    }
}