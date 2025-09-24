package com.Springboot.aplicationweb.Usuario.Model;

import com.Springboot.aplicationweb.CuentaBancaria.Model.Cuenta_Bancaria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// Esto es lo que va a llevar mi base de datos

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    // Esta es la llave foránea
    @OneToMany(mappedBy = "usuarioId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cuenta_Bancaria> cuentas_bancarias;

    @Column(name = "nombre", nullable = false, length = 100)
    private String  nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String correo;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "TipoUsuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.CLIENTE;

    public enum Rol {
        CLIENTE,
        EMPLEADO,
        ADMIN
    }
}
