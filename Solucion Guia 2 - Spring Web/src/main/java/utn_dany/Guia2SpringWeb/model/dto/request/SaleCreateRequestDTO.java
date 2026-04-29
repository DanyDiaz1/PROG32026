package utn_dany.Guia2SpringWeb.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SaleCreateRequestDTO {

    private Long id;

    @NotNull(message = "El userId es obligatorio")
    @Positive(message = "El userId debe ser mayor a 0")
    private Long userId;

    @NotNull(message = "El productId es obligatorio")
    @Positive(message = "El productId debe ser mayor a 0")
    private Long productId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Max(value = 1000, message = "La cantidad es demasiado grande")
    private int quantity;
}
