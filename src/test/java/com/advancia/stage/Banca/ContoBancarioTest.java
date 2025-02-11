package com.advancia.stage.Banca;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.advancia.stage.Banca.utils.ImportoNonValidoException;
import com.advancia.stage.Banca.utils.SaldoInsufficienteException;

class ContoBancarioTest {

	ContoBancario tester;

	@BeforeEach
	void setup() {
		tester = new ContoBancario(01, "Pippo", 500);
	}

	static double[] negativeNumber() {
		return new double[] { -100, -200, -300, -400, -2038473 };
	}

	static double[] withdrawNumber() {
		return new double[] { 500, 2000, 3000, 4000, 2038473 };
	}

	@ParameterizedTest
	@MethodSource(value = "negativeNumber")
	void testPrelevaWithInvalidNumber(double data) {
		assertThrows(ImportoNonValidoException.class, () -> tester.preleva(data));
	}

	@Test
	void testPrelevaWithZeroValue() {
		double zero = 0;
		assertThrows(ImportoNonValidoException.class, () -> tester.preleva(zero));
	}

	@ParameterizedTest
	@MethodSource(value = "withdrawNumber")
	void testPrelevaWithInsufficientBalance(double data) {
		assertThrows(SaldoInsufficienteException.class, () -> tester.preleva(2000));
	}

	@ParameterizedTest
	@MethodSource(value = "negativeNumber")
	void testDepositoWithInvalidNumber(double data) {
		assertThrows(ImportoNonValidoException.class, () -> tester.deposito(data));
	}

}