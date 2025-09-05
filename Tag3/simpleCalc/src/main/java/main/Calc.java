package main;

public class Calc {
    public double add(double a, double b) { return a + b; }
    public double subtract(double a, double b) { return a - b; }
    public double multiply(double a, double b) { return a * b; }
    public double divide(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Division durch 0 nicht erlaubt");
        return a / b;
    }
}
