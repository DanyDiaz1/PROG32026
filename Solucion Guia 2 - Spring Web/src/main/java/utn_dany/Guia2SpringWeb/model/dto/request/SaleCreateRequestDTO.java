package utn_dany.Guia2SpringWeb.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SaleCreateRequestDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
}
