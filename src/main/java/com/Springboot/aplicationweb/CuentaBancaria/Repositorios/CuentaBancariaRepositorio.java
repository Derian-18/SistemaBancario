package com.Springboot.aplicationweb.CuentaBancaria.Repositorios;

import com.Springboot.aplicationweb.CuentaBancaria.Model.Cuenta_Bancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Aquí se conectará a la base de datos de la cuenta bancaria
@Repository
public interface CuentaBancariaRepositorio extends JpaRepository<Cuenta_Bancaria, Integer> {

}
