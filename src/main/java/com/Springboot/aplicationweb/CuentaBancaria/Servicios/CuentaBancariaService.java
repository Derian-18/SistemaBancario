package com.Springboot.aplicationweb.CuentaBancaria.Servicios;

import com.Springboot.aplicationweb.CuentaBancaria.Model.Cuenta_Bancaria;
import com.Springboot.aplicationweb.CuentaBancaria.Repositorios.CuentaBancariaRepositorio;
import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class CuentaBancariaService {

    // Hacemos la inyección de dependencias necesarias
    @Autowired
    private CuentaBancariaRepositorio repo_cuenta;

    // Este es el metodo que crea una cuenta bancaria para un usuario el cual se usa en UsuarioService
    public Cuenta_Bancaria crearCuentaUsuario(Usuarios usuario){
        Cuenta_Bancaria cuenta = new Cuenta_Bancaria();
        cuenta.setUsuarioId(usuario);
        cuenta.setNumeroCuenta(generarNumeroDeCuenta());
        cuenta.setSaldo(BigDecimal.ZERO);

        return repo_cuenta.save(cuenta);
    }

    // Aquí se empieza a construir el numero de cuenta
    public String generarNumeroDeCuenta(){
        StringBuilder numeroDeCuenta = new StringBuilder("001");
        Random random = new Random();

        for(int i = 0; i < 13; i++) {
            int digitoAleatorio = random.nextInt(10);
            numeroDeCuenta.append(digitoAleatorio);
        }
        return numeroDeCuenta.toString();
    }
}