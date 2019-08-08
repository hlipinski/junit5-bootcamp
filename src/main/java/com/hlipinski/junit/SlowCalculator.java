package com.hlipinski.junit;

class SlowCalculator extends Calculator {
    @Override
    int divide(int a, int b) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.divide(a, b);
    }
}
