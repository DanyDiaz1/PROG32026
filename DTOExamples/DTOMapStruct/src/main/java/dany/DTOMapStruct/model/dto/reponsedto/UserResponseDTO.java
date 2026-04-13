package dany.DTOMapStruct.model.dto.reponsedto;

import lombok.*;

// UserResponseDTO — datos que le devolvemos al cliente
// Nunca devolvemos la entidad directamente → controlamos qué campos exponemos
@Getter
@Setter   // ModelMapper lo necesita para asignar los campos uno a uno
@Builder  // Builder y MapStruct lo usan para construir el objeto
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long   id;
    private String fullName;  // combinamos name + lastName de la entidad
    private String email;
    // password NO está aquí — nunca exponemos datos sensibles en el response
}
