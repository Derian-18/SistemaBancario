package com.Springboot.aplicationweb.Usuario.Controllers;
import com.Springboot.aplicationweb.Usuario.Dto.UsuarioGetDTO;
import com.Springboot.aplicationweb.Usuario.Dto.UsuarioResponseDTO;
import com.Springboot.aplicationweb.Usuario.Dto.UsuarioUpdateDTO;
import com.Springboot.aplicationweb.Usuario.Servicios.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioService serv_usuario;

    // Aquí se puede usar la clase DTO o usar ResponseEntity o usar los dos al mismo tiempo

    // Este GetMapping hace que se muestren todos los usuarios mediante el ResponseDTO
    @GetMapping
    public ResponseEntity<List<UsuarioGetDTO>> listarTodos(){
        List<UsuarioGetDTO> usuarios = serv_usuario.ListaUsuarios().stream()
                .map(u -> new UsuarioGetDTO(u.getUsuarioId(),
                        u.getNombre(),
                        u.getApellido(),
                        u.getCorreo(),
                        u.getRol(),
                        u.getNumeroCuenta())).toList();
        return ResponseEntity.ok(usuarios);
    }

    // Aqui buscamos un usuario por el id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioGetDTO> buscar(@Valid @PathVariable int id){
        return serv_usuario.BuscarId(id)
                .map(usuario -> new UsuarioGetDTO(
                        usuario.getUsuarioId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getCorreo(),
                        usuario.getRol(),
                        usuario.getNumeroCuenta()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Aqui actualizaremos un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioUpdateDTO> actualizarUsuario(@Valid @PathVariable int id, @RequestBody UsuarioUpdateDTO usuariodto){
        return serv_usuario.Actualizar(id, usuariodto)
                .map(u -> new UsuarioUpdateDTO(u.getNombre(),
                        u.getApellido(),
                        u.getCorreo(),
                        u.getContrasenia()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Aqui eliminaremos un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable int id){
        try{
            serv_usuario.Eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
