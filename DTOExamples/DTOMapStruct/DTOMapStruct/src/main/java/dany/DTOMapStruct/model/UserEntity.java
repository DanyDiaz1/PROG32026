package dany.DTOMapStruct.model;

import lombok.*;

@Getter
@Setter            // necesario para que update() modifique los campos de la instancia
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    private Long   id;        // el repositorio asigna el id al guardar
    private String name;
    private String lastName;
    private String email;
    private String password;  // en producción se guardaría hasheada (ej: BCrypt)
}
