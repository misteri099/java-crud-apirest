package com.apirest.apirest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.entidades.Usuario;
import com.apirest.apirest.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class controladorUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obteneUsuarioId(@PathVariable Integer id){
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no registrado con el ID: " + id));
    }
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario editUsuario(@PathVariable int id,@RequestBody Usuario detalleUsuario){
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no registrado con el ID: " + id));

        usuario.setDni(detalleUsuario.getDni());
        usuario.setNombre(detalleUsuario.getNombre());
        usuario.setApellido(detalleUsuario.getApellido());
        usuario.setTelefono(detalleUsuario.getTelefono());

        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no registrado con el ID: " + id));

        usuarioRepository.delete(usuario);

        return "El usuario con el ID: " + id + " ha sido eliminado correctamente.";
    }
}
