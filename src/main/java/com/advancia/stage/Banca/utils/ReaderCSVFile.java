package com.advancia.stage.Banca.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.advancia.stage.Banca.ContoBancario;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ReaderCSVFile {

	Map<Integer, ContoBancario> contiBancari = new HashMap<>();

	public void eseguiOperazioni(String filePath) throws IOException, CsvValidationException {
		try {
			FileReader fileReader = new FileReader(filePath);
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRecord;
			csvReader.readNext();

			while ((nextRecord = csvReader.readNext()) != null) {


				int numeroConto = Integer.parseInt(nextRecord[0]);
				String intestatarioConto = nextRecord[1];
				double saldo = Double.parseDouble(nextRecord[2]);
				String tipoOperazione = nextRecord[3];
				double importo = Double.parseDouble(nextRecord[4]);

				ContoBancario conto = new ContoBancario(numeroConto, intestatarioConto, saldo);
				contiBancari.put(numeroConto, conto);

				System.out.println("Operazione su conto di: " + intestatarioConto);

				try {

					if ("deposita".equals(tipoOperazione)) {
						conto.deposito(importo);
						System.out.println("Deposito effettuato. Nuovo saldo: " + conto.getSaldo());
					} else if ("preleva".equals(tipoOperazione)) {
						conto.preleva(importo);
						System.out.println("Prelievo effettuato. Nuovo saldo: " + conto.getSaldo());
					}
				} catch (ImportoNonValidoException | SaldoInsufficienteException e) {
					System.err.println("Errore durante l'operazione: " + e.getMessage());
				}

				System.out.println("-----------------------------------");
			}

		} catch (NumberFormatException e) {
			System.err.println("Errore nella validazione del CSV: " + e.getMessage());
			throw e;

		} catch (IOException e) {
			System.err.println("Errore di lettura del file CSV: " + e.getMessage());
			throw e;
		}
	}

}
