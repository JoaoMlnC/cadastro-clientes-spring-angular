package br.com.projeto.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.api.dto.ClienteDTO;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;

	
	public Cliente cadastrarCliente(ClienteDTO clienteDTO) {
		
		Cliente cliente = new Cliente();
		cliente.setCnpj(clienteDTO.getCnpj());
		cliente.setRazaoSocial(clienteDTO.getRazaoSocial());
		cliente.setStatus(clienteDTO.getStatus());
		cliente.setUsername(clienteDTO.getUsername());

		
		String senhaHash = passwordEncoder.encode(clienteDTO.getPassword());
		cliente.setPassword(senhaHash);

		return clienteRepository.save(cliente);
	}

	public Cliente editarCliente(ClienteDTO clienteDTO) {
		
		Cliente clienteExistente = clienteRepository.findById(clienteDTO.getId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

		
		clienteExistente.setUsername(clienteDTO.getUsername());
		clienteExistente.setCnpj(clienteDTO.getCnpj());
		clienteExistente.setRazaoSocial(clienteDTO.getRazaoSocial());
		clienteExistente.setStatus(clienteDTO.getStatus());

		if (clienteDTO.getPassword() != null && !clienteDTO.getPassword().isEmpty()) {
			if (!clienteDTO.getPassword().equals(clienteExistente.getPassword())) {
				if (!passwordEncoder.matches(clienteDTO.getPassword(), clienteExistente.getPassword())) {
					String senhaHash = passwordEncoder.encode(clienteDTO.getPassword());
					clienteExistente.setPassword(senhaHash);
				}
			}
			
		}

		return clienteRepository.save(clienteExistente);
	}

	public Optional<Cliente> buscarCliente(String username) {
		return clienteRepository.findByUsername(username);
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		for (Cliente cliente : clientes) {
			
			System.out.println(cliente); 

			
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setUsername(cliente.getUsername());
			clienteDTO.setPassword(cliente.getPassword()); 
			clienteDTO.setCnpj(cliente.getCnpj());
			clienteDTO.setRazaoSocial(cliente.getRazaoSocial());
			clienteDTO.setStatus(cliente.getStatus());

			
			clienteDTOs.add(clienteDTO);
		}

		return clienteDTOs;
	}

	
	public void removerCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	public List<ClienteDTO> buscarPorRazaoSocial(String razaoSocial) {
		List<Cliente> clientes = clienteRepository.findByRazaoSocial(razaoSocial);
		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		 if (clientes.isEmpty()) {
		        System.out.println("Nenhum cliente encontrado com a razão social: " + razaoSocial);
		        return clienteDTOs; 
		    }
		for (Cliente cliente : clientes) {
			
			System.out.println(cliente); 

			
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setUsername(cliente.getUsername());
			clienteDTO.setPassword(cliente.getPassword()); 
			clienteDTO.setCnpj(cliente.getCnpj());
			clienteDTO.setRazaoSocial(cliente.getRazaoSocial());
			clienteDTO.setStatus(cliente.getStatus());

			
			clienteDTOs.add(clienteDTO);
		}

		return clienteDTOs;
	}

	
	public List<ClienteDTO> buscarPorCnpj(String cnpj) {
		List<Cliente> clientes = clienteRepository.findByCnpj(cnpj);
		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		 if (clientes.isEmpty()) {
		        System.out.println("Nenhum cliente encontrado com o CNPJ: " + cnpj);
		        return clienteDTOs; 
		    }
		for (Cliente cliente : clientes) {
			System.out.println(cliente);

			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setUsername(cliente.getUsername());
			clienteDTO.setPassword(cliente.getPassword()); 
			clienteDTO.setCnpj(cliente.getCnpj());
			clienteDTO.setRazaoSocial(cliente.getRazaoSocial());
			clienteDTO.setStatus(cliente.getStatus());

			
			clienteDTOs.add(clienteDTO);
		}

		return clienteDTOs;
	}
}
