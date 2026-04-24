package dany.EjemploJPA.controller;

import dany.EjemploJPA.model.Pedido;
import dany.EjemploJPA.model.enums.EstadoPedido;
import dany.EjemploJPA.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public Pedido crear(
            @RequestParam Long clienteId,
            @RequestParam List<Long> productos,
            @RequestBody Pedido pedido) {

        return service.crear(clienteId, productos, pedido);
    }

    @GetMapping
    public Page<Pedido> listar(
            @RequestParam int page,
            @RequestParam int size) {

        return service.listar(PageRequest.of(page, size));
    }

    @GetMapping("/buscar")
    public List<Pedido> buscar(@RequestParam String nombre) {
        return service.buscarPorCliente(nombre);
    }

    @PutMapping("/{id}/estado")
    public void cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoPedido estado) {

        service.cambiarEstado(id, estado);
    }
}
