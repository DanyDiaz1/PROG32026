package utn_dany.Guia2SpringWeb.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleUpdateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.response.SaleResponseDTO;

@Component
@RequiredArgsConstructor
public class SaleMapper {

    private final ModelMapper modelMapper;

    public SaleEntity toEntity(SaleCreateRequestDTO dto, double totalPrice) {
        SaleEntity entity = modelMapper.map(dto, SaleEntity.class);
        entity.setTotalPrice(totalPrice);
        return entity;
    }

    public SaleEntity toUpdatedEntity(SaleEntity existingSale,
                                      SaleUpdateRequestDTO dto,
                                      double recalculatedTotalPrice) {
        SaleEntity updated = modelMapper.map(existingSale, SaleEntity.class);
        updated.setQuantity(dto.getQuantity());
        updated.setTotalPrice(recalculatedTotalPrice);
        return updated;
    }

    public SaleResponseDTO toResponseDTO(SaleEntity entity) {
        return modelMapper.map(entity, SaleResponseDTO.class);
    }
}
