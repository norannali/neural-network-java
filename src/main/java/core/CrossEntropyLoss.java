package core;

public class CrossEntropyLoss implements LossFunction {
    public double compute(double[] predicted, double[] actual) {
        double sum = 0.0;
        for (int i = 0; i < predicted.length; i++) {
            sum -= actual[i] * Math.log(predicted[i] + 1e-9); // avoid log(0)
        }
        return sum;
    }

    public double[] gradient(double[] predicted, double[] actual) {
        double[] grad = new double[predicted.length];
        for (int i = 0; i < predicted.length; i++) {
            grad[i] = predicted[i] - actual[i]; // derivative of softmax + cross-entropy
        }
        return grad;
    }
}
