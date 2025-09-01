package com.Springboot.aplicationweb.Usuario.Repositorios;

import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Aquí es donde ya se conecta a la base de datos
@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Integer>{

}
