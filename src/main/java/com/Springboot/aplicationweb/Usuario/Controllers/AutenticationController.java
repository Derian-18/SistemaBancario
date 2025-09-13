package com.Springboot.aplicationweb.Usuario.Controllers;

import com.Springboot.aplicationweb.Usuario.Dto.UsuarioLoginDTO;
import com.Springboot.aplicationweb.Usuario.Dto.UsuarioLoginResponseDTO;
import com.Springboot.aplicationweb.Usuario.Dto.UsuarioRegisterDTO;
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

    @PostMapping
    public ResponseEntity<UsuarioLoginResponseDTO> login(@Valid @RequestBody UsuarioLoginDTO loginDTO){
        try {
            UsuarioLoginResponseDTO response = serv_usuario.validarLogin(loginDTO);

            if (response.isSuccess()){
                return ResponseEntity.ok(response);
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

        }catch (Exception e){
            UsuarioLoginResponseDTO errorResponse = new UsuarioLoginResponseDTO(
                    false,
                    "Error interno del servidor",
                    null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Aqui crearemos un usuario mediante la ventana de registro
    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterDTO> crearUsuario(@Valid @RequestBody UsuarioRegisterDTO usuariodto){
        Usuarios insertar = serv_usuario.Crear(usuariodto);
        UsuarioRegisterDTO respDTO = new UsuarioRegisterDTO(
                insertar.getNombre(),
                insertar.getApellido(),
                insertar.getCorreo(),
                insertar.getContrasenia());
        return ResponseEntity.status(HttpStatus.CREATED).body(respDTO);
    }
}