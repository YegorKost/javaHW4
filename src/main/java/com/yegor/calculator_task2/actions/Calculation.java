package com.yegor.calculator_task2.actions;

import static com.yegor.calculator_task2.actions.Actions.*;

/**
 * This class contains methods that parse the input string
 * with arithmetic action and performs calculation.
 * Created by YegorKost on 14.02.2017.
 */
public class Calculation {
    private Action action = new Action();

    public void calculate(String statement) {
        parser(statement);
    }

    private void calculation(String firstArg, String secondArg, Actions action) {
        double first;
        double second;
        double result = Double.NaN;
        try {
            first = Double.valueOf(firstArg);
            second = Double.valueOf(secondArg);
            switch (action) {
                case ADDITION: result = this.action.addition(first, second); break;
                case SUBTRACTION: result = this.action.subtraction(first, second); break;
                case MULTIPLICATION: result = this.action.multiplication(first, second); break;
                case DIVISION: result = this.action.division(first, second); break;
                case SQUARE_ROOT: result = this.action.squareRoot(first); break;
                case EXPONENTIATION: result = this.action.exponentiation(first, second); break;
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Illegal number format: " + e.getMessage());
        }


        System.out.println("Result = " + result);

    }

    // Method parsers string with arithmetic action
    private void parser(String statement) {
        String[] arg;
        arg = statement.split("\\s+");
        try {
            if (arg[1].equals("+")) {
                calculation(arg[0], arg[2], ADDITION);
            } else if (arg[1].equals("-")) {
                calculation(arg[0], arg[2], SUBTRACTION);
            } else if (arg[1].equals("*")) {
                calculation(arg[0], arg[2], MULTIPLICATION);
            } else if (arg[1].equals("/")) {
                calculation(arg[0], arg[2], DIVISION);
            } else if (arg[0].equals("sqr")) {
                calculation(arg[1], "0", SQUARE_ROOT);
            } else if (arg[1].equals("^")) {
                calculation(arg[0], arg[2], EXPONENTIATION);
            } else {
                System.out.println("Current operator is not available");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Enter the correct statement. See above!");
        }
    }
}
