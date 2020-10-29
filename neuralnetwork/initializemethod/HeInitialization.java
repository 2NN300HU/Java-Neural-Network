package neuralnetwork.initializemethod;

import neuralnetwork.layer.LayerData;
import neuralnetwork.machinelearning.NeuralNetwork;

import java.util.ArrayList;
import java.util.Random;

public class HeInitialization implements InitializeMethod {
    private Random random;

    public HeInitialization() {
        long seed = System.currentTimeMillis();
        this.random = new Random(seed);
    }

    @Override
    public ArrayList<LayerData> set(NeuralNetwork neuralNetwork) {
        ArrayList<LayerData> layerDataArrayList = new ArrayList<>();
        ArrayList<int[]> layerSize = neuralNetwork.getLayerSize();
        for (int[] size : layerSize) {
            LayerData temp = new LayerData();
            temp.inputSize = size[0];
            temp.outputSize = size[1];
            temp.weight = new double[size[0]][size[1]];
            temp.bias = new double[size[1]];
            for (int k = 0; k < size[0]; k++) {
                for (int j = 0; j < size[1]; j++) {
                    temp.weight[k][j] = he(size[0]);
                }

            }
            for (int j = 0; j < size[1]; j++) {
                temp.bias[j] = he(size[0]);
            }
            layerDataArrayList.add(temp);
        }
        return layerDataArrayList;
    }

    private double he(int in) {
        return (random.nextDouble() - 0.5) * 2 * Math.sqrt(6.0 / in);
    }

    @Override
    public String getName() {
        return "He Initialization";
    }
}
