package com.advancia.stage.Banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.example.VerificaUtenteRequest;
import com.example.VerificaUtenteResponse;



@Controller
public class LoginController {

    @Autowired
    private WebServiceTemplate webServiceTemplate;
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login"; 
    }
    

    @PostMapping("/login")
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model) {
    	
        // Crea una richiesta per il web service utilizzando i parametri ottenuti
        VerificaUtenteRequest request = new VerificaUtenteRequest();
        request.setEmail(email);
        request.setPassword(password);

        VerificaUtenteResponse response = (VerificaUtenteResponse) webServiceTemplate.marshalSendAndReceive(request);
    
        if (response.getIdCliente() != -1L) {
            model.addAttribute("cliente", response);
            return "redirect:/riservata?idCliente=" + response.getIdCliente();
        } else {
            model.addAttribute("error", "Credenziali non valide. Riprova.");
            return "login";
        }
    } 

} 
