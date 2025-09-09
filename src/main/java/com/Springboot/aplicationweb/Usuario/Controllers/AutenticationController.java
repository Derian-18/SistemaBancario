package com.Springboot.aplicationweb.Usuario.Controllers;

import com.Springboot.aplicationweb.Usuario.Dto.UsuarioCreateDTO;
import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import com.Springboot.aplicationweb.Usuario.Servicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AutenticationController {


    @Autowired
    private UsuarioService serv_usuario;

    // Aqui crearemos un usuario mediante la ventana de registro
    @PostMapping("/register")
    public ResponseEntity<UsuarioCreateDTO> crearUsuario(@Valid @RequestBody UsuarioCreateDTO usuariodto){
        Usuarios insertar = serv_usuario.Crear(usuariodto);
        UsuarioCreateDTO respDTO = new UsuarioCreateDTO(
                insertar.getNombre(),
                insertar.getApellido(),
                insertar.getCorreo());
        return ResponseEntity.status(HttpStatus.CREATED).body(respDTO);
    }
}