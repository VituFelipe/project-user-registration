package com.vitufelipe.api_mysql.repository;

import com.vitufelipe.api_mysql.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
