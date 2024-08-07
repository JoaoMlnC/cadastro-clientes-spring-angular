package br.com.projeto.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.model.Usuario; // Supondo que você tenha uma entidade Usuario
import br.com.projeto.api.repository.ClienteRepository;
import br.com.projeto.api.repository.UsuarioRepository; // Repositório do Usuário

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Lazy
    private ClienteRepository clienteRepository; // Altere para usar o repositório do Usuário

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuário: " + username);

        // Busca o usuário pelo nome de usuário
        Optional<Cliente> usuarioOpt = clienteRepository.findByUsername(username);
        System.out.println("Usuário encontrado: " + usuarioOpt.isPresent());

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        Cliente cliente = usuarioOpt.get();
        System.out.println("Usuário encontrado: " + cliente.getUsername());
        System.out.println("Senha do usuário: " + cliente.getPassword());

        // Retorna os detalhes do usuário, utilizando o username e a senha
        return new User(cliente.getUsername(), cliente.getPassword(), new ArrayList<>()); // Authorities podem ser adicionadas aqui
    }
}
