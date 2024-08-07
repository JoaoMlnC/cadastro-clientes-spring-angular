package br.com.projeto.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.projeto.api.dto.ClienteDTO;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.services.ClienteService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class Controller {

    @Autowired
    private ClienteService clienteService;

    // Rota para listar todos os clientes
    @GetMapping("/listarClientes")
    public List<ClienteDTO> listar() {
        return clienteService.findAll();
    }

    // Rota para cadastrar um novo cliente
    @PostMapping("/cadastrar")
    public ClienteDTO cadastrar(@RequestBody ClienteDTO clienteDTO) {
        // Chama o serviço para cadastrar o cliente
        Cliente cliente = clienteService.cadastrarCliente(clienteDTO);
        // Retorna o ClienteDTO sem a senha
        return new ClienteDTO(cliente.getId(), cliente.getUsername(), 
                              null, // Não retornamos a senha
                              null, // Não retornamos a confirmPassword
                              cliente.getCnpj(),
                              cliente.getRazaoSocial(), 
                              cliente.getStatus());
    }

    // Rota para editar um cliente existente
    @PutMapping("/editar")
    public ClienteDTO editar(@RequestBody ClienteDTO clienteDTO) {
        // Passa o DTO para o serviço para editar o cliente
        Cliente cliente = clienteService.editarCliente(clienteDTO);
        // Retorna o ClienteDTO sem a senha
        return new ClienteDTO(cliente.getId(), cliente.getUsername(), 
                              null, // Não retornamos a senha
                              null, // Não retornamos a confirmPassword
                              cliente.getCnpj(),
                              cliente.getRazaoSocial(), 
                              cliente.getStatus());
    }

    // Rota para remover um cliente por ID
    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable long id) {
        clienteService.removerCliente(id);
    }
    
    @GetMapping("/buscarPorRazaoSocial")
    public List<ClienteDTO> buscarPorRazaoSocial(@RequestParam String razaoSocial) {
        return clienteService.buscarPorRazaoSocial(razaoSocial);
    }

    // Rota para buscar clientes por CNPJ
    @GetMapping("/buscarPorCnpj")
    public List<ClienteDTO> buscarPorCnpj(@RequestParam String cnpj) {
        return clienteService.buscarPorCnpj(cnpj);
    }
}
