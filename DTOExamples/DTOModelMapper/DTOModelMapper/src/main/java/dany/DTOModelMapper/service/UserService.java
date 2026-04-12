package dany.DTOModelMapper.service;


import dany.DTOModelMapper.mapper.UserMapper;
import dany.DTOModelMapper.model.UserEntity;
import dany.DTOModelMapper.model.dto.reponsedto.UserResponseDTO;
import dany.DTOModelMapper.model.dto.requestdto.UserRequestDTO;
import dany.DTOModelMapper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
