package neuralnetwork.initializemethod;

import neuralnetwork.layer.LayerData;
import neuralnetwork.machinelearning.NeuralNetwork;

import java.util.ArrayList;
import java.util.Random;

public class XavierInitialization implements InitializeMethod {
    private Random random;

    public XavierInitialization() {
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
                    temp.weight[k][j] = xavier(size[0], size[1]);
                }

            }
            for (int j = 0; j < size[1]; j++) {
                temp.bias[j] = xavier(size[0], size[1]);
            }
            layerDataArrayList.add(temp);
        }
        return layerDataArrayList;
    }

    private double xavier(int in, int out) {
        return random.nextGaussian() * Math.sqrt(2.0 / (in + out));
    }
}
