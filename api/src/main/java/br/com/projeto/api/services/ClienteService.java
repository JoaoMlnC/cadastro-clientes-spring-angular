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

	// Método para cadastrar um cliente
	public Cliente cadastrarCliente(ClienteDTO clienteDTO) {
		// Cria um novo objeto Cliente a partir do ClienteDTO
		Cliente cliente = new Cliente();
		cliente.setCnpj(clienteDTO.getCnpj());
		cliente.setRazaoSocial(clienteDTO.getRazaoSocial());
		cliente.setStatus(clienteDTO.getStatus());
		cliente.setUsername(clienteDTO.getUsername());

		// Hash da senha antes de armazenar
		String senhaHash = passwordEncoder.encode(clienteDTO.getPassword());
		cliente.setPassword(senhaHash);

		return clienteRepository.save(cliente);
	}

	public Cliente editarCliente(ClienteDTO clienteDTO) {
		// Busca o cliente existente
		Cliente clienteExistente = clienteRepository.findById(clienteDTO.getId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

		// Atualiza os campos do cliente
		clienteExistente.setUsername(clienteDTO.getUsername());
		clienteExistente.setCnpj(clienteDTO.getCnpj());
		clienteExistente.setRazaoSocial(clienteDTO.getRazaoSocial());
		clienteExistente.setStatus(clienteDTO.getStatus());

		// Atualiza a senha apenas se uma nova senha for fornecida
		if (clienteDTO.getPassword() != null && !clienteDTO.getPassword().isEmpty()) {
			// Se a nova senha for diferente da senha atual (não se deve comparar hashes)
			if (!clienteDTO.getPassword().equals(clienteExistente.getPassword())) {
				if (!passwordEncoder.matches(clienteDTO.getPassword(), clienteExistente.getPassword())) {
					// Se a senha for diferente, atualize com o novo hash
					String senhaHash = passwordEncoder.encode(clienteDTO.getPassword());
					clienteExistente.setPassword(senhaHash);
				}
			}
			// Se a nova senha for a mesma, não faça nada (não atualiza a senha)
		}

		return clienteRepository.save(clienteExistente);
	}

	// Método para buscar cliente por username
	public Optional<Cliente> buscarCliente(String username) {
		return clienteRepository.findByUsername(username);
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		for (Cliente cliente : clientes) {
			// Logando os dados do cliente
			System.out.println(cliente); // ou use um logger para registrar as informações

			// Criando o ClienteDTO e setando as propriedades manualmente
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setUsername(cliente.getUsername());
			clienteDTO.setPassword(cliente.getPassword()); // Opcional, se você precisar da senha
			clienteDTO.setCnpj(cliente.getCnpj());
			clienteDTO.setRazaoSocial(cliente.getRazaoSocial());
			clienteDTO.setStatus(cliente.getStatus());

			// Adiciona o ClienteDTO à lista
			clienteDTOs.add(clienteDTO);
		}

		return clienteDTOs;
	}

	// Método para remover um cliente por ID
	public void removerCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	public List<ClienteDTO> buscarPorRazaoSocial(String razaoSocial) {
		List<Cliente> clientes = clienteRepository.findByRazaoSocial(razaoSocial);
		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		 if (clientes.isEmpty()) {
		        System.out.println("Nenhum cliente encontrado com a razão social: " + razaoSocial);
		        return clienteDTOs; // Retorna uma lista vazia
		    }
		for (Cliente cliente : clientes) {
			// Logando os dados do cliente
			System.out.println(cliente); // ou use um logger para registrar as informações

			// Criando o ClienteDTO e setando as propriedades manualmente
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setUsername(cliente.getUsername());
			clienteDTO.setPassword(cliente.getPassword()); // Opcional, se você precisar da senha
			clienteDTO.setCnpj(cliente.getCnpj());
			clienteDTO.setRazaoSocial(cliente.getRazaoSocial());
			clienteDTO.setStatus(cliente.getStatus());

			// Adiciona o ClienteDTO à lista
			clienteDTOs.add(clienteDTO);
		}

		return clienteDTOs;
	}

	// Método para buscar clientes por CNPJ
	public List<ClienteDTO> buscarPorCnpj(String cnpj) {
		List<Cliente> clientes = clienteRepository.findByCnpj(cnpj);
		List<ClienteDTO> clienteDTOs = new ArrayList<>();

		 if (clientes.isEmpty()) {
		        System.out.println("Nenhum cliente encontrado com o CNPJ: " + cnpj);
		        return clienteDTOs; // Retorna uma lista vazia
		    }
		for (Cliente cliente : clientes) {
			// Logando os dados do cliente
			System.out.println(cliente); // ou use um logger para registrar as informações

			// Criando o ClienteDTO e setando as propriedades manualmente
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(cliente.getId());
			clienteDTO.setUsername(cliente.getUsername());
			clienteDTO.setPassword(cliente.getPassword()); // Opcional, se você precisar da senha
			clienteDTO.setCnpj(cliente.getCnpj());
			clienteDTO.setRazaoSocial(cliente.getRazaoSocial());
			clienteDTO.setStatus(cliente.getStatus());

			// Adiciona o ClienteDTO à lista
			clienteDTOs.add(clienteDTO);
		}

		return clienteDTOs;
	}
}
