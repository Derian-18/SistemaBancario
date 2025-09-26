package com.Springboot.aplicationweb.CuentaBancaria.Repositorios;

import com.Springboot.aplicationweb.CuentaBancaria.Model.Cuenta_Bancaria;
import org.springframework.data.jpa.repository.JpaRepository;

// Aquí se conectará a la base de datos de la cuenta bancaria
public interface CuentaBancariaRepositorio extends JpaRepository<Cuenta_Bancaria, Integer> {

}
