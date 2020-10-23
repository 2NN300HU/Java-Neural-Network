package neuralnetwork.layer;

import neuralnetwork.calculate.Matrix;
import neuralnetwork.activatefunction.ActivateFunction;

public class OutputLayer extends Layer {

    public OutputLayer(ActivateFunction function, int inputSize, int outputSize, double learningRate) {
        super(function, inputSize, outputSize, learningRate);
    }

    public double[][] backpropagation(int[] label) {
        double[][] deltaBias;
        double[][] result = new double[batchSize][inputSize];
        double[][] deltaWeight = new double[inputSize][outputSize];
        for (int i = 0; i < label.length; i++) {
            outputActivate[i][label[i]] -= 1;
        }
        output = function.derivative(output, outputActivate);
        deltaBias = Matrix.eachMultiply(outputActivate, output);
        for (int i = 0; i < batchSize; i++) {
            for (int k = 0; k < inputSize; k++) {
                for (int j = 0; j < outputSize; j++) {
                    deltaWeight[k][j] += input[i][k] * deltaBias[i][j];
                    result[i][k] = deltaBias[i][j] * weight[k][j];
                }
            }
        }
        Matrix.update(weight, deltaWeight, learningRate, batchSize, bias, deltaBias);
        return result;
    }
}
