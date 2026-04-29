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
    SaleEntity toEntity(SaleCreateRequestDTO dto, double totalPrice);


    // Nombres iguales → mapeo 100% automático
    SaleResponseDTO toResponseDTO(SaleEntity entity);
}
