package com.primer.proyectojava.controllers;

//----------------------------------------------------------------------------------------------------------------------

import com.primer.proyectojava.dao.UsuarioDao;
import com.primer.proyectojava.models.Usuario;
import com.primer.proyectojava.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------

@RestController // Esta clase es un controlador
public class UsuarioController {


    @Autowired
    private UsuarioDao usuarioDao;


    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        if (!validarToken(token)) { return null; } // Error

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);// Error
        return usuarioId != null;
    }

//----------------------------------------------------------------------------------------------------------------------


    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id) {
        usuarioDao.eliminar(id);
    }


//----------------------------------------------------------------------------------------------------------------------


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }


//----------------------------------------------------------------------------------------------------------------------

}

























