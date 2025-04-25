package com.vitufelipe.api_mysql.controller;

import com.vitufelipe.api_mysql.model.Usuario;
import com.vitufelipe.api_mysql.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }
}