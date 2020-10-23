package neuralnetwork.machinelearning;

import neuralnetwork.activatefunction.ActivateFunction;
import neuralnetwork.inputfunction.InputFunction;
import neuralnetwork.layer.HiddenLayer;
import neuralnetwork.layer.InputLayer;
import neuralnetwork.layer.OutputLayer;

import java.util.ArrayList;

public class NeuralNetwork {
    private InputLayer inputLayer = null;
    private int inputLayerSize;
    private ArrayList<HiddenLayer> hiddenLayers = new ArrayList<>();
    private ArrayList<Integer> hiddenLayersSize = new ArrayList<>();
    private OutputLayer outputLayer;
    private double learningRate;

    public NeuralNetwork(double learningRate) {
        this.learningRate = learningRate;
    }

    public void addInputLayer(InputFunction inputFunction, int size) throws Exception {
        if (!(inputLayer == null)) {
            throw new Exception("Error : Input layer already exists!");
        }
        this.inputLayer = new InputLayer(inputFunction);
        this.inputLayerSize = size;
    }

    public void addHiddenLayer(ActivateFunction function, int size) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before adding hidden layer");
        }
        if (!(outputLayer == null)) {
            throw new Exception("Error : Can't add hidden layer when output layer is already added");
        }
        if (hiddenLayers.isEmpty()) {
            hiddenLayers.add(new HiddenLayer(function, inputLayerSize, size, this.learningRate));
        } else {
            hiddenLayers.add(new HiddenLayer(function, hiddenLayersSize.get(hiddenLayers.size() - 1), size, this.learningRate));
        }
        hiddenLayersSize.add(size);
    }

    public void getHiddenLayers() {
    }

    public void addOutputLayer(ActivateFunction function, int size) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before adding input layer");
        }
        if (hiddenLayers.isEmpty()) {
            outputLayer = new OutputLayer(function, inputLayerSize, size, this.learningRate);
        } else {
            outputLayer = new OutputLayer(function, hiddenLayersSize.get(hiddenLayers.size() - 1), size, this.learningRate);
        }
    }

    public void getOutputLayer() {
    }

    public void feedFoward() {
    }

    public void backpropagation() {
    }
}
