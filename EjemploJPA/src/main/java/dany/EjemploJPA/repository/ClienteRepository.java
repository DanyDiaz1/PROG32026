package dany.EjemploJPA.repository;

import dany.EjemploJPA.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // derivada
    List<Cliente> findByNombreContaining(String nombre);

    // JPQL
    @Query("SELECT c FROM Cliente c WHERE c.direccion.ciudad = :ciudad")
    List<Cliente> buscarPorCiudad(@Param("ciudad") String ciudad);

}
