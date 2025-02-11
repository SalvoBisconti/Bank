package com.advancia.stage.Banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancia.stage.Banca.model.Cliente;
import com.advancia.stage.Banca.service.ClienteService;

@Controller
public class LoginController {
	
	@Autowired
	private ClienteService cs;

    @GetMapping("/login")
    public String login(Model model) {
        return "login"; 
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model) {
    		Cliente cliente = cs.verificaUtente(email, password);
    		
    		if(cliente != null) {
    			model.addAttribute("cliente", cliente);
    			return "redirect:/riservata?idCliente=" + cliente.getId_clnt();
    		} else {
    			 model.addAttribute("error", "Credenziali non valide. Riprova.");
    		        return "login";
    		}
        
    }
}
