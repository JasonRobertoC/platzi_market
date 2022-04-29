package com.practica.platzimarket.persistence.crud;

import com.practica.platzimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//CrudRepository<Entidad, tipo dato PK>
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
//    //Forma nativa, y el metodo puede tener otro nombre
//    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
//    List<Producto> getProductosByCategoria(int idCategoria);

    //Con Query Methods
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    //List<Producto> findByIdCategoria(int idCategoria);
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado );

}
