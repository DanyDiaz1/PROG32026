package utn_dany.Guia2SpringWeb.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDTO {
    private Long id;
    private Long idUser;
    private Long idProduct;
    private int quantity;
    private double totalPrice;
}
