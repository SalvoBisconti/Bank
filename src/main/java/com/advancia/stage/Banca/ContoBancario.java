package com.advancia.stage.Banca;

import java.io.IOException;

import com.advancia.stage.Banca.utils.ImportoNonValidoException;
import com.advancia.stage.Banca.utils.ReaderCSVFile;
import com.advancia.stage.Banca.utils.SaldoInsufficienteException;
import com.opencsv.exceptions.CsvValidationException;

public class ContoBancario {

	private int numeroConto;
	private String intestatario;
	private double saldo;

	public ContoBancario() {
	};

	public ContoBancario(int numeroConto, String intestatario, double saldo) {
		super();
		this.numeroConto = numeroConto;
		this.intestatario = intestatario;
		this.saldo = saldo;
	}

	public int getNumeroConto() {
		return numeroConto;
	}

	public void setNumeroConto(int numeroConto) {
		this.numeroConto = numeroConto;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "ContoBancario [numeroConto=" + numeroConto + ", intestatario=" + intestatario + ", saldo=" + saldo
				+ "]";
	}

	public void deposito(double importo) throws ImportoNonValidoException {

		if (importo < 0) {
			throw new ImportoNonValidoException();
		} else {
			this.setSaldo(saldo + importo);
		}
	}

	public void preleva(double importo) throws ImportoNonValidoException, SaldoInsufficienteException {

		if (importo < 0) {
			throw new ImportoNonValidoException();

		} else if (importo > this.getSaldo()) {
			throw new SaldoInsufficienteException();
		} else {
			this.setSaldo(this.saldo - importo);
		}
	}

	public void mostraSaldo() {
		System.out.println("Il saldo è: " + this.getSaldo());
	}

	public static void main(String[] args) {

		ContoBancario conto = new ContoBancario(01, "Pippo", 50000);
		try {
			conto.mostraSaldo();
			conto.deposito(5000);
			conto.mostraSaldo();
			conto.preleva(10000);
			conto.mostraSaldo();
			conto.preleva(30000);
		} catch (ImportoNonValidoException | SaldoInsufficienteException e) {
			System.err.println("Errore: " + e.getMessage());
		}

		try {
			conto.deposito(-5000);
		} catch (ImportoNonValidoException e) {
			System.err.println("Errore: " + e.getMessage());
		}

		// LETTURA CSV
		final String FILE_PATH = "/Users/salvo/eclipse-workspace-advancia/Banca/operation.csv";
		ReaderCSVFile readerCsv = new ReaderCSVFile();
		
		try {
			readerCsv.eseguiOperazioni(FILE_PATH);

		} catch (CsvValidationException e) {
			System.out.println("Errore lettura CSV: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Path file errata o file insesistente");
		}
		
		

	}
}