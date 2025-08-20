package core;

public class MeanSquaredError implements LossFunction {
    @Override
    public double compute(double[] predicted, double[] actual) {
        double sum = 0.0;
        for (int i = 0; i < predicted.length; i++) {
            sum += Math.pow(predicted[i] - actual[i], 2);
        }
        return sum / predicted.length;
    }

    @Override
    public double[] gradient(double[] predicted, double[] actual) {
        double[] grad = new double[predicted.length];
        for (int i = 0; i < predicted.length; i++) {
            grad[i] = 2 * (predicted[i] - actual[i]) / predicted.length;
        }
        return grad;
    }
}