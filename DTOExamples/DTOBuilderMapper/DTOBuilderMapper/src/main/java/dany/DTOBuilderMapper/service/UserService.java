package dany.DTOBuilderMapper.service;

import dany.DTOBuilderMapper.mapper.UserMapper;
import dany.DTOBuilderMapper.model.UserEntity;
import dany.DTOBuilderMapper.model.dto.reponsedto.UserResponseDTO;
import dany.DTOBuilderMapper.model.dto.requestdto.UserRequestDTO;
import dany.DTOBuilderMapper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// El Service no sabe CÓMO se hace el mapeo, solo que existe un UserMapper
// Cambiar de Builder a MapStruct no requiere tocar ni una línea de este archivo
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // ── CREATE ─────────────────────────────────────────────────────────────
    // Flujo: RequestDTO → Entity → save() → ResponseDTO
    public UserResponseDTO create(UserRequestDTO dto) {
        UserEntity entity = userMapper.toEntity(dto);
        UserEntity saved  = userRepository.save(entity);
        return userMapper.toDTO(saved);
    }

    // ── READ ONE ───────────────────────────────────────────────────────────
    // orElseThrow lanza excepción si el id no existe en el repositorio
    public UserResponseDTO findById(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        return userMapper.toDTO(user);
    }

    // ── READ ALL ───────────────────────────────────────────────────────────
    // userMapper::toDTO es una referencia al metodo — equivale a u -> userMapper.toDTO(u)
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────
    // Verificamos que el usuario exista, actualizamos sus campos y llamamos a update()
    public UserResponseDTO update(long id, UserRequestDTO dto) {
        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        existing.setName(dto.getName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPassword(dto.getPassword());
        return userMapper.toDTO(userRepository.update(existing));
    }

    // ── DELETE ─────────────────────────────────────────────────────────────
    // Obtenemos la entidad completa para pasársela al repositorio según el contrato
    public void delete(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        userRepository.delete(user);
    }
}
