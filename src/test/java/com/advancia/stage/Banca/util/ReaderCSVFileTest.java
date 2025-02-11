package com.advancia.stage.Banca.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.advancia.stage.Banca.utils.ReaderCSVFile;

class ReaderCSVFileTest {

	private ReaderCSVFile readerCSVFile;

	private final String invalidCsv = "/Users/salvo/eclipse-workspace-advancia/Banca/operationTest.csv";

	@BeforeEach
	void setUp() {
		readerCSVFile = new ReaderCSVFile();
	}

	@Test
	void testFileNotExist() {
		String wrongPath = "wrongPath.csv";
		assertThrows(IOException.class, () -> {
			readerCSVFile.eseguiOperazioni(wrongPath);
		});
	}

	@Test
	void testCsvIncorrectFormat() {
		assertThrows(NumberFormatException.class, () -> {
			readerCSVFile.eseguiOperazioni(invalidCsv);
		});

	}
}
