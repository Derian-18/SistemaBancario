package com.Springboot.aplicationweb.Usuario.Servicios;

import com.Springboot.aplicationweb.CuentaBancaria.Repositorios.CuentaBancariaRepositorio;
import com.Springboot.aplicationweb.CuentaBancaria.Servicios.CuentaBancariaService;
import com.Springboot.aplicationweb.Usuario.Dto.*;
import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import com.Springboot.aplicationweb.Usuario.Repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    // Aquí ya manejamos la inyección de dependencias con @Autowired
    @Autowired
    private UsuariosRepositorio repo_usuarios;

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    // Lo siguiente sirve para encriptar las contrasenias lo cual se hará uso de esto después
    //@Autowired
    //prívate PasswordEncoder contraseniaEncoder;

    // Aqui lo que se hace es que muestren los usuarios
    public List<UsuarioGetDTO> ListaUsuarios(){
        return repo_usuarios.findAll().stream()
                .map(u -> new UsuarioGetDTO(
                        u.getUsuarioId(),
                        u.getNombre(),
                        u.getApellido(),
                        u.getCorreo(),
                        u.getRol().name(),
                        (u.getCuentas_bancarias() != null) ? u.getCuentas_bancarias().getNumeroCuenta().toString() : null
                ))
                .collect(Collectors.toList());
    }

    // Aqui los usuarios se muestran pero solo el id elegido
    public Optional<UsuarioGetDTO> BuscarId(int id){
        Usuarios u = repo_usuarios.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return Optional.of(new UsuarioGetDTO(
                u.getUsuarioId(),
                u.getNombre(),
                u.getApellido(),
                u.getCorreo(),
                u.getRol().name(),
                (u.getCuentas_bancarias() != null) ? u.getCuentas_bancarias().getNumeroCuenta() : null
        ));
    }

    // Este se va a usar para registro de usuarios
    public Usuarios Crear(UsuarioRegisterDTO usuario){
        Usuarios u = new Usuarios();
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setCorreo(usuario.getCorreo());
        u.setContrasenia(usuario.getContrasenia());

        // Aquí guardamos el usuario
        Usuarios  usuarioGuardado = repo_usuarios.save(u);

        // Aquí creamos automáticamente la cuenta bancaria
        cuentaBancariaService.crearCuentaUsuario(usuarioGuardado);

        return usuarioGuardado;
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

    /* Aquí ira el método para poder iniciar sesión*/
    // Por cuestiones de simplicidad se usara .equals para las contrasenias

    // Aquí se está usando el método que esta en el repositorio
    // Recordar que aqui es el DTO de respuesta al LOGIN
    public UsuarioLoginResponseDTO validarLogin(UsuarioLoginDTO loginDTO){
        Optional<Usuarios> usuarioOpt = repo_usuarios.findByCorreo(loginDTO.getCorreo());

        if (usuarioOpt.isEmpty()){
            return new UsuarioLoginResponseDTO(false, "Usuario no encontrado", null);
        }

        Usuarios usuario = usuarioOpt.get();

        if (!usuario.getContrasenia().equals(loginDTO.getContrasenia())){
            return new UsuarioLoginResponseDTO(false, "Contraseña incorrecta", null);
        }

        UsuarioGetDTO responseDTO = new UsuarioGetDTO(
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getRol().name(),
                (usuario.getCuentas_bancarias() != null) ? usuario.getCuentas_bancarias().getNumeroCuenta() : null
        );
        return new UsuarioLoginResponseDTO(true, "Login exitoso", responseDTO);
    }
}