package com.yegor.calculator_task2.actions;

/**
 * This class contains methods that represent arithmetic actions.
 * Created by YegorKost on 13.02.2017.
 */
public class Action {

    public double addition(double a, double b) throws ArithmeticException {
        Double result = a + b;
        if (result.equals(Double.NaN)) {
            throw new ArithmeticException("The result of addition is " + result);
        }
        return result;
    }

    public double subtraction(double a, double b) throws ArithmeticException {
        Double result = a - b;
        if (result.equals(Double.NaN)) {
            throw new ArithmeticException("The result of subtraction is " + result);
        }
        return result;
    }

    public double multiplication(double a, double b) throws ArithmeticException {
        Double result = a * b;
        if (result.equals(Double.NaN)) {
            throw new ArithmeticException("The result of multiplication is " + result);
        }
        return result;
    }

    public double division(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division to zero");
        }
        Double result = a / b;
        if (result.equals(Double.NaN)) {
            throw new ArithmeticException("The result of division is " + result);
        }
        return result;
    }

    public double squareRoot(double a) {
        Double result = Math.sqrt(a);
        if (result.equals(Double.NaN)) {
            throw new ArithmeticException("The argument of square root is " + result);
        }
        return result;
    }

    public double exponentiation(double a, double b) {
        Double result = Math.pow(a, b);
        if (result.equals(Double.NaN)) {
            throw new ArithmeticException("The result is " + result);
        }
        return result;
    }

}
