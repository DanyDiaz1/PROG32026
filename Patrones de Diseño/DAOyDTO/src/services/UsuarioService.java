package services;

import dto.UsuarioDTO;
import models.UsuarioDAO;
import repositorio.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repositorio = new UsuarioRepository();

    public UsuarioDTO obtenerUsuarioPublico(int id) {

        UsuarioDAO usuarioDAO = repositorio.buscarPorId(id);

        // convertir DAO a DTO
        UsuarioDTO dto = new UsuarioDTO(
                usuarioDAO.getNombre(),
                usuarioDAO.getSaldo()
        );

        return dto;
    }
}
