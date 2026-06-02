# Cambios realizados: Basic Auth -> JWT

Este proyecto mantiene la logica de negocio original y reemplaza el mecanismo de autenticacion HTTP Basic por JWT.

## Clases agregadas

- `AuthController`: endpoint `POST /auth/login` para autenticar usuario/password y devolver un token.
- `AuthRequestDTO`: cuerpo esperado para login.
- `AuthResponseDTO`: respuesta con `tokenType` y `accessToken`.
- `JwtService`: crea, firma, lee y valida tokens JWT.
- `JwtAuthenticationFilter`: lee el header `Authorization: Bearer <token>`, valida el token y carga la autenticacion en el `SecurityContext`.

## Clase modificada

- `SecurityConfig`: se desactiva Basic Auth, se habilita seguridad stateless y se agrega el filtro JWT antes de `UsernamePasswordAuthenticationFilter`.

## Flujo basico

1. El cliente llama a `POST /auth/login` con `username` y `password`.
2. Spring autentica esas credenciales usando `AuthenticationManager`, `DaoAuthenticationProvider`, `UserDetailsService` y `PasswordEncoder`.
3. Si son correctas, `JwtService` genera un token firmado.
4. En cada endpoint privado, el cliente envia el header: `Authorization: Bearer <token>`.
5. `JwtAuthenticationFilter` valida el token y carga el usuario autenticado en el `SecurityContext`.
6. Spring Security aplica las reglas de roles/permisos configuradas en `SecurityConfig`.

## Ejemplo de login

```json
POST /auth/login
Content-Type: application/json

{
  "username": "usuario",
  "password": "password"
}
```

Respuesta:

```json
{
  "tokenType": "Bearer",
  "accessToken": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Luego usar:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```


## Registro de usuarios

Se agrego el endpoint publico:

```http
POST /auth/register
Content-Type: application/json

{
  "username": "matias",
  "password": "1234"
}
```

El registro crea un usuario habilitado con rol `USER`, guarda la password encriptada con BCrypt y devuelve un JWT igual que el login.

Flujo resumido:

1. El cliente envia username y password a `/auth/register`.
2. `AuthService` verifica que el username no exista.
3. Se busca el rol `USER` inicial.
4. Se guarda el usuario con password encriptada.
5. Se genera un JWT para ese usuario.
6. El cliente usa el token en endpoints privados con `Authorization: Bearer <token>`.
