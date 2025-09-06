package com.Springboot.aplicationweb.Usuario.Servicios;

import com.Springboot.aplicationweb.Usuario.Dto.UsuarioCreateDTO;
import com.Springboot.aplicationweb.Usuario.Dto.UsuarioUpdateDTO;
import com.Springboot.aplicationweb.Usuario.Model.Usuarios;
import com.Springboot.aplicationweb.Usuario.Repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Aquí ya manejamos la inyección de dependencias con @Autowired
    @Autowired
    private UsuariosRepositorio repo_usuarios;

    public List<Usuarios> ListaUsuarios(){
        return repo_usuarios.findAll();
    }

    public Optional<Usuarios> BuscarId(int id){
        return repo_usuarios.findById(id);
    }

    public Usuarios Crear(UsuarioCreateDTO usuario){
        Usuarios u = new Usuarios();
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setCorreo(usuario.getCorreo());
        return repo_usuarios.save(u);
    }

    public Optional<Usuarios> Actualizar(int id, UsuarioUpdateDTO actualiza){
        return repo_usuarios.findById(id).map(usuarioExiste -> {
            usuarioExiste.setNombre(actualiza.getNombre());
            usuarioExiste.setApellido(actualiza.getApellido());
            usuarioExiste.setCorreo(actualiza.getCorreo());
            return repo_usuarios.save(usuarioExiste);
        });
    }

    public void Eliminar(int id){
        repo_usuarios.deleteById(id);
    }
}