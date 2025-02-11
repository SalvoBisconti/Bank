package com.advancia.stage.Banca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.advancia.stage.Banca.service.ContoCorrenteService;
import com.advancia.stage.Banca.utils.ImportoNonValidoException;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private ContoCorrenteService contoCorrenteService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Long contoId = 1L; 
        
        try {
            System.out.println("Eseguo un deposito sul conto con ID: " + contoId);
            System.out.println(contoCorrenteService.mostraSaldo(contoId));
            contoCorrenteService.deposito(contoId, 500.0);
            double saldo = contoCorrenteService.mostraSaldo(contoId);
            System.out.println("Deposito completato! Saldo attuale: " + saldo);
        } catch (ImportoNonValidoException e) {
            System.err.println("Errore: Importo non valido.");
        } catch (RuntimeException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }
}

