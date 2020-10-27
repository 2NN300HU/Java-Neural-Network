package neuralnetwork.activatefunction;

public class Sigmoid implements ActivateFunction {
    @Override
    public double[][] function(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] < 0) {
                    result[i][j] = Math.exp(input[i][j]) / (Math.exp(input[i][j]) + 1.0);
                } else {
                    result[i][j] = 1.0 / (1.0 + Math.exp((-1.0) * input[i][j]));
                }
            }
        }
        return result;
    }

    @Override
    public double[][] derivative(double[][] input, double[][] activate) {
        double[][] result = new double[activate.length][activate[0].length];
        for (int i = 0; i < activate.length; i++) {
            for (int j = 0; j < activate[0].length; j++) {
                result[i][j] = activate[i][j] * (1.0 - activate[i][j]);
            }
        }
        return result;
    }
}
