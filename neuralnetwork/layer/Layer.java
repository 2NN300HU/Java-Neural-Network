package neuralnetwork.layer;

import neuralnetwork.activatefunction.ActivateFunction;
import neuralnetwork.calculate.Matrix;

public class Layer {
    ActivateFunction function;
    int inputSize;
    int outputSize;
    double[][] output;
    double[][] outputActivate;
    double[][] weight;
    double[] bias;
    int batchSize;
    boolean isInitialized = false;
    double[][] input;
    double learningRate;

    public Layer(ActivateFunction function, int inputSize, int outputSize, double learningRate) {
        this.function = function;
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.learningRate = learningRate;
    }

    public void setData(LayerData data) throws Exception {
        if (this.inputSize != data.inputSize) {
            throw new Exception("Error : LayerData's inputSize and Layer's inputSize are not same");
        }
        if (this.outputSize != data.outputSize) {
            throw new Exception("Error : LayerData's outputSize and Layer's outputSize are not same");
        }
        this.weight = data.weight;
        this.bias = data.bias;
        this.isInitialized = true;
    }

    public LayerData getData() throws Exception {
        if (this.isInitialized) {
            LayerData data = new LayerData();
            data.inputSize = this.inputSize;
            data.outputSize = this.outputSize;
            data.bias = this.bias;
            data.weight = this.weight;
            return data;
        } else {
            throw new Exception("Error : Can't get data from not initialized layer");
        }
    }

    public double[][] feedForward(double[][] input) {
        this.input = input;
        this.batchSize = input.length;
        double[][] temp;
        double[][] result = new double[this.batchSize][this.outputSize];
        temp = Matrix.multiply(input, weight);
        this.output = Matrix.sum(temp, bias);
        this.outputActivate = this.function.function(this.output);
        for (int i = 0; i < this.batchSize; i++) {
            result[i] = this.outputActivate[i].clone();
        }
        return result;
    }
}
