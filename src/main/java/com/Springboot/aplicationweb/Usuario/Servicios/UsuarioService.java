package com.Springboot.aplicationweb.Usuario.Servicios;

import com.Springboot.aplicationweb.Usuario.Dto.*;
import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import com.Springboot.aplicationweb.Usuario.Repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Aquí ya manejamos la inyección de dependencias con @Autowired
    @Autowired
    private UsuariosRepositorio repo_usuarios;

    // Lo siguiente sirve para encriptar las contrasenias lo cual se hará uso de esto después
//    @Autowired
//    prívate PasswordEncoder contraseniaEncoder;

    public List<Usuarios> ListaUsuarios(){
        return repo_usuarios.findAll();
    }

    public Optional<Usuarios> BuscarId(int id){
        return repo_usuarios.findById(id);
    }

    // Este se va a usar para registro de usuarios
    public Usuarios Crear(UsuarioRegisterDTO usuario){
        Usuarios u = new Usuarios();
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setCorreo(usuario.getCorreo());
        u.setContrasenia(usuario.getContrasenia());
        return repo_usuarios.save(u);
    }

    public Optional<Usuarios> Actualizar(int id, UsuarioUpdateDTO actualiza){
        return repo_usuarios.findById(id).map(usuarioExiste -> {
            usuarioExiste.setNombre(actualiza.getNombre());
            usuarioExiste.setApellido(actualiza.getApellido());
            usuarioExiste.setCorreo(actualiza.getCorreo());
            usuarioExiste.setContrasenia(actualiza.getContrasenia());
            return repo_usuarios.save(usuarioExiste);
        });
    }

    public void Eliminar(int id){
        repo_usuarios.deleteById(id);
    }

    // Aquí ira el método para poder iniciar sesión
    // Por cuestiones de simplicidad se usara .equals para las contrasenias

    // Aquí se está usando el método que esta en el repositorio
    public UsuarioLoginResponseDTO validarLogin(UsuarioLoginDTO loginDTO){
        Optional<Usuarios> usuarioOpt = repo_usuarios.findByCorreo(loginDTO.getCorreo());

        if (usuarioOpt.isEmpty()){
            return new UsuarioLoginResponseDTO(false, "Usuario no encontrado", null);
        }

        Usuarios usuario = usuarioOpt.get();

        if (!usuario.getContrasenia().equals(loginDTO.getContrasenia())){
            return new UsuarioLoginResponseDTO(false, "Contraseña incorrecta", null);
        }

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getRol().name()
        );
        return new UsuarioLoginResponseDTO(true, "Login exitoso", responseDTO);
    }
}