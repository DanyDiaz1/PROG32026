package dany.EjemploIntroduccion.service;

import dany.EjemploIntroduccion.model.User;
import dany.EjemploIntroduccion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service marca esta clase como componente de lógica de negocio
@Service
// @RequiredArgsConstructor inyecta automáticamente UserRepository
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> update(Long id, User user) {
        return userRepository.update(id, user);
    }

    public boolean delete(Long id) {
        return userRepository.deleteById(id);
    }
}
