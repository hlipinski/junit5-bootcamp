package com.hlipinski.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    1,   1",
			"1,    2,   3",
			"49,  51, 100",
			"1,  100, 101"
	})
	void add(int first, int second, int expectedResult) {
		Calculator calculator = new Calculator();
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

	@Test
	@DisplayName("Should not divide by 0")
	void divideBy0() {
		Calculator calculator = new Calculator();
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(1, 0));
	}

	@Test
	@DisplayName("Should divide correctly (check all)")
	void divideTwoNumbers() {
		Calculator calculator = new Calculator();
		assertAll(
				() -> assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1"),
				() -> assertEquals(2, calculator.divide(2, 1), "2 / 1 should equal 1"),
				() -> assertEquals(512, calculator.divide(1024, 2), "1024 / 2 should equal 512"),
				() -> assertEquals(0, calculator.divide(0, 2), "0 / 2 should equal 0")
		);
	}

	@Test
	@DisplayName("Should divide correctly (stop on first fail)")
	void divideTwoNumbersWithFail() {
		Calculator calculator = new Calculator();
		assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1");
		assertEquals(2, calculator.divide(2, 1), "2 / 1 should equal 1");
		assertEquals(512, calculator.divide(1024, 2), "1024 / 2 should equal 512");
		assertEquals(0, calculator.divide(0, 2), "0 / 2 should equal 0");
	}
}