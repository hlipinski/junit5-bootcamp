package com.hlipinski.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class CalculatorOrderedTest {

	private final Calculator calculator = new Calculator();

	@Test
	@DisplayName("1 + 1 = 2")
//	@Order(1)
	void addsTwoNumbers() {
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@Test
	@DisplayName("2 / 2 = 1")
//	@Order(1)
	void divideTwoNumbers() {
		assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1");
	}
}