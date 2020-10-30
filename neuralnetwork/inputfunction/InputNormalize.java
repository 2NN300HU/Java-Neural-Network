package neuralnetwork.inputfunction;

public class InputNormalize implements InputFunction {
    @Override
    public double[][] function(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        double min;
        double max;
        for (int i = 0; i < input.length; i++) {
            max = input[i][0];
            min = input[i][0];
            for (double j : input[i]) {
                if (max < j) {
                    max = j;
                } else if (min > j) {
                    min = j;
                }
            }
            double temp = max - min;
            for (int j = 0; j < input[0].length; j++) {
                result[i][j] = input[i][j] / temp;
            }
        }
        return result;
    }

    @Override
    public String getName() {
        return "InputNormalize";
    }
}