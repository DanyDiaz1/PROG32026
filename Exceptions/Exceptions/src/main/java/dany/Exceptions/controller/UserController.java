package dany.Exceptions.controller;

import dany.Exceptions.model.User;
import dany.Exceptions.dto.UserDTO;
import dany.Exceptions.service.UserService;
import dany.Exceptions.validation.Groups;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // 🔹 GET ALL
    @GetMapping
    public ResponseEntity<List<User>> listar() {
        List<User> users = service.listar();
        return ResponseEntity.ok(users);
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> obtener(@PathVariable Long id) {
        User user = service.buscarPorId(id);
        return ResponseEntity.ok(user);
    }

    // 🔹 POST (CREATE)
    @PostMapping
    public ResponseEntity<User> crear(
            @Validated(Groups.Crear.class)
            @Valid
            @RequestBody UserDTO dto) {

        User creado = service.crear(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // 🔹 PUT (UPDATE)
    @PutMapping
    public ResponseEntity<User> actualizar(
            @Validated(Groups.Actualizar.class)
            @Valid
            @RequestBody UserDTO dto) {

        User actualizado = service.actualizar(dto);

        return ResponseEntity.ok(actualizado);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
