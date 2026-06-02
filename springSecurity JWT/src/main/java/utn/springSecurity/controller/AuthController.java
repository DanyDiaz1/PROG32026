package utn.springSecurity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.springSecurity.dto.AuthRequestDTO;
import utn.springSecurity.dto.AuthResponseDTO;
import utn.springSecurity.dto.RegisterRequestDTO;
import utn.springSecurity.security.jwt.JwtService;
import utn.springSecurity.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody @Valid RegisterRequestDTO request) {
        // Endpoint publico: crea el usuario y devuelve un JWT para que el cliente pueda autenticarse.
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid AuthRequestDTO request) {

        // 1) El usuario envia username/password solo una vez, al endpoint de login.
        // 2) AuthenticationManager delega en DaoAuthenticationProvider.
        // 3) DaoAuthenticationProvider usa UserDetailsService y PasswordEncoder.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        // Si las credenciales son correctas, generamos el JWT.
        // A partir de ahora el cliente debe enviar este token en cada request privada.
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        String jwt = jwtService.generateToken(userDetails);

        return new AuthResponseDTO("Bearer", jwt);
    }
}
