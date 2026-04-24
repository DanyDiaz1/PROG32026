package dany.EjemploJPA.service;

import dany.EjemploJPA.model.Cliente;
import dany.EjemploJPA.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepo;

    @Transactional
    public Cliente crear(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente obtener(Long id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepo.findByNombreContaining(nombre);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorCiudad(String ciudad) {
        return clienteRepo.buscarPorCiudad(ciudad);
    }

    @Transactional
    public Cliente actualizar(Long id, Cliente nuevo) {
        Cliente existente = obtener(id);
        existente.setNombre(nuevo.getNombre());
        existente.setDireccion(nuevo.getDireccion());

        return clienteRepo.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!clienteRepo.existsById(id))
            throw new RuntimeException("Cliente no encontrado");

        clienteRepo.deleteById(id);
    }
}
