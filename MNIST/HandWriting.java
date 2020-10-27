package MNIST;

import neuralnetwork.activatefunction.ReLU;
import neuralnetwork.activatefunction.Sigmoid;
import neuralnetwork.activatefunction.Softmax;
import neuralnetwork.dataset.Dataset;
import neuralnetwork.datasetload.LoadMNIST;
import neuralnetwork.initializemethod.HeInitialization;
import neuralnetwork.initializemethod.XavierInitialization;
import neuralnetwork.inputfunction.InputNormalize;
import neuralnetwork.machinelearning.NeuralNetwork;
import neuralnetwork.machinelearning.Save;
import neuralnetwork.machinelearning.Train;

public class HandWriting {
    public static void main(String[] args) throws Exception {
        NeuralNetwork nn = new NeuralNetwork(0.01);
        nn.addInputLayer(new InputNormalize(), 784);
        nn.addHiddenLayer(new ReLU(), 300);
        nn.addOutputLayer(new Softmax(), 10);
        Dataset train = LoadMNIST.load("./src/MNIST/data/train-images-idx3-ubyte", "./src/MNIST/data/train-labels-idx1-ubyte");
        Dataset test = LoadMNIST.load("./src/MNIST/data/t10k-images-idx3-ubyte", "./src/MNIST/data/t10k-labels-idx1-ubyte");
        Train tr = new Train(nn);
        tr.Initialize(new XavierInitialization());
        tr.setTrainDataset(train);
        tr.setTestDataset(test);
        tr.run(4, 1);
        Save.save(nn);
    }
}
