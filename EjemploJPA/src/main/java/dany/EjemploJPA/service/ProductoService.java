package dany.EjemploJPA.service;

import dany.EjemploJPA.model.Producto;
import dany.EjemploJPA.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepo;

    @Transactional
    public Producto crear(Producto producto) {
        return productoRepo.save(producto);
    }

    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return productoRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Producto obtener(Long id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepo.findByNombreContaining(nombre);
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarBaratos(Double precio) {
        return productoRepo.findByPrecioLessThan(precio);
    }

    @Transactional
    public Producto actualizar(Long id, Producto nuevo) {
        Producto existente = obtener(id);

        existente.setNombre(nuevo.getNombre());
        existente.setPrecio(nuevo.getPrecio());

        return productoRepo.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepo.existsById(id))
            throw new RuntimeException("Producto no encontrado");

        productoRepo.deleteById(id);
    }
}
