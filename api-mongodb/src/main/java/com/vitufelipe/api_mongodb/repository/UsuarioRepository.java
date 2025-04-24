package com.vitufelipe.api_mongodb.repository;

import com.vitufelipe.api_mongodb.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
