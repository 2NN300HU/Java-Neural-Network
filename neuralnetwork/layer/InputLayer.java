package neuralnetwork.layer;

import neuralnetwork.inputfunction.InputFunction;

public class InputLayer {
    private InputFunction function;
    private int inputSize;

    public InputLayer(InputFunction function, int inputSize) {
        this.function = function;
        this.inputSize = inputSize;
    }

    public double[][] feedForward(double[][] input) throws Exception {
        if (input[0].length != this.inputSize) {
            throw new Exception("Error : Dataset's Size and Input Layer's size are not same");
        }
        return this.function.function(input);
    }
}
