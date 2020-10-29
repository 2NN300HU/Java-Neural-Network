package neuralnetwork.initializemethod;

import neuralnetwork.layer.LayerData;
import neuralnetwork.machinelearning.NeuralNetwork;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LoadSettingFile implements InitializeMethod {
    @Override
    public ArrayList<LayerData> set(NeuralNetwork neuralNetwork) throws Exception {
        DataInputStream in = new DataInputStream(new FileInputStream("setting.bin"));
        ArrayList<LayerData> layerDataArrayList = new ArrayList<>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            LayerData temp = new LayerData();
            temp.inputSize = in.readInt();
            temp.outputSize = in.readInt();
            temp.weight = new double[temp.inputSize][temp.outputSize];
            for (int k = 0; k < temp.inputSize; k++) {
                for (int j = 0; j < temp.outputSize; j++) {
                    temp.weight[k][j] = in.readDouble();
                }
            }
            temp.bias = new double[temp.outputSize];
            for (int k = 0; k < temp.outputSize; k++) {
                temp.bias[k] = in.readDouble();
            }
            layerDataArrayList.add(temp);
        }
        return layerDataArrayList;
    }
}
