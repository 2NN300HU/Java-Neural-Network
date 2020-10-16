package NeuralNetwork;

public class InputLayer {
    private InputFunction function;

    InputLayer(InputFunction function) {
        this.function = function;
    }

    public double[][] feedFoward(double[][] input) {
        return this.function.function(input);
    }
}
