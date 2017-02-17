package com.yegor.calculator_task2.actions;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by YegorKost on 13.02.2017.
 */
public class ActionTest {
    private Action action = new Action();

    @Test
    public void additionTest() throws Exception {
        assertEquals(123.4567890, action.addition(123, 0.4567890), 0);
        assertEquals(123, action.addition(123.4567890, -0.4567890), 0);
        assertEquals(0.0, action.addition(0.0, 0.0), 0);
        assertEquals(0.0, action.addition(123.4567890, -123.4567890), 0);
    }

    @Test(expected = ArithmeticException.class)
    public void additionTest1() throws Exception {
        action.addition(123, Double.NaN);
        action.addition(Double.NaN, 123);
    }

    @Test
    public void subtractionTest() throws Exception {
        assertEquals(123, action.subtraction(123.4567890, 0.4567890), 0);
        assertEquals(123.4567890, action.subtraction(123, -0.4567890), 0);
        assertEquals(0.0, action.subtraction(0.0, 0.0), 0);
        assertEquals(0.0, action.subtraction(123.4567890, 123.4567890), 0);
    }

    @Test(expected = ArithmeticException.class)
    public void subtractionTest1() throws Exception {
        action.subtraction(123, Double.NaN);
        action.subtraction(Double.NaN, 123);
    }

    @Test
    public void multiplicationTest() throws Exception {
        assertEquals(1, action.multiplication(1/3d, 3), 0);
        assertEquals(-1, action.multiplication(1/3d, -3), 0);
        assertEquals(0, action.multiplication(0, 3), 0);
        assertEquals(0, action.multiplication(0, -3), 0);
        assertEquals(0, action.multiplication(0, -0), 0);
    }

    @Test(expected = ArithmeticException.class)
    public void multiplicationTest1() throws Exception {
        action.multiplication(3, Double.NaN);
        action.multiplication(Double.NaN, 3);
    }

    @Test
    public void divisionTest() throws Exception {
        assertEquals(1/3d, action.division(1, 3), 0);
        assertEquals(-1/3d, action.division(1, -3), 0);
        assertEquals(0, action.division(0, 3), 0);
    }

    @Test(expected = ArithmeticException.class)
    public void divisionTest1() throws Exception {
        action.division(3, 0);
        action.division(3, -0);
        action.division(0, 0);
        action.division(Double.NaN, 3);
        action.division(3, Double.NaN);

    }

    @Test
    public void squareRootTest() throws Exception {
        assertEquals(3, action.squareRoot(9), 0);
        assertEquals(0, action.squareRoot(0), 0);
    }

    @Test(expected = ArithmeticException.class)
    public void squareRootTest1() throws Exception {
        action.squareRoot(-1);
        action.squareRoot(Double.NaN);
    }

    @Test
    public void exponentiationTest() throws Exception {
        assertEquals(0.125d, action.exponentiation(0.5, 3), 0);
        assertEquals(0.5, action.exponentiation(0.5, 1), 0);
        assertEquals(0, action.exponentiation(0, 3), 0);
        assertEquals(1, action.exponentiation(3, 0), 0);
        assertEquals(1/27d , action.exponentiation(3, -3), 0);
        assertEquals(-27 , action.exponentiation(-3, 3), 0);
        assertEquals(9 , action.exponentiation(-3, 2), 0);
        assertEquals(1 , action.exponentiation(Double.NaN, 0), 0);

    }

    @Test(expected = ArithmeticException.class)
    public void exponentiationTest1() throws Exception {
        action.exponentiation(3, Double.NaN);
        action.exponentiation(Double.NaN, 3);
    }
}