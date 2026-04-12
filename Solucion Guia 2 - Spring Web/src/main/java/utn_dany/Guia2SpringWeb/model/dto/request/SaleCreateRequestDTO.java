package utn_dany.Guia2SpringWeb.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleCreateRequestDTO {
    private Long saleId;
    private Long productId;
    private Long userId;
    private Integer quantity;
}
