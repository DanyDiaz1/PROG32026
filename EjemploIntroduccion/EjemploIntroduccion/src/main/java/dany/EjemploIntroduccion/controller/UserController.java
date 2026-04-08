package dany.EjemploIntroduccion.controller;

import dany.EjemploIntroduccion.model.User;
import dany.EjemploIntroduccion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController = @Controller + @ResponseBody
// Indica que esta clase maneja peticiones HTTP y devuelve JSON
@RestController
// Define la ruta base para todos los endpoints de este controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET /api/users  →  devuelve todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // GET /api/users/1  →  devuelve un usuario o 404
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/users  →  crea un usuario nuevo
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/users/1  →  actualiza un usuario o 404
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @RequestBody User user) {
        Optional<User> updated = userService.update(id, user);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/users/1  →  elimina un usuario o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
