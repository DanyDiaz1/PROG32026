package utn.springSecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import utn.springSecurity.dto.AuthResponseDTO;
import utn.springSecurity.dto.RegisterRequestDTO;
import utn.springSecurity.model.RoleEntity;
import utn.springSecurity.model.RoleEnum;
import utn.springSecurity.model.UserEntity;
import utn.springSecurity.repository.RoleRepository;
import utn.springSecurity.repository.UserRepository;
import utn.springSecurity.security.jwt.JwtService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailServiceImpl userDetailsService;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {

        // Validamos que no exista otro usuario con el mismo username.
        if (userRepository.findUserEntityByUsername(request.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese username");
        }

        // Para no agregar complejidad, todo usuario registrado desde este endpoint nace con rol USER.
        RoleEntity userRole = roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No existe el rol USER inicial"));

        UserEntity newUser = UserEntity.builder()
                .username(request.username())
                // Nunca se guarda la password en texto plano: se guarda hasheada con BCrypt.
                .password(passwordEncoder.encode(request.password()))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roles(Set.of(userRole))
                .build();

        userRepository.save(newUser);

        // Luego de registrar, generamos un JWT para mostrar el ciclo completo:
        // registro -> usuario persistido -> token emitido -> token enviado en requests privadas.
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
        String jwt = jwtService.generateToken(userDetails);

        return new AuthResponseDTO("Bearer", jwt);
    }
}
