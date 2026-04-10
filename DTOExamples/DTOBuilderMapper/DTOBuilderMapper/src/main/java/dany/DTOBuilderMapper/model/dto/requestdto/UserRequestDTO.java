package dany.DTOBuilderMapper.model.dto.requestdto;

import lombok.Getter;

// UserRequestDTO — datos que el cliente envía en el body del POST o PUT
// No tiene id: todavía no fue creado, el repositorio lo asigna al guardar
@Getter
public class UserRequestDTO {

    private String name;
    private String lastName;
    private String email;
    private String password;  // llega en texto plano → en producción el Service la hashea
}
