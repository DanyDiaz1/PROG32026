package utn_dany.Guia2SpringWeb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSale {
    private Long saleId;
    private Long productId;
    private Long userId;
    private Integer quantity;
}
