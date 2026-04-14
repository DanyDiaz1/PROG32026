package dany.Exceptions.repository;

import dany.Exceptions.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<User> usuarios = new ArrayList<>();

    public UserRepository() {
        // DATOS PRECARGADOS
        usuarios.add(new User(1L, "Matias", "mati@mail.com"));
        usuarios.add(new User(2L, "Juan", "juan@mail.com"));
    }

    public List<User> findAll() {
        return usuarios;
    }

    public Optional<User> findById(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public User save(User usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public void deleteById(Long id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }

    public User update(User usuario) {
        deleteById(usuario.getId());
        usuarios.add(usuario);
        return usuario;
    }
}
