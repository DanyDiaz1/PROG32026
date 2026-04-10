package dany.DTOBuilderMapper.mapper;

import dany.DTOBuilderMapper.model.UserEntity;
import dany.DTOBuilderMapper.model.dto.reponsedto.UserResponseDTO;
import dany.DTOBuilderMapper.model.dto.requestdto.UserRequestDTO;
import org.springframework.stereotype.Component;

// @Component registra este mapper como bean de Spring
// Centraliza toda la conversión — el Service no sabe cómo se construye cada objeto
@Component
public class UserMapper {

    // ── REQUEST DTO → ENTITY ───────────────────────────────────────────────
    // id no se setea aquí: lo asigna el repositorio al guardar
    public UserEntity toEntity(UserRequestDTO dto) {
        return UserEntity.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())  // la password va a la entidad, NUNCA al response
                .build();
    }

    // ── ENTITY → RESPONSE DTO ──────────────────────────────────────────────
    // fullName combina name + lastName que en la entidad están separados
    public UserResponseDTO toDTO(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getName() + " " + user.getLastName())
                .email(user.getEmail())
                // password NO se incluye → seguridad
                .build();
    }
}
