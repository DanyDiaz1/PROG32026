package dany.DTOMapStruct.repository;



import dany.DTOMapStruct.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IRepository<UserEntity> {
    // Lista en memoria que hace las veces de tabla de base de datos
    private final List<UserEntity> storage = new ArrayList<>();

    // Contador para simular el autoincremento de id (como IDENTITY en JPA)
    private long nextId = 1L;

    // ── FIND ALL ───────────────────────────────────────────────────────────
    // Devuelve una copia de la lista para proteger el storage interno
    public List<UserEntity> findAll() {
        return new ArrayList<>(storage);
    }

    // ── SAVE ───────────────────────────────────────────────────────────────
    // Solo inserta entidades nuevas — asigna el id antes de guardar
    // Para actualizar una entidad existente usar update()
    public UserEntity save(UserEntity entity) {
        entity.setId(nextId++);
        storage.add(entity);
        return entity;
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────
    // Busca la entidad por id y la reemplaza en la lista
    // Devuelve la entidad actualizada, o null si el id no existe
    public UserEntity update(UserEntity entity) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId().equals(entity.getId())) {
                storage.set(i, entity);
                return entity;
            }
        }
        return null;  // el Service verifica que el usuario exista antes de llamar a update()
    }

    // ── DELETE ─────────────────────────────────────────────────────────────
    // Recibe la entidad completa y la elimina de la lista
    // Devuelve true si la eliminó, false si no la encontró
    public boolean delete(UserEntity entity) {
        return storage.removeIf(u -> u.getId().equals(entity.getId()));
    }

    // ── FIND BY ID ─────────────────────────────────────────────────────────
    // Recibe long primitivo (no Long) — mismo contrato que JPA
    // Devuelve Optional: presente si existe, vacío si no
    public Optional<UserEntity> findById(long id) {
        return storage.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }
}
