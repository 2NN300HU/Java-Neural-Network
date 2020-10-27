package neuralnetwork.layer;

import neuralnetwork.calculate.Matrix;
import neuralnetwork.activatefunction.ActivateFunction;

public class HiddenLayer extends Layer {

    public HiddenLayer(ActivateFunction function, int inputSize, int outputSize, double learningRate) {
        super(function, inputSize, outputSize, learningRate);
    }

    public double[][] backpropagation(double[][] nextLayerResult) {
        double[][] deltaBias;
        double[][] result = new double[batchSize][inputSize];
        double[][] deltaWeight = new double[inputSize][outputSize];
        output = function.derivative(output, outputActivate);
        deltaBias = Matrix.eachMultiply(nextLayerResult, output);
        for (int i = 0; i < batchSize; i++) {
            for (int k = 0; k < inputSize; k++) {
                for (int j = 0; j < outputSize; j++) {
                    deltaWeight[k][j] += input[i][k] * deltaBias[i][j];
                    result[i][k] += deltaBias[i][j] * weight[k][j];
                }
            }
        }
        Matrix.update(weight, deltaWeight, learningRate, batchSize, bias, deltaBias);
        return result;
    }
}
