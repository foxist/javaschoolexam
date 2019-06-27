package com.tsystems.javaschool.tasks.calculator;

import java.util.*;

//TODO
// Welcome, to the Calculator!
// You must enter a string consisting of numbers, floating-point numbers, and arithmetic operations:
// "()" - brackets
// "^" - exponentiation
// "*" - multiply
// "/" - division
// "!" - factorial
// "+" - addition
// "-" - subtraction
public class Calculator {

    /**
     * evaluate - call two methods. First is check the input string and if is return true
     * we can calculate reverse Polish Notation, or null
     * Params: input string
     * Return: if input string right true, else null*/
    String evaluate(String input) {
        if (isRight(input))
            return getReversePolishNotation(input);
        return null;
    }

    /**
     * getReversePolishNotation - considers reverse Polish addiction
     * Params: input string
     * Return: output string is a result after reverse Polish notation
     */
    private static String getReversePolishNotation(String input) {
        input += '\n';
        Stack<Character> ops = new Stack<Character>();
        Stack<Double> values = new Stack<Double>();
        int lengthNum = 0, bracketCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                ops.push(input.charAt(i));
                bracketCount++;
            }
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9' || input.charAt(i) == '.')
                lengthNum++;
            if (isOps(input.charAt(i)) || input.charAt(i) == '\n') {
                if (lengthNum != 0) {
                    values.push(Double.valueOf(input.substring(i - lengthNum, i)));
                    lengthNum = 0;
                }
                while (!ops.empty() && getPriority(ops.peek()) >= getPriority(input.charAt(i))) {
                    if (ops.peek() == '^' && input.charAt(i) == '^')
                        break;
                    char op = ops.pop();
                    double b = values.pop(), a = 0.0;
                    if (op != '!' && !values.empty())
                        a = values.pop();
                    try {
                        action(values, op, a, b);
                    } catch (ArithmeticException ex) {
                        return null;
                    }
                }
                ops.push(input.charAt(i));
            }
            if (input.charAt(i) == ')' && bracketCount > 0) {
                if (lengthNum != 0) {
                    values.push(Double.valueOf(input.substring(i - lengthNum, i)));
                    lengthNum = 0;
                }
                char op = ops.pop();
                while (op != '(') {
                    double b = values.pop(), a = 0.0;
                    if (op != '!' && !values.empty())
                        a = values.pop();
                    try {
                        action(values, op, a, b);
                    } catch (ArithmeticException ex) {
                        return null;
                    }
                    op = ops.pop();
                }
                bracketCount--;
            }
        }
        if (bracketCount != 0)
            return null;
        return isInteger(values.pop());
    }

    /**
     * isOps - Returns true if the character is an arithmetic operation
     * Params: char c - is an arithmetic operation
     * Return: true if c is an arithmetic operation, else false
     */
    private static boolean isOps(char c) {
        return c == '^' || c == '!' || c == '*' || c == '/' || c == '+' || c == '-';
    }

    /**
     * action - Combines the methods of arithmetic operations
     * Params: stack of values, char op is arithmetic operation, double a, b - are numbers which we will consider
     */
    private static void action(Stack<Double> values, char op, double a, double b) {
        if (op == '^')
            values.push(exp(a, b));
        else if (op == '!')
            values.push(fac(b));
        else if (op == '*')
            values.push(mul(a, b));
        else if (op == '/') {
            values.push(Double.valueOf(div(a, b)));
        } else if (op == '+')
            values.push(add(a, b));
        else if (op == '-')
            values.push(sub(a, b));
    }

    /**
     * add - Addition
     * Params: double a, double b
     * Return: double a + b
     */
    private static double add(double a, double b) {
        return a + b;
    }

    /**
     * sub - Subtraction
     * Params: double a, double b
     * Return: double a - b
     */
    private static double sub(double a, double b) {
        return a - b;
    }

    /**
     * mul - Multiply
     * Params: double a, double b
     * Return: double a * b
     */
    private static double mul(double a, double b) {
        return a * b;
    }

    /**
     * div - Division
     * Params: double a, double b
     * Return: double a / b, if b == 0, then exception
     */
    private static String div(double a, double b) {
        if (b == 0)
            throw new ArithmeticException();
        return String.valueOf(a / b);
    }

    /**
     * exp - Exponentiation
     * Params: double a, double n
     * Return: double a ^ n
     */
    private static double exp(double a, double n) {
        return Math.pow(a, n);
    }

    /**
     * fac - Factorial
     * Params: double n
     * Return: if n == 0 and n == 0, then return 1, else return n!
     */
    private static double fac(double n) {
        if (n == 0) return 1.0;
        if (n >= 1) return n * fac(n - 1);
        else return n * fac(-1.0 * (n + 1));
    }

    /**
     * getPriority - returns the operation priority
     * Params: char c
     * Return: if с == '^' || c == '!', then return 3, etc. by priority
     */
    private static int getPriority(char c) {
        if (c == '^' || c == '!') return 3;
        if (c == '*' || c == '/') return 2;
        if (c == '+' || c == '-') return 1;
        if (c == '(' || c == ')') return 0;
        return -1;
    }

    /**
     * isRight - checks method for string validity
     * Params: input string
     * Return: if string is right return true, else false*/
    private static boolean isRight(String input) {
        if (input == null || input.equals(""))
            return false;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ' || input.charAt(i) == ',')
                return false;
            if (isOps(input.charAt(i)) || input.charAt(i) == '.')
                if (i + 1 < input.length() && isOps(input.charAt(i + 1)) || input.charAt(i) == '.')
                    if (input.charAt(i) == input.charAt(i + 1))
                        return false;
        }
        return true;
    }

    /**
     * isInteger - checks method is value int or double
     * Params: double input
     * Return: if input - (int) input == 0, the method return integer in string, else double in string*/
    private static String isInteger(double input) {
        return input - (int) input == 0 ? String.valueOf((int) input) : String.valueOf(input);
    }
}