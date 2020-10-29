package neuralnetwork.machinelearning;

import neuralnetwork.layer.LayerData;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Save {
    public static void save(NeuralNetwork neuralNetwork) throws Exception {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("setting.bin"));
        ArrayList<LayerData> layerDataArrayList = neuralNetwork.getLayerData();
        out.writeInt(layerDataArrayList.size());
        for (LayerData i : layerDataArrayList) {
            out.writeInt(i.inputSize);
            out.writeInt(i.outputSize);
            for (double[] k : i.weight) {
                for (double j : k) {
                    out.writeDouble(j);
                }
            }
            for (double k : i.bias) {
                out.writeDouble(k);
            }
        }
    }
}
