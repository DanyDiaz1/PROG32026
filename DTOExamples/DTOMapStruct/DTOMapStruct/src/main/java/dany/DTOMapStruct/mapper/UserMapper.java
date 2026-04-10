package dany.DTOMapStruct.mapper;

import dany.DTOMapStruct.model.UserEntity;
import dany.DTOMapStruct.model.dto.reponsedto.UserResponseDTO;
import dany.DTOMapStruct.model.dto.requestdto.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


// MapStruct lee esta interfaz al compilar y genera UserMapperImpl automáticamente
// componentModel = "spring" → la clase generada tiene @Component → Spring la inyecta
@Mapper(componentModel = "spring")
public interface UserMapper {

    // ── REQUEST DTO → ENTITY ───────────────────────────────────────────────
    // ignore = true en id: no existe todavía, el repositorio lo asigna al guardar
    // El resto de campos coincide en nombre → MapStruct los mapea automáticamente
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserRequestDTO dto);

    // ── ENTITY → RESPONSE DTO ──────────────────────────────────────────────
    // fullName no existe en la entidad → expression permite escribir Java inline
    // password no está en UserResponseDTO → MapStruct la ignora automáticamente
    @Mapping(
            target     = "fullName",
            expression = "java(user.getName() + \" \" + user.getLastName())"
    )
    UserResponseDTO toDTO(UserEntity user);
}
