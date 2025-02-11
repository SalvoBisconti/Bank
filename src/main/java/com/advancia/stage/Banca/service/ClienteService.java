package com.advancia.stage.Banca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.stage.Banca.model.Cliente;
import com.advancia.stage.Banca.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public Cliente findClienteById(Long idCliente) {
		Optional<Cliente> cliente = clienteRepo.findById(idCliente);
		return  cliente.isPresent() ? cliente.get() : null;
	}
	
	public Cliente verificaUtente(String email, String password) {
		List<Cliente> listaClienti = new ArrayList<>();
		Cliente cliente = clienteRepo.verifyUser(email, password);
		listaClienti.add(cliente);
		return listaClienti.isEmpty() ? null : listaClienti.get(0); 
	}		

}
