package com.advancia.stage.Banca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.stage.Banca.model.ContoCorrente;
import com.advancia.stage.Banca.model.Transazione;
import com.advancia.stage.Banca.repository.ContoCorrenteRepository;
import com.advancia.stage.Banca.repository.TransazioneRepository;
import com.advancia.stage.Banca.utils.ImportoNonValidoException;
import com.advancia.stage.Banca.utils.SaldoInsufficienteException;

@Service
public class ContoCorrenteService {

	@Autowired
	private ContoCorrenteRepository ccRepo;

	@Autowired
	private TransazioneRepository transRepo;

	public void deposito(Long idConto, double importo) throws ImportoNonValidoException {
		ContoCorrente conto = ccRepo.findById(idConto).orElseThrow(() -> new RuntimeException("Conto non trovato"));

		if (importo < 0) {
			Transazione transazione = new Transazione("DEPOSITO", importo, new Date(), "FALLITA", conto);
			transRepo.save(transazione);
			throw new ImportoNonValidoException();
		}

		conto.setSaldo(conto.getSaldo() + importo);
		ccRepo.save(conto);

		Transazione transazione = new Transazione("DEPOSITO", importo, new Date(), "COMPLETATA", conto);
		transRepo.save(transazione);

	}

	public void preleva(Long idConto, double importo) throws ImportoNonValidoException, SaldoInsufficienteException {

		ContoCorrente conto = ccRepo.findById(idConto).orElseThrow(() -> new RuntimeException("Conto non trovato"));

		if (importo < 0) {
			Transazione transazione = new Transazione("PRELIEVO", importo, new Date(), "FALLITA", conto);
			transRepo.save(transazione);
			throw new ImportoNonValidoException();
		}

		if (importo > conto.getSaldo()) {
			Transazione transazione = new Transazione("PRELIEVO", importo, new Date(), "FALLITA", conto);
			transRepo.save(transazione);
			throw new SaldoInsufficienteException();
		} else {
			conto.setSaldo(conto.getSaldo());
			ccRepo.save(conto);
			Transazione transazione = new Transazione("PRELIEVO", importo, new Date(), "COMPLETATA", conto);
			transRepo.save(transazione);
		}
	}

	public double mostraSaldo(Long idConto) {
		ContoCorrente conto = ccRepo.findById(idConto).orElseThrow(() -> new RuntimeException("Conto non trovato"));
		return conto.getSaldo();
	}
}
