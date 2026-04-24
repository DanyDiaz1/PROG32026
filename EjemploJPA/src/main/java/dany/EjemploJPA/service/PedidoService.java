package dany.EjemploJPA.service;

import dany.EjemploJPA.model.Cliente;
import dany.EjemploJPA.model.Pedido;
import dany.EjemploJPA.model.Producto;
import dany.EjemploJPA.model.enums.EstadoPedido;
import dany.EjemploJPA.repository.ClienteRepository;
import dany.EjemploJPA.repository.PedidoRepository;
import dany.EjemploJPA.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;

    @Transactional
    public Pedido crear(Long clienteId, List<Long> productosIds, Pedido pedido) {

        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow();

        Set<Producto> productos = new HashSet<>(productoRepo.findAllById(productosIds));

        pedido.setCliente(cliente);
        pedido.setProductos(productos);

        return pedidoRepo.save(pedido);
    }

    @Transactional(readOnly = true)
    public Page<Pedido> listar(Pageable pageable) {
        return pedidoRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Pedido> buscarPorCliente(String nombre) {
        return pedidoRepo.buscarPorNombreCliente(nombre);
    }

    @Transactional
    public void cambiarEstado(Long id, EstadoPedido estado) {
        pedidoRepo.actualizarEstado(id, estado);
    }
}
