package dany.DTOModelMapper.controller;


import dany.DTOModelMapper.model.dto.reponsedto.UserResponseDTO;
import dany.DTOModelMapper.model.dto.requestdto.UserRequestDTO;
import dany.DTOModelMapper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// El Controller solo habla con el Service — nunca toca el Mapper ni el Repository
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ── POST /api/users ────────────────────────────────────────────────────
    // @RequestBody convierte el JSON del body en UserRequestDTO automáticamente
    // Devuelve 201 Created con el UserResponseDTO en el body
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    // ── GET /api/users/{id} ────────────────────────────────────────────────
    // @PathVariable extrae el {id} de la URL → ej: GET /api/users/5
    // Devuelve 200 OK con el UserResponseDTO, o excepción si no existe
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // ── GET /api/users ─────────────────────────────────────────────────────
    // Devuelve 200 OK con la lista completa → puede ser una lista vacía []
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // ── PUT /api/users/{id} ────────────────────────────────────────────────
    // El id viene en la URL, los nuevos datos vienen en el body
    // Devuelve 200 OK con el UserResponseDTO actualizado
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable long id,
            @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    // ── DELETE /api/users/{id} ─────────────────────────────────────────────
    // Devuelve 204 No Content → operación exitosa sin body en la respuesta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
