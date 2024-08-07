package br.com.projeto.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.repository.ClienteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuário: " + username);

        // Busca o cliente pelo nome de usuário
        Optional<Cliente> clienteOpt = clienteRepository.findByUsername(username.toLowerCase());
        System.out.println("Cliente encontrado: " + clienteOpt.isPresent());
        


        if (clienteOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        Cliente cliente = clienteOpt.get();
        System.out.println("Cliente encontrado: " + cliente.getUsername());
        System.out.println("Senha do cliente: " + cliente.getPassword());

        return new User(cliente.getUsername(), cliente.getPassword(), new ArrayList<>());
    }
}

