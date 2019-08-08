package com.hlipinski.junit;

class SlowCalculator extends Calculator {
    @Override
    int divide(int a, int b) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) { }
        return super.divide(a, b);
    }
}
