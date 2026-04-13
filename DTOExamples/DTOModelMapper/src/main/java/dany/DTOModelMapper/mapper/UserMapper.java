package dany.DTOModelMapper.mapper;


import dany.DTOModelMapper.model.UserEntity;
import dany.DTOModelMapper.model.dto.reponsedto.UserResponseDTO;
import dany.DTOModelMapper.model.dto.requestdto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// Envolvemos al ModelMapper en nuestro @Component para bajo acoplamiento
// El Service no depende directamente de la librería externa
@Component
@RequiredArgsConstructor
public class UserMapper {

    // ModelMapper es el @Bean declarado en AppConfig.java
    private final ModelMapper modelMapper;

    // ── REQUEST DTO → ENTITY ───────────────────────────────────────────────
    // ModelMapper mapea automáticamente los campos con el mismo nombre:
    // name→name, lastName→lastName, email→email, password→password
    // id no está en UserRequestDTO → no se mapea → el repositorio lo asigna
    public UserEntity toEntity(UserRequestDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    // ── ENTITY → RESPONSE DTO ──────────────────────────────────────────────
    // Paso 1: ModelMapper mapea automáticamente lo que coincide (id, email)
    // Paso 2: fullName lo seteamos a mano porque combina dos campos distintos
    // password no está en UserResponseDTO → ModelMapper la ignora automáticamente
    public UserResponseDTO toDTO(UserEntity user) {
        UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);
        dto.setFullName(user.getName() + " " + user.getLastName());
        return dto;
    }
}
