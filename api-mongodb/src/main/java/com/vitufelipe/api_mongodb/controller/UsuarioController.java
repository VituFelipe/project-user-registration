package com.vitufelipe.api_mongodb.controller;

import com.vitufelipe.api_mongodb.model.Usuario;
import com.vitufelipe.api_mongodb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:3000"})
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }
}
