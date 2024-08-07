package br.com.projeto.api.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.projeto.api.model.Cliente;



public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByUsername(String username);
    List<Cliente> findByRazaoSocial(String razaoSocial);
    List<Cliente> findByCnpj(String cnpj);
}

