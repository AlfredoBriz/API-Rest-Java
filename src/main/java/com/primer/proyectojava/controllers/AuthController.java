package com.primer.proyectojava.controllers;

import com.primer.proyectojava.dao.UsuarioDao;
import com.primer.proyectojava.models.Usuario;
import com.primer.proyectojava.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//----------------------------------------------------------------------------------------------------------------------

@RestController
public class AuthController {


    @Autowired
    private UsuarioDao usuarioDao;


    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioDao.verificarCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }




}
