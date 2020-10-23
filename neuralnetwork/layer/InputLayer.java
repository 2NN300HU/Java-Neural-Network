package neuralnetwork.layer;

import neuralnetwork.inputfunction.InputFunction;

public class InputLayer {
    private InputFunction function;

    public InputLayer(InputFunction function) {
        this.function = function;
    }

    public double[][] feedFoward(int[][] input) {
        return this.function.function(input);
    }
}
