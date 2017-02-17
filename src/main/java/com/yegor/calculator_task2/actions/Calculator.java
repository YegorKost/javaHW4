package com.yegor.calculator_task2.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;

/**
 * This class starts calculator.
 * Created by YegorKost on 15.02.2017.
 */
public class Calculator {
    public static void main(String[] args) {
        Calculation calculation = new Calculation();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))){

            System.out.println("Enter a statement for calculating or \"exit\" for finish.\n" +
                    "Arguments with operator separated with whitespace.\n" +
                    "Available operations:\nargument1 + argument2 - addition\nargument1 - argument2 - subtraction\n" +
                    "argument1 * argument2 - multiplication\nargument1 / argument2 - division\nsqr argument1 - square root\nargument1 ^ argument2 - exponentiation");
            String statement;
            while ((statement = bufferedReader.readLine()) != null) {
                if (!statement.contains("exit")){
                    calculation.calculate(statement);
                    System.out.println("Enter a statement for calculating " +
                            "(argument1 operator argument2) or \"exit\" for finish");
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
