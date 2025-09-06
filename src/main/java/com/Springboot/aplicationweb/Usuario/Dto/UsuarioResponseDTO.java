package com.Springboot.aplicationweb.Usuario.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta es la clase de respuesta para obtener solo lo requerido. (No mostrar información delicada como contrasenias)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    @NotNull
    @NotBlank
    private Integer usuarioId;

    @NotNull(message = "Solo esta permitido ingresar letras")
    @Pattern(regexp = "^[A-Za-z\\sáéíóúÁÉÍÓÚñÑ]+$")
    @NotBlank(message = "El nombre es obligatorio")
    private String  nombre;

    @NotNull
    @NotBlank(message = "El apelliddo es obligatorio")
    @Pattern(regexp = "^[A-Za-z\\sáéíóúÁÉÍÓÚñÑ]+$")
    private String apellido;

    @NotNull
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El email debe estar escrito correctamente")
    private String correo;
}
