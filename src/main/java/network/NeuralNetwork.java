package network;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private final List<layer> layers = new ArrayList<>();
    private final LossFunction lossFunction;

    public NeuralNetwork(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }

    public void addLayer(int inputSize, int outputSize, ActivationFunction activation) {
        layers.add(new layer(inputSize, outputSize, activation));
    }

    public double[] predict(double[] inputArray) {
        Matrix input = Matrix.fromArray(inputArray);
        Matrix output = forward(input);
        return output.toArray();
    }

    private Matrix forward(Matrix input) {
        Matrix output = input;
        for (layer layer : layers) {
            output = layer.forward(output);
        }
        return output;
    }

    public void train(double[] inputArray, double[] targetArray, double learningRate) {
        Matrix input = Matrix.fromArray(inputArray);
        Matrix output = forward(input);

        double[] predicted = output.toArray();
        double[] gradient = lossFunction.gradient(predicted, targetArray);

        Matrix dLoss = Matrix.fromArray(gradient);
        for (int i = layers.size() - 1; i >= 0; i--) {
            dLoss = layers.get(i).backward(dLoss, learningRate);
        }
    }

    public double computeLoss(double[] inputArray, double[] targetArray) {
        double[] predicted = predict(inputArray);
        return lossFunction.compute(predicted, targetArray);
    }
}