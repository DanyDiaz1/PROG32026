package utn_dany.Guia2SpringWeb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utn_dany.Guia2SpringWeb.mapper.SaleMapper;
import utn_dany.Guia2SpringWeb.model.*;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleUpdateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.response.SaleResponseDTO;
import utn_dany.Guia2SpringWeb.repository.SaleRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class SaleService {

    private final SaleRepository repository;
    private final ProductService productService;
    private final UserService userService;
    private final SaleMapper mapper;

    public SaleResponseDTO create(SaleCreateRequestDTO newSale) {
        userService.getById(newSale.getUserId());
        ProductEntity product = productService.getById(newSale.getProductId());

        if(newSale.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        if(product.getStock() < newSale.getQuantity()){
            throw new IllegalArgumentException("No hay suficiente stock disponible.");
        }
        product.setStock(product.getStock() - newSale.getQuantity());
        productService.update(product.getId(), product);

        SaleEntity saved = repository.save(mapper.toEntity(newSale,newSale.getQuantity()*product.getPrice()));

        return mapper.toResponseDTO(saved);
    }

    public List<SaleResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public SaleResponseDTO findById(Long saleId) {
        return mapper.toResponseDTO(repository.findById(saleId)
                .orElseThrow(() -> new NoSuchElementException("Venta no encontrada.")));
    }

    public SaleResponseDTO update(Long saleId, SaleUpdateRequestDTO saleUpdateRequestDTO) {

        SaleEntity sale = repository.findById(saleId).orElseThrow(() -> new NoSuchElementException("Venta no encontrada."));

        if(saleUpdateRequestDTO.getQuantity() < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        ProductEntity product = productService.getById(sale.getProductId());

        int differenceStock = saleUpdateRequestDTO.getQuantity() - sale.getQuantity();
        if(saleUpdateRequestDTO.getQuantity() > sale.getQuantity() && product.getStock() < differenceStock) {
            throw new IllegalArgumentException("No hay suficiente stock.");
        }


        product.setStock(product.getStock() - differenceStock);
        productService.update(product.getId(), product);

        return mapper.toResponseDTO(repository.update(mapper.toUpdatedEntity(sale,saleUpdateRequestDTO,saleUpdateRequestDTO.getQuantity()* product.getPrice())));
    }

    public void delete(Long idSale) {
        SaleEntity toDelete = repository.findById(idSale).orElseThrow(() -> new NoSuchElementException("Venta no encontrada."));
        /*
        ProductEntity product = productService.getById(toDelete.getProductId());
        product.setStock(product.getStock() + toDelete.getQuantity());
        productService.update(product.getId(), product);
        */
        if(!repository.delete(toDelete)){
            throw new RuntimeException("No se pudo eliminar la venta.");
        }
    }

}
