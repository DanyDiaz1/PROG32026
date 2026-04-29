package utn_dany.Guia2SpringWeb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn_dany.Guia2SpringWeb.model.dto.request.SaleCreateRequestDTO;
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
    public ResponseEntity<SaleResponseDTO> create(@Valid @RequestBody SaleCreateRequestDTO saleCreateRequestDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saleService.create(saleCreateRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAll() {
            return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getById(@PathVariable Long id) {
            return ResponseEntity.status(HttpStatus.OK).body(saleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> update(@PathVariable Long id,
                                                  @Valid @RequestBody SaleUpdateRequestDTO saleUpdateRequestDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(saleService.update(id, saleUpdateRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id) {
            saleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
