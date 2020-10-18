package neuralnetwork.inputfunction;

public class InputNormalize implements InputFunction {
    @Override
    public double[][] function(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                result[i][j] = input[i][j] / 255;
            }
        }
        return result;
    }
}