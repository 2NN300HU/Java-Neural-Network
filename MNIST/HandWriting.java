package MNIST;

import neuralnetwork.dataset.Dataset;
import neuralnetwork.datasetload.LoadMNIST;
import neuralnetwork.initializemethod.HeInitialization;
import neuralnetwork.machinelearning.NeuralNetwork;
import neuralnetwork.machinelearning.Save;
import neuralnetwork.machinelearning.Train;
import neuralnetwork.machinelearning.TrainMethod;

public class HandWriting {
    public static void main(String[] args) throws Exception {
        NeuralNetwork nn = NeuralNetwork.rate(0.01)           // mandatory
                                        .addInputLayer(784)   // mandatory
                                        .addHiddenLayer(300)  // mandatory
                                        .addOutputLayer(10);  // mandatory

        Dataset test = LoadMNIST.load("./data/t10k-images.idx3-ubyte", "./data/t10k-labels.idx1-ubyte");
        Dataset train = LoadMNIST.load("./data/train-images.idx3-ubyte", "./data/train-labels.idx1-ubyte");

        Train tr = Train.initialize(nn)
                        .useMethod(TrainMethod.He.get()) // if not set, default: HeInitialization
                        .backup(true)                    // if not set, default: true
                        .batch(4)                        // if not set, default: 4
                        .epoch(5)                        // if not set, default: 5
                        .train(train)                    // mandatory
                        .test(test);                     // mandatory

        tr.run();
        //tr.runTest();
        Save.save(nn);
    }
}
