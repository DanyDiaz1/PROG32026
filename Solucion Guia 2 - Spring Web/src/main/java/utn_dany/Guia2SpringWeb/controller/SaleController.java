package utn_dany.Guia2SpringWeb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleUpdateRequestDTO;
import utn_dany.Guia2SpringWeb.model.dto.response.SaleResponseDTO;
import utn_dany.Guia2SpringWeb.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> create(@RequestBody SaleCreateRequestDTO saleCreateRequestDTO) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(saleService.create(saleCreateRequestDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(saleService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> update(@PathVariable Long id,
                                         @RequestBody SaleUpdateRequestDTO saleUpdateRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(saleService.update(id, saleUpdateRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id) {
        try{
            saleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
