package com.advancia.stage.Banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.advancia.stage.Banca.model.Cliente;
import com.advancia.stage.Banca.service.ClienteService;
import com.advancia.stage.Banca.service.ContoCorrenteService;
import com.advancia.stage.Banca.utils.ImportoNonValidoException;
import com.advancia.stage.Banca.utils.SaldoInsufficienteException;
import com.example.DepositoRequest;
import com.example.DepositoResponse;
import com.example.PrelevaRequest;
import com.example.PrelevaResponse;

@Controller
public class RiservataController {

	@Autowired
	private ClienteService cS;

	@Autowired
	private ContoCorrenteService ccS;

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@GetMapping("/riservata")
	public String riservata(Model model, @RequestParam Long idCliente) {
		Cliente cliente = cS.findClienteById(idCliente);
		model.addAttribute("cliente", cliente);
		return "riservata";
	};

	@PostMapping("/riservata")
	public String eseguiTransazione(@RequestParam String operazione, @RequestParam double importo,
			@RequestParam Long idConto, Model model) {

		Cliente cliente = cS.findClienteById(idConto);

		try {
			if ("prelievo".equalsIgnoreCase(operazione)) {

				PrelevaRequest request = new PrelevaRequest();
				request.setIdConto(idConto);
				request.setImporto(importo);

				PrelevaResponse response = (PrelevaResponse) webServiceTemplate.marshalSendAndReceive(request);

				if (response.getEsito().equals("L'importo non può essere negativo")) {
					throw new ImportoNonValidoException();
				} else if (response.getEsito().equals("Saldo insufficiente")) {
					throw new SaldoInsufficienteException();
				}

			} else if ("deposito".equalsIgnoreCase(operazione)) {
				DepositoRequest request = new DepositoRequest();
				request.setIdConto(idConto);
				request.setImporto(importo);

				DepositoResponse response = (DepositoResponse) webServiceTemplate.marshalSendAndReceive(request);

				if (response.getEsito().equals("L'importo non può essere negativo")) {
					throw new ImportoNonValidoException();
				}

			}
			model.addAttribute("message", "Transazione eseguita con successo.");

		} catch (RuntimeException e) {
			model.addAttribute("message", "Errore nella transazione");
		} catch (ImportoNonValidoException e) {
			model.addAttribute("message", "Importo non valido");
			e.printStackTrace();
		} catch (SaldoInsufficienteException e) {
			model.addAttribute("message", "Saldo insufficiente");
			e.printStackTrace();
		}

		model.addAttribute("cliente", cliente);

		return "riservata";
	}

}