package dany.EjemploJPA.repository;

import dany.EjemploJPA.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContaining(String nombre);

    List<Producto> findByPrecioLessThan(Double precio);

    List<Producto> findByPrecioBetween(Double min, Double max);

}
