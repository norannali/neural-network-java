package network;

import core.Matrix;
import core.ActivationFunction;

public class layer {
    public final int inputSize;
    public final int outputSize;
    public Matrix weights;
    public Matrix biases;
    public ActivationFunction activation;

    public Matrix lastInput;
    public Matrix lastZ;

    public layer(int inputSize, int outputSize, ActivationFunction activation) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.activation = activation;

        this.weights = new Matrix(outputSize, inputSize);
        this.weights.randomize();

        this.biases = new Matrix(outputSize, 1);
        this.biases.randomize();
    }

    public Matrix forward(Matrix input) {
        this.lastInput = input;
        Matrix z = weights.dot(input).add(biases);
        this.lastZ = z;
        return z.apply(activation::activate);
    }

    public Matrix backward(Matrix dOutput, double learningRate) {
        Matrix dZ = Matrix.multiplyElementWise(dOutput, lastZ.apply(activation::derivative));
        Matrix dW = dZ.dot(lastInput.transpose());
        Matrix dB = dZ;

        weights = weights.subtract(dW.multiplyByScalar(learningRate));
        biases = biases.subtract(dB.multiplyByScalar(learningRate));

        return weights.transpose().dot(dZ);
    }
}