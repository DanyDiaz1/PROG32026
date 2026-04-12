package utn_dany.Guia2SpringWeb.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private double totalPrice;
}