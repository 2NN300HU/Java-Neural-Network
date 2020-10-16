package NeuralNetwork;

public class HiddenLayer {
    private ActivateFunction function;
    private int inputsize;
    private int outputsize;
    private double[][] output;
    private double[][] outputActivate;
    private double[][] weight;
    private double[] bias;
    private double[][] input;
    private double learningRate;
    private double[][] deltaWeight;
    private double[][] deltaBias;
    private int batchsize;

    HiddenLayer(ActivateFunction function, int inputSize, int outputSize, double learningRate) {
        this.function = function;
        this.inputsize = inputSize;
        this.outputsize = outputSize;
        this.learningRate = learningRate;
    }

    public void set(double[][] weight, double[] bias) {
        this.weight = weight;
        this.bias = bias;
    }

    public double[][] feedFoward(double[][] input) {
        this.input = input;
        this.batchsize = input.length;
        double[][] temp;
        double[][] result = new double[this.batchsize][this.outputsize];
        temp = MatrixCalculate.multiply(input, weight);
        this.output = MatrixCalculate.sum(temp, bias);
        this.outputActivate = this.function.function(this.output);
        for( int i = 0 ; i < this.outputsize; i++){
            result[i] = this.outputActivate[i].clone();
        }
        return result;
    }

    public double[][] backpropagation(double[][] nextLayerResult) {
        double[][] result = new double[this.batchsize][this.inputsize];
        this.deltaBias = new double[this.batchsize][this.outputsize];
        this.deltaWeight = new double[this.inputsize][this.outputsize];
        this.output = this.function.derivative(this.output);
        this.deltaBias = MatrixCalculate.eachMultiply(nextLayerResult, this.output);
        for (int i = 0; i < this.batchsize; i++) {
            for (int k = 0; k < this.inputsize; k++) {
                for (int j = 0; j < this.outputsize; j++) {
                    this.deltaWeight[k][j] += this.input[i][k] * this.deltaBias[i][j];
                    result[i][k] = this.deltaBias[i][j] * this.weight[k][j];
                }
            }
        }
        MatrixCalculate.join(this.weight, this.deltaWeight, this.learningRate, this.batchsize, this.bias, this.deltaBias);
        return result;
    }
}
