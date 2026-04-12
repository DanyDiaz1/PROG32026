package utn_dany.Guia2SpringWeb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleUpdateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.response.SaleResponseDTO;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    // Como los nombres coinciden, solo necesitamos explicitar totalPrice
    // porque viene como parámetro separado, no del DTO
    @Mapping(source = "totalPrice", target = "totalPrice")
    SaleEntity toEntity(SaleCreateRequestDTO dto, double totalPrice);

    // Con múltiples fuentes hay que especificar de dónde viene cada campo
    @Mapping(source = "existingSale.id", target = "id")
    @Mapping(source = "existingSale.userId", target = "userId")
    @Mapping(source = "existingSale.productId", target = "productId")
    @Mapping(source = "dto.quantity", target = "quantity")
    @Mapping(source = "recalculatedTotalPrice", target = "totalPrice")
    SaleEntity toUpdatedEntity(SaleEntity existingSale,
                               SaleUpdateRequestDTO dto,
                               double recalculatedTotalPrice);

    // Nombres iguales → mapeo 100% automático
    SaleResponseDTO toResponseDTO(SaleEntity entity);
}
