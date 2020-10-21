package neuralnetwork.activatefunction;

public class ReLU implements ActivateFunction {
    @Override
    public double[][] function(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] < 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = input[i][j];
                }
            }
        }
        return result;
    }

    @Override
    public double[][] derivative(double[][] input, double[][] activate) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] < 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }
}
