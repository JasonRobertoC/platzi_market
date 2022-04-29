package com.practica.platzimarket.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    private String id;
    private String nombre;
    private String apellidos;
    private Long celular;
    private String direccion;
    @Column(name = "correo_electronico")
    private String correoElectronico;

//    mappedBy, mapea la variable a relacionar con el atributo
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
}
