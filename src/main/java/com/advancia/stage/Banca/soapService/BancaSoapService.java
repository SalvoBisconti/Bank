package com.advancia.stage.Banca.soapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.advancia.stage.Banca.model.Cliente;
import com.advancia.stage.Banca.service.ClienteService;
import com.advancia.stage.Banca.service.ContoCorrenteService;
import com.example.DepositoRequest;
import com.example.DepositoResponse;
import com.example.PrelevaRequest;
import com.example.PrelevaResponse;
import com.example.VerificaUtenteRequest;
import com.example.VerificaUtenteResponse;

@Endpoint
public class BancaSoapService {

	private static final String NAMESPACE_URI = "http://www.example.com";

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContoCorrenteService contoCorrenteService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "VerificaUtenteRequest")
	@ResponsePayload
	public VerificaUtenteResponse verificaUtente(@RequestPayload VerificaUtenteRequest request) {
		Cliente cliente = clienteService.verificaUtente(request.getEmail(), request.getPassword());
		VerificaUtenteResponse response = new VerificaUtenteResponse();
		if (cliente != null) {
			response.setIdCliente(cliente.getId_clnt());
			response.setNome(cliente.getNome_clnt());
			response.setCognome(cliente.getCgn_clnt());
			response.setEmail(cliente.getEmail_clnt());
		} else {
			response.setIdCliente(-1L);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "PrelevaRequest")
	@ResponsePayload
	public PrelevaResponse preleva(@RequestPayload PrelevaRequest request) {
		PrelevaResponse response = new PrelevaResponse();
		try {
			contoCorrenteService.preleva(request.getIdConto(), request.getImporto());
			response.setEsito("Accettato");
		} catch (Exception e) {
			response.setEsito(e.getMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "DepositoRequest")
	@ResponsePayload
	public DepositoResponse deposito(@RequestPayload DepositoRequest request) {
		DepositoResponse response = new DepositoResponse();
		try {
			contoCorrenteService.deposito(request.getIdConto(), request.getImporto());
			response.setEsito("Accettato");
		} catch (Exception e) {
			response.setEsito(e.getMessage());
		}
		return response;
	}

}