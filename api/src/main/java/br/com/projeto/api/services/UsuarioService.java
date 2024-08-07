package br.com.projeto.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Usuario;
import br.com.projeto.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Método para registrar um novo usuário
	public Usuario cadastrarUsuario(Usuario usuario) {
		// Hash da senha antes de salvar
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	// Método para encontrar um usuário por nome de usuário
	public Optional<Usuario> encontrarPorUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	// Outros métodos relacionados a usuários podem ser adicionados aqui
}
