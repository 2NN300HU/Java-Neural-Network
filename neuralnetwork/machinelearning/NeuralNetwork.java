package neuralnetwork.machinelearning;

import neuralnetwork.activatefunction.ActivateFunction;
import neuralnetwork.inputfunction.InputFunction;
import neuralnetwork.layer.HiddenLayer;
import neuralnetwork.layer.InputLayer;
import neuralnetwork.layer.LayerData;
import neuralnetwork.layer.OutputLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class NeuralNetwork {
    private InputLayer inputLayer = null;
    private int inputLayerSize;
    private ArrayList<HiddenLayer> hiddenLayers = new ArrayList<>();
    private ArrayList<Integer> hiddenLayersSize = new ArrayList<>();
    private OutputLayer outputLayer = null;
    private double learningRate;
    private ArrayList<int[]> layerSize = new ArrayList<>();

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
            layerSize.add(new int[]{inputLayerSize, size});
        } else {
            int temp = hiddenLayersSize.get(hiddenLayers.size() - 1);
            hiddenLayers.add(new HiddenLayer(function, temp, size, this.learningRate));
            layerSize.add(new int[]{temp, size});
        }
        hiddenLayersSize.add(size);
    }

    public void addOutputLayer(ActivateFunction function, int size) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before adding input layer");
        }
        if (!(outputLayer == null)) {
            throw new Exception("Error : Output layer already exists!");
        }
        if (hiddenLayers.isEmpty()) {
            outputLayer = new OutputLayer(function, inputLayerSize, size, this.learningRate);
            layerSize.add(new int[]{inputLayerSize, size});
        } else {
            int temp = hiddenLayersSize.get(hiddenLayers.size() - 1);
            outputLayer = new OutputLayer(function, temp, size, this.learningRate);
            layerSize.add(new int[]{temp, size});
        }
    }

    public ArrayList<LayerData> getLayerData() throws Exception {
        if (outputLayer == null) {
            throw new Exception("Error : output layer must be added before saving settings");
        }
        ArrayList<LayerData> layerDataArrayList = new ArrayList<>();
        for (HiddenLayer hiddenLayer : this.hiddenLayers) {
            layerDataArrayList.add(hiddenLayer.getData());
        }
        layerDataArrayList.add(outputLayer.getData());
        return layerDataArrayList;
    }

    public void setLayerData(ArrayList<LayerData> layerData) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before load settings");
        }
        if (outputLayer == null) {
            throw new Exception("Error : output layer must be added before load settings");
        }

        Iterator<LayerData> iterator = layerData.iterator();
        for (HiddenLayer hiddenLayer : this.hiddenLayers) {
            if (!iterator.hasNext()) {
                throw new Exception("Error : Not enough layers in setting");
            }
            hiddenLayer.setData(iterator.next());
        }

        if (!iterator.hasNext()) {
            throw new Exception("Error : Not enough layers in setting");
        }
        outputLayer.setData(iterator.next());
    }

    public double[][] feedForward(double[][] input) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before feed forward");
        }
        if (outputLayer == null) {
            throw new Exception("Error : output layer must be added before feed forward");
        }
        double[][] result;
        result = this.inputLayer.feedForward(input);

        for (HiddenLayer hiddenLayer : this.hiddenLayers) {
            result = hiddenLayer.feedForward(result);
        }

        result = this.outputLayer.feedForward(result);

        return result;
    }

    public void backpropagation(int[] label) {
        double[][] result;
        result = this.outputLayer.backpropagation(label);
        ListIterator<HiddenLayer> iterator = this.hiddenLayers.listIterator(this.hiddenLayers.size());
        while (iterator.hasPrevious()) {
            result = iterator.previous().backpropagation(result);
        }
    }

    public ArrayList<int[]> getLayerSize() {
        return layerSize;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
