package com.Springboot.aplicationweb.Usuario.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginResponseDTO {
    // Aqui se define si el login es exitoso
    private Boolean success;
    // Esto para mostrar mensaje al usuario
    private String message;
    // Esto se llena solo si el login es exitoso
    private UsuarioResponseDTO usuario;

    // Si @Data no funciona, agrega estos métodos manualmente:
    public boolean isSuccess() {
        return success;
    }
}
