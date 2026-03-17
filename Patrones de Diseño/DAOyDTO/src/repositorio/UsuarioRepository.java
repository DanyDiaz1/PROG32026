package repositorio;

import models.UsuarioDAO;

public class UsuarioRepository {

    public UsuarioDAO buscarPorId(int id) {

        // simulación de base de datos
        return new UsuarioDAO(
                id,
                "Juan Perez",
                "34567890",
                "password123",
                1500.50
        );
    }
}
