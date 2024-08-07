package br.com.projeto.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projeto.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
}
