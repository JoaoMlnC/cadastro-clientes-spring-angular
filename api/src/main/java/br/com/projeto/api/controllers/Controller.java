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

   
    @GetMapping("/listarClientes")
    public List<ClienteDTO> listar() {
        return clienteService.findAll();
    }

   
    @PostMapping("/cadastrar")
    public ClienteDTO cadastrar(@RequestBody ClienteDTO clienteDTO) {
       
        Cliente cliente = clienteService.cadastrarCliente(clienteDTO);
        return new ClienteDTO(cliente.getId(), cliente.getUsername(), 
                              null,
                              null, 
                              cliente.getCnpj(),
                              cliente.getRazaoSocial(), 
                              cliente.getStatus());
    }

    
    @PutMapping("/editar")
    public ClienteDTO editar(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.editarCliente(clienteDTO);
        return new ClienteDTO(cliente.getId(), cliente.getUsername(), 
                              null, 
                              null, 
                              cliente.getCnpj(),
                              cliente.getRazaoSocial(), 
                              cliente.getStatus());
    }

    
    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable long id) {
        clienteService.removerCliente(id);
    }
    
    @GetMapping("/buscarPorRazaoSocial")
    public List<ClienteDTO> buscarPorRazaoSocial(@RequestParam String razaoSocial) {
        return clienteService.buscarPorRazaoSocial(razaoSocial);
    }

   
    @GetMapping("/buscarPorCnpj")
    public List<ClienteDTO> buscarPorCnpj(@RequestParam String cnpj) {
        return clienteService.buscarPorCnpj(cnpj);
    }
}
