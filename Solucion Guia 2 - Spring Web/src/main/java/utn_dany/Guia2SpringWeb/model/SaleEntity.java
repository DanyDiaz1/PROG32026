package utn_dany.Guia2SpringWeb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleEntity {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private double totalPrice;
}
