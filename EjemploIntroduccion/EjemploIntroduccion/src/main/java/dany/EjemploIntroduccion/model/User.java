package dany.EjemploIntroduccion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data genera getters, setters, toString, equals y hashCode
@Data
// @AllArgsConstructor genera un constructor con todos los campos
@AllArgsConstructor
// @NoArgsConstructor genera un constructor vacío (necesario para JSON)
@NoArgsConstructor
public class User {
    private Long   id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
