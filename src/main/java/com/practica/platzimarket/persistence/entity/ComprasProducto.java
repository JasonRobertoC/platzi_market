package com.practica.platzimarket.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compras_productos")
public class ComprasProducto {
    @EmbeddedId
    private ComprasProductoPK id;
    private Integer cantidad;
    private Double total;
    private Boolean estado;

//    Relacion con la entidad Compra
    @ManyToOne
//    name tendra el valor del nombre de la FK
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
//    Relacion con la entidad Producto
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
}
