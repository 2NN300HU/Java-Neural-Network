package neuralnetwork.activatefunction;

public class Softmax implements ActivateFunction {
    @Override
    public double[][] function(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        double valueSum = 0.0;
        double maxValue = 0.0;
        for (int i = 0; i < input.length; i++) {
            for (double j : input[i]) {
                if (maxValue < j) {
                    maxValue = j;
                }
            }
            for (double j : input[i]) {
                valueSum += Math.exp(j - maxValue);
            }
            for (int j = 0; j < input[0].length; j++) {
                result[i][j] = Math.exp(input[i][j] - maxValue) / valueSum;
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
