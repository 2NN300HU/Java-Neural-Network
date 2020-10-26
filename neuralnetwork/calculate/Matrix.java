package neuralnetwork.calculate;

public class Matrix {
    public static double[][] sum(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static double[][] sum(double[][] a, double[] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[j];
            }
        }
        return result;
    }

    public static double[][] sub(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public static double[][] sub(double[][] a, double[] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] - b[j];
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < b[0].length; k++) {
                for (int j = 0; j < a[0].length; j++) {
                    result[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return result;
    }

    public static double[][] eachMultiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] += a[i][j] * b[i][j];
            }
        }
        return result;
    }

    public static void update(double[][] weight, double[][] deltaWeight, double learningRate, int batchSize, double[] bias, double[][] deltaBias) {
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[0].length; j++) {
                weight[i][j] -= learningRate * deltaWeight[i][j] / batchSize;
            }
        }
        for (int j = 0; j < deltaBias[0].length; j++) {
            for (double[] deltaBia : deltaBias) {
                bias[j] -= learningRate * deltaBia[j] / batchSize;
            }
        }
    }

}
