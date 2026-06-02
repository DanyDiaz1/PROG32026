package utn.springSecurity.dto;

public record AuthResponseDTO(
        String tokenType,
        String accessToken
) {
}
