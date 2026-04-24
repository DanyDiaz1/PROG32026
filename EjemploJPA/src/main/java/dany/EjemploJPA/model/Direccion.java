package dany.EjemploJPA.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Direccion {

    private String calle;
    private String ciudad;
}
