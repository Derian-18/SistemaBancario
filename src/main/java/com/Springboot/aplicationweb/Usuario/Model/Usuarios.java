package com.Springboot.aplicationweb.Usuario.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esto es lo que va a llevar mi base de datos

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @Column(name = "nombre", nullable = false, length = 100)
    private String  nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String correo;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;
}
