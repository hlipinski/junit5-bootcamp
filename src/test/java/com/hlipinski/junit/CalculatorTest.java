package com.hlipinski.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class CalculatorTest {

	private final Calculator calculator = new Calculator();

//	@BeforeClass
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all tests");
	}

//	@Before
	@BeforeEach
	void beforeEach() {
		System.out.println("Before each test");
	}

//	@After
	@AfterEach
	void afterEach() {
		System.out.println("After each test");
	}

//	@AfterClass
	@AfterAll
	static void afterAll() {
		System.out.println("After all tests");
	}

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

//	Pragmatist's JUnit Params
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    1,   1",
			"1,    2,   3",
			"49,  51, 100",
			"1,  100, 101"
	})
	void add(int first, int second, int expectedResult) {
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

	@Test
//	@Test(expected = IllegalArgumentException.class)
	@DisplayName("Should not divide by 0")
	void divideBy0() {
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(1, 0));
	}

	@Test
	@DisplayName("Should divide correctly (stop on first fail)")
	void divideTwoNumbersWithFailInSequence() {
		assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1");
		assertEquals(2, calculator.divide(2, 1), "2 / 1 should equal 1");
		assertEquals(512, calculator.divide(1024, 2), "1024 / 2 should equal 512");
		assertEquals(0, calculator.divide(0, 2), "0 / 2 should equal 0");
	}

	@Test
	@DisplayName("Should divide correctly (check all)")
	void divideTwoNumbersWithAllFail() {
		assertAll(
				() -> assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1"),
				() -> assertEquals(2, calculator.divide(2, 1), "2 / 1 should equal 1"),
				() -> assertEquals(512, calculator.divide(1024, 2), "1024 / 2 should equal 512"),
				() -> assertEquals(0, calculator.divide(0, 2), "0 / 2 should equal 0")
		);
	}

	@Test
//	@Ignore
	@Disabled("Disabled test")
	void disabled() {
		fail();
	}

	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class Multiple_operation_tests {

		@Test
		void should_multiply_correctly() {
			assertEquals(64, calculator.multiply(8, 8), "8 * 8 should equal 64");
		}
	}

	@Test
	void divideNoLongerThan() {
		Calculator slowCalc = new SlowCalculator();
		assertTimeout(Duration.ofMillis(3), () -> slowCalc.divide(2, 2));

//		New thread:
		assertTimeoutPreemptively(Duration.ofMillis(3), () -> slowCalc.divide(2, 2));
	}

	@RepeatedTest(10)
	void divideMultipleTimes() {
		assertEquals(1, calculator.divide(2, 2), "2 / 2 should equal 1");
	}

	@Test
	@DisplayName("Test divide operation only if b != 0")
	void divideWithAssumption() {
		int a = 2;
		int b = 0;
		assumingThat(
				b != 0,
				() -> assertEquals(1, calculator.divide(a, b), () -> a + " / " + b + " should equal " + a / b)
		);

		assumeTrue(
				"DEV".equals(System.getenv("ENV")),
				() -> "Aborting test: not on developer workstation"
		);
	}
}