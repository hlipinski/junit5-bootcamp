package com.hlipinski.junit;

class Calculator {

	int add(int a, int b) {
		return a + b;
	}

	int divide(int a, int b) {
		if (b == 0) throw new IllegalArgumentException("Divider must not be 0");
		return a / b;
	}
}
