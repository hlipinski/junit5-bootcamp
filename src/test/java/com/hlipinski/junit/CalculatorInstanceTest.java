package com.hlipinski.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CalculatorInstanceTest {

	CalculatorInstanceTest() {
		System.out.println("Class created " + Instant.now().toEpochMilli());
	}

	private final Calculator calculator = new Calculator();

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@Test
	@DisplayName("2 / 2 = 1")
	void divideTwoNumbers() {
		assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1");
	}
}