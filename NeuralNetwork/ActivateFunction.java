package NeuralNetwork;

public interface ActivateFunction {
    public abstract double[][] function(double[][] input);

    public abstract double[][] derivative(double[][] input);
}
