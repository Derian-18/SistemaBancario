package com.Springboot.aplicationweb.Usuario.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Esta clase DTO que usaremos para iniciar sesion

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {

    @NotNull
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El email debe estar escrito correctamente")
    private String correo;

    @NotNull
    @NotBlank(message = "La contrasenia es obligatoria")
    @Size(min = 4, message = "La contrasenia debe tener almenos 4 caracteres")
    private String contrasenia;
}
