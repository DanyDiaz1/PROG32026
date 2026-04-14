package dany.Exceptions.service;

import dany.Exceptions.exception.UserNotFoundException;
import dany.Exceptions.model.User;
import dany.Exceptions.dto.UserDTO;
import dany.Exceptions.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> listar() {
        return repository.findAll();
    }

    public User buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));
    }

    public User crear(UserDTO dto) {
        User usuario = new User(
                new Random().nextLong(),
                dto.getNombre(),
                dto.getEmail()
        );
        return repository.save(usuario);
    }

    public User actualizar(UserDTO dto) {

        User existente = repository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + dto.getId()));

        existente.setNombre(dto.getNombre());
        existente.setEmail(dto.getEmail());

        return repository.update(existente);
    }

    public void eliminar(Long id) {

        User existente = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));

        repository.deleteById(id);
    }
}
