package utn_dany.Guia2SpringWeb.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleUpdateRequestDTO {

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Max(value = 1000, message = "La cantidad es demasiado grande")
    private int quantity;
}
