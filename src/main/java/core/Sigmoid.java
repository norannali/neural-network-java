package core;

public class Sigmoid implements ActivationFunction {
    public double activate(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public double derivative(double x) {
        double y = activate(x);
        return y * (1 - y);
    }
}
