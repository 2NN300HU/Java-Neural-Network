package neuralnetwork.initializemethod;

import neuralnetwork.layer.LayerData;
import neuralnetwork.machinelearning.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public interface InitializeMethod {
    List<LayerData> set(NeuralNetwork neuralNetwork) throws Exception;

    String getName();
}
