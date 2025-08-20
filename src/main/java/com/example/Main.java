package com.example;

import network.NeuralNetwork;
import core.*;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(new MeanSquaredError());

        nn.addLayer(3, 5, new ReLU());
        nn.addLayer(5, 2, new Sigmoid());

        double[] input = {1.0, 0.5, -1.2};
        double[] target = {0.0, 1.0};

        nn.train(input, target, 0.01);

        double[] prediction = nn.predict(input);
        System.out.println("Prediction:");
        for (double p : prediction) {
            System.out.println(p);
        }

        double loss = nn.computeLoss(input, target);
        System.out.println("Loss: " + loss);

        double accuracy = computeAccuracy(prediction, target);
        System.out.println("Accuracy: " + (accuracy * 100) + "%");
    }

    private static double computeAccuracy(double[] predicted, double[] actual) {
        int correct = 0;
        for (int i = 0; i < predicted.length; i++) {
            if (Math.abs(predicted[i] - actual[i]) < 0.5) { // عتبة الدقة
                correct++;
            }
        }
        return (double) correct / actual.length;
    }
}