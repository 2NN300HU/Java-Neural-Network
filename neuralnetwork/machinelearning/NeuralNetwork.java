package neuralnetwork.machinelearning;

import neuralnetwork.activatefunction.ActivateFunction;
import neuralnetwork.activatefunction.Softmax;
import neuralnetwork.inputfunction.InputNormalize;
import neuralnetwork.layer.HiddenLayer;
import neuralnetwork.layer.InputLayer;
import neuralnetwork.layer.LayerData;
import neuralnetwork.layer.OutputLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static neuralnetwork.machinelearning.NetWorkConfig.*;

public class NeuralNetwork {
    private InputLayer inputLayer;
    private int inputLayerSize;
    private final ArrayList<HiddenLayer> hiddenLayers = new ArrayList<>();
    private final ArrayList<Integer> hiddenLayersSize = new ArrayList<>();
    private OutputLayer outputLayer;
    private final double learningRate;
    private final List<int[]> layerSize = new ArrayList<>();
    private final InputNormalize inputNormalize = getInputNormalize();
    private final ActivateFunction activateFunction = getFunction();
    private final Softmax softmax = getSoftMax();

    private NeuralNetwork(double learningRate) {
        this.learningRate = learningRate;
    }

    public static NeuralNetwork rate(double learningRate) {
        return new NeuralNetwork(learningRate);
    }

    public NeuralNetwork addInputLayer(int size) throws Exception {
        if (!(inputLayer == null)) {
            throw new Exception("Error : Input layer already exists!");
        }
        this.inputLayer = new InputLayer(inputNormalize, size);
        this.inputLayerSize = size;
        return this;
    }

    public NeuralNetwork addHiddenLayer(int size) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before adding hidden layer");
        }
        if (!(outputLayer == null)) {
            throw new Exception("Error : Can't add hidden layer when output layer is already added");
        }
        if (hiddenLayers.isEmpty()) {
            hiddenLayers.add(new HiddenLayer(activateFunction, inputLayerSize, size, this.learningRate));
            layerSize.add(new int[]{inputLayerSize, size});
        } else {
            int temp = hiddenLayersSize.get(hiddenLayers.size() - 1);
            hiddenLayers.add(new HiddenLayer(activateFunction, temp, size, this.learningRate));
            layerSize.add(new int[]{temp, size});
        }
        hiddenLayersSize.add(size);
        return this;
    }

    public NeuralNetwork addOutputLayer(int size) throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before adding input layer");
        }
        if (!(outputLayer == null)) {
            throw new Exception("Error : Output layer already exists!");
        }
        if (hiddenLayers.isEmpty()) {
            outputLayer = new OutputLayer(softmax, inputLayerSize, size, this.learningRate);
            layerSize.add(new int[]{inputLayerSize, size});
        } else {
            int temp = hiddenLayersSize.get(hiddenLayers.size() - 1);
            outputLayer = new OutputLayer(softmax, temp, size, this.learningRate);
            layerSize.add(new int[]{temp, size});
        }
        return this;
    }

    public ArrayList<LayerData> getLayerData() throws Exception {
        ArrayList<LayerData> layerDataArrayList = new ArrayList<>();
        for (HiddenLayer hiddenLayer : this.hiddenLayers) {
            layerDataArrayList.add(hiddenLayer.getData());
        }
        layerDataArrayList.add(outputLayer.getData());
        return layerDataArrayList;
    }

    public void setLayerData(List<LayerData> layerData) throws Exception {

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

    public List<int[]> getLayerSize() {
        return layerSize;
    }

    public void printSetting() throws Exception {
        if (inputLayer == null) {
            throw new Exception("Error : Input layer must be added before printing Settings");
        }
        if (outputLayer == null) {
            throw new Exception("Error : output layer must be added before printing Settings");
        }
        System.out.println("<Network Structure>");
        System.out.print("Input Layer ; ");
        this.inputLayer.printSetting();
        for (HiddenLayer i : this.hiddenLayers) {
            System.out.print("Hidden Layer ; ");
            i.printSetting();
        }
        System.out.print("Output Layer ; ");
        this.outputLayer.printSetting();
    }

    public double getLearningRate() {
        return learningRate;
    }
}
