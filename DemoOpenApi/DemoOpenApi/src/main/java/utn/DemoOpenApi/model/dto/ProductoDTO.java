package utn.DemoOpenApi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO utilizado para crear o mostrar productos")
public class ProductoDTO {

    @Schema(
            description = "Identificador único del producto",
            example = "1"
    )
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(
            description = "Nombre comercial del producto",
            example = "Notebook Lenovo"
    )
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    @Schema(
            description = "Precio del producto en pesos",
            example = "850000"
    )
    private Double precio;

}
