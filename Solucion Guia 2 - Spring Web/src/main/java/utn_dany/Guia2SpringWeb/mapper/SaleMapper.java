package utn_dany.Guia2SpringWeb.mapper;

import org.springframework.stereotype.Component;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleUpdateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.response.SaleResponseDTO;

@Component
public class SaleMapper {

    public SaleEntity toEntity(SaleCreateRequestDTO dto, double totalPrice) {
        return SaleEntity.builder()
                .id(dto.getSaleId())
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .totalPrice(totalPrice)
                .build();
    }

    public SaleEntity toUpdatedEntity(SaleEntity existingSale,
                                      SaleUpdateRequestDTO dto,
                                      double recalculatedTotalPrice) {
        return SaleEntity.builder()
                .id(existingSale.getId())
                .userId(existingSale.getUserId())
                .productId(existingSale.getProductId())
                .quantity(dto.getQuantity())
                .totalPrice(recalculatedTotalPrice)
                .build();
    }

    public SaleResponseDTO toResponseDTO(SaleEntity entity) {
        return SaleResponseDTO.builder()
                .id(entity.getId())
                .idUser(entity.getUserId())
                .idProduct(entity.getProductId())
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .build();
    }
}