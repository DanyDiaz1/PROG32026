package dany.Exceptions.dto;

import dany.Exceptions.validation.Groups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull(groups = Groups.Actualizar.class, message = "El ID es obligatorio para actualizar")
    private Long id;

    @NotBlank(groups = {Groups.Crear.class, Groups.Actualizar.class})
    @Size(min = 3, groups = {Groups.Crear.class, Groups.Actualizar.class},
            message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @Email(groups = {Groups.Crear.class, Groups.Actualizar.class}, message = "Email inválido")
    @NotBlank(groups = {Groups.Crear.class, Groups.Actualizar.class})
    private String email;
}
