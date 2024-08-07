package br.com.projeto.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.repository.ClienteRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes") // Define um prefixo para as rotas do controller
public class Controller {

    @Autowired
    private ClienteRepository clienteRepository;

    // Rota para listar todos os clientes
    @GetMapping("/listarClientes")
    public Iterable<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Rota para cadastrar um novo cliente
    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Rota para editar um cliente existente
    @PutMapping
    public Cliente editar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Rota para remover um cliente por ID
    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id) {
        clienteRepository.deleteById(id);
    }
}
