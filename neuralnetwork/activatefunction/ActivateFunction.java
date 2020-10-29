package neuralnetwork.activatefunction;

public interface ActivateFunction {
    double[][] function(double[][] input);

    double[][] derivative(double[][] input, double[][] activate);

    String getName();
}
