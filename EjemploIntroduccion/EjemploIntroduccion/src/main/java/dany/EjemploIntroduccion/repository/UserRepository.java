package dany.EjemploIntroduccion.repository;

import dany.EjemploIntroduccion.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Repository marca esta clase como componente de acceso a datos
@Repository
public class UserRepository {

    // Lista que simula una base de datos en memoria
    private final List<User> users = new ArrayList<>(List.of(
            new User(1L, "Ana",    "García",    "ana@mail.com",    "ADMIN"),
            new User(2L, "Carlos", "López",     "carlos@mail.com", "USER"),
            new User(3L, "Lucía",  "Martínez", "lucia@mail.com",  "USER"),
            new User(4L, "Pedro",  "Sánchez",  "pedro@mail.com",  "MODERATOR"),
            new User(5L, "Sofía",  "Fernández","sofia@mail.com",  "USER")
    ));
    private long nextId = 6L;

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public User save(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public Optional<User> update(Long id, User updated) {
        return findById(id).map(u -> {
            u.setFirstName(updated.getFirstName());
            u.setLastName(updated.getLastName());
            u.setEmail(updated.getEmail());
            u.setRole(updated.getRole());
            return u;
        });
    }

    public boolean deleteById(Long id) {
        return users.removeIf(u -> u.getId().equals(id));
    }
}
