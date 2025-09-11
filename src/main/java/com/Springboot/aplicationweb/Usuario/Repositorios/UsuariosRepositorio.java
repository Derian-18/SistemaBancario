package com.Springboot.aplicationweb.Usuario.Repositorios;

import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Aquí es donde ya se conecta a la base de datos
@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Integer>{

    // Metodo para buscar por email
    Optional<Usuarios> findByCorreo(String correo);

    // Metodo para verificar si existe por email
    boolean existsByCorreo(String correo);

}
