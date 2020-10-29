package neuralnetwork.initializemethod;

import neuralnetwork.layer.LayerData;
import neuralnetwork.machinelearning.NeuralNetwork;

import java.util.ArrayList;

public interface InitializeMethod {
    ArrayList<LayerData> set(NeuralNetwork neuralNetwork) throws Exception;

    String getName();
}
