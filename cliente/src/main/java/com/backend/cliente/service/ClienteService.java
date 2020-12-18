package com.backend.cliente.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.cliente.models.Cliente;
import com.backend.cliente.repository.ClienteRepository;
import com.backend.cliente.repository.TelefoneRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	/* @Autowired
	EnderecoRepository enderecoRepository; */

	public void save(Cliente cliente) 
	{
		Cliente client = new Cliente();
		client.setNome(cliente.getNome());
		client.setCnpjCpf(cliente.getCnpjCpf());
		
		clienteRepository.save(client);
		
		/* 
		  List<Telefone> telefones = new ArrayList<>();
		telefones.addAll(cliente.getTelefones());
		client.getTelefones().addAll(cliente.getTelefones());
		telefoneRepository.saveAll(telefones); 
		
		*/
		
		/* 
		  List<Endereco> enderecos = new ArrayList<>();
		enderecos.addAll(cliente.getEnderecos());
		client.getEnderecos().addAll(cliente.getEnderecos());
		enderecoRepository.saveAll(enderecos);
		
		*/
	}
}
