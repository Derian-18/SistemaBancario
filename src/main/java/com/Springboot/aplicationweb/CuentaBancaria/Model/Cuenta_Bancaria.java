package com.Springboot.aplicationweb.CuentaBancaria.Model;

import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cuenta_bancaria")
public class Cuenta_Bancaria {
    // Esta es la base de datos para la cuanta bancaria de cada usuario registrado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuenta_id;

    // Esta es la llave foránea
    @OneToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    @JsonBackReference
    private Usuarios usuarioId;

    @Column(name = "numeroCuenta", nullable = false)
    private String numeroCuenta;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;
}