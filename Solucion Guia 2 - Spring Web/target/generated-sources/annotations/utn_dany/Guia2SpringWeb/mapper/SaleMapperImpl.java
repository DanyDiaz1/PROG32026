package utn_dany.Guia2SpringWeb.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleUpdateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.response.SaleResponseDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-12T22:55:55-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleEntity toEntity(SaleCreateRequestDTO dto, double totalPrice) {
        if ( dto == null ) {
            return null;
        }

        SaleEntity saleEntity = new SaleEntity();

        if ( dto != null ) {
            saleEntity.setId( dto.getId() );
            saleEntity.setUserId( dto.getUserId() );
            saleEntity.setProductId( dto.getProductId() );
            saleEntity.setQuantity( dto.getQuantity() );
        }
        saleEntity.setTotalPrice( totalPrice );

        return saleEntity;
    }

    @Override
    public SaleEntity toUpdatedEntity(SaleEntity existingSale, SaleUpdateRequestDTO dto, double recalculatedTotalPrice) {
        if ( existingSale == null && dto == null ) {
            return null;
        }

        SaleEntity saleEntity = new SaleEntity();

        if ( existingSale != null ) {
            saleEntity.setId( existingSale.getId() );
            saleEntity.setUserId( existingSale.getUserId() );
            saleEntity.setProductId( existingSale.getProductId() );
        }
        if ( dto != null ) {
            saleEntity.setQuantity( dto.getQuantity() );
        }
        saleEntity.setTotalPrice( recalculatedTotalPrice );

        return saleEntity;
    }

    @Override
    public SaleResponseDTO toResponseDTO(SaleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SaleResponseDTO.SaleResponseDTOBuilder saleResponseDTO = SaleResponseDTO.builder();

        saleResponseDTO.id( entity.getId() );
        saleResponseDTO.userId( entity.getUserId() );
        saleResponseDTO.productId( entity.getProductId() );
        saleResponseDTO.quantity( entity.getQuantity() );
        saleResponseDTO.totalPrice( entity.getTotalPrice() );

        return saleResponseDTO.build();
    }
}
