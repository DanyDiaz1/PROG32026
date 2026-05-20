package utn.DemoOpenApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.DemoOpenApi.model.dto.ProductoDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(
        name = "Productos",
        description = "Operaciones relacionadas con la gestión de productos"
)
public class ProductoController {

    private final List<ProductoDTO> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new ProductoDTO(1L, "Notebook Lenovo", 850000.0));
        productos.add(new ProductoDTO(2L, "Mouse Logitech", 45000.0));
    }

    @GetMapping
    @Operation(
            summary = "Listar productos",
            description = "Devuelve todos los productos cargados en el sistema"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar producto por ID",
            description = "Devuelve un producto específico según su identificador"
    )
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Long id) {
        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Crear producto",
            description = "Registra un nuevo producto en el sistema"
    )
    @ApiResponse(responseCode = "200", description = "Producto creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        productoDTO.setId((long) (productos.size() + 1));
        productos.add(productoDTO);

        return ResponseEntity.ok(productoDTO);
    }
}
