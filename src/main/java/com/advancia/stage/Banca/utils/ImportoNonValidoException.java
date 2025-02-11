package com.advancia.stage.Banca.utils;

public class ImportoNonValidoException extends Exception {

	public ImportoNonValidoException() {
		super("L'importo non può essere negativo");
	}

}
