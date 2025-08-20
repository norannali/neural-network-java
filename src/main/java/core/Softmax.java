package core;

public class Softmax {
    public static double[] compute(double[] input) {
        double max = Double.NEGATIVE_INFINITY;
        for (double val : input) {
            if (val > max) max = val;
        }

        double sum = 0.0;
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = Math.exp(input[i] - max); // numerical stability
            sum += output[i];
        }
        for (int i = 0; i < output.length; i++) {
            output[i] /= sum;
        }
        return output;
    }
}
