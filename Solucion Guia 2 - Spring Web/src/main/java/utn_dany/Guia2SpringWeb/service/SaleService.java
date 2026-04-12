package utn_dany.Guia2SpringWeb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utn_dany.Guia2SpringWeb.model.*;
import utn_dany.Guia2SpringWeb.repository.SaleRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class SaleService {

    private final SaleRepository repository;
    private final ProductService productService;
    private final UserService userService;

    public SaleEntity create(CreateSale newSale) {
        UserEntity user = userService.getById(newSale.getUserId());
        ProductEntity product = productService.getById(newSale.getProductId());

        if(newSale.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        if(product.getStock() < newSale.getQuantity()){
            throw new IllegalArgumentException("No hay suficiente stock disponible.");
        }

        SaleEntity saved = repository.save(new SaleEntity(
                newSale.getSaleId(),
                user.getId(),
                product.getId(),
                newSale.getQuantity(),
                product.getPrice() * newSale.getQuantity())
        );

        product.setStock(product.getStock() - newSale.getQuantity());

        productService.update(product.getId(), product);

        return saved;
    }

    public List<SaleEntity> findAll() {
        return repository.findAll();
    }

    public SaleEntity findById(Long saleId) {
        return repository.findById(saleId)
                .orElseThrow(() -> new NoSuchElementException("Venta no encontrada."));
    }

    public SaleEntity update(Long saleId, UpdateSale updateSale) {
        //SaleEntity sale = findById(saleId);
        SaleEntity sale = repository.findById(saleId).orElseThrow(() -> new NoSuchElementException("Venta no encontrada."));

        if(updateSale.getQuantity() < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        ProductEntity product = productService.getById(sale.getProductId());

        int differenceStock = updateSale.getQuantity() - sale.getQuantity();
        if(updateSale.getQuantity() > sale.getQuantity() && product.getStock() < differenceStock) {
            throw new IllegalArgumentException("No hay suficiente stock.");
        }

        sale.setQuantity(updateSale.getQuantity());
        sale.setTotalPrice(updateSale.getQuantity()* product.getPrice());

        // sale quantity = 3 - updatesale quantity = 5 => difference = 2
        // sale quantity = 3 - updatesale quantity = 1 => difference = -2
        product.setStock(product.getStock() - differenceStock);
        productService.update(product.getId(), product);

        return repository.update(sale);
    }

    public void delete(Long idSale) {
        SaleEntity toDelete = findById(idSale);

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
