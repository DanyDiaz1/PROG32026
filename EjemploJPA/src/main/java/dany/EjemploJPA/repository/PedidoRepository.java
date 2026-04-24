package dany.EjemploJPA.repository;

import dany.EjemploJPA.model.Pedido;
import dany.EjemploJPA.model.enums.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // DERIVADAS
    List<Pedido> findByEstado(EstadoPedido estado);

    List<Pedido> findByTotalGreaterThan(Double total);

    List<Pedido> findByClienteNombreContaining(String nombre);

    // JPQL
    @Query("SELECT p FROM Pedido p WHERE p.cliente.nombre = :nombre")
    List<Pedido> buscarPorNombreCliente(@Param("nombre") String nombre);

    // JOIN
    @Query("SELECT DISTINCT p FROM Pedido p JOIN p.productos pr WHERE pr.nombre LIKE %:nombre%")
    List<Pedido> buscarPorProducto(@Param("nombre") String nombre);

    // NATIVE
    @Query(value = "SELECT * FROM pedido ORDER BY total DESC LIMIT :limite", nativeQuery = true)
    List<Pedido> topPedidos(@Param("limite") int limite);

    // UPDATE
    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.estado = :estado WHERE p.id = :id")
    int actualizarEstado(@Param("id") Long id, @Param("estado") EstadoPedido estado);

    // PAGINACIÓN
    Page<Pedido> findAll(Pageable pageable);
}
