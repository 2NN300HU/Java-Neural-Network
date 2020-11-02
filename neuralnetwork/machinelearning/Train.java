package neuralnetwork.machinelearning;

import neuralnetwork.dataset.Data;
import neuralnetwork.dataset.Dataset;
import neuralnetwork.initializemethod.HeInitialization;
import neuralnetwork.initializemethod.InitializeMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Train {

    private NeuralNetwork neuralNetwork;
    private Dataset trainDataset;
    private Dataset testDataset;
    private int batch = 4; // default option
    private int epoch = 5; // default option
    private boolean backup = true; // default option
    private int testBatch = 10;
    private boolean isInitialized = false;
    private final String initializeMethodName;
    InitializeMethod initializeMethod = TrainMethod.He.get(); // default is HeInitialization

    private Train(NeuralNetwork neuralNetwork) throws Exception {
        this.neuralNetwork = neuralNetwork;
        this.isInitialized = true;
        this.neuralNetwork.setLayerData(initializeMethod.set(neuralNetwork));
        this.initializeMethodName = initializeMethod.getName();
    }

    public static Train initialize(NeuralNetwork neuralNetwork) throws Exception {
        return new Train(neuralNetwork);
    }
    public Train test(Dataset testDataset) {
        this.testDataset = testDataset;
        return this;
    }
    public Train train(Dataset trainDataset) {
        this.trainDataset = trainDataset;
        return this;
    }

    public String getInitializeMethodName() {
        return initializeMethodName;
    }

    public Train useMethod(InitializeMethod initializeMethod) {
        this.initializeMethod = initializeMethod;
        return this;
    }

    public Train batch(int batch) {
        this.batch = batch;
        return this;
    }

    public Train epoch(int epoch) {
        this.epoch = epoch;
        return this;
    }

    public Train backup(boolean backup) {
        this.backup = backup;
        return this;
    }

    public void run() throws Exception {
        if(trainDataset == null) {
            throw new Exception("you forget setting train dataset in Train class.");
        }
        train(backup);
    }

    public void runTest() throws Exception {
        this.neuralNetwork.printSetting();
        test("");
    }

    private void train(boolean backup) throws Exception {
        if (trainDataset == null) {
            throw new Exception("Error : Dataset for train must be added");
        }
        if (!isInitialized) {
            throw new Exception("Error : Neural network must be initialized");
        }
        System.out.println("\nStart train...");
        System.out.printf("Initialize method : %s\n", this.initializeMethod);
        System.out.printf("Learning rate : %f\n", this.neuralNetwork.getLearningRate());
        System.out.printf("Batch Size : %d Epoch Size : %d\n", this.batch, this.epoch);
        System.out.printf("Backup: %b\n", backup);
        for (int epoch = 0; epoch < this.epoch; epoch++) {
            int i = 0;
            while (i < this.trainDataset.getDatasetSize()) {
                Batch miniBatch = new Batch();
                for (int j = 0; j < this.batch; j++) {
                    if (i < trainDataset.getDatasetSize()) {
                        miniBatch.addData(trainDataset.getData(i));
                        i++;
                    }
                }
                this.neuralNetwork.feedForward(miniBatch.getInput());
                this.neuralNetwork.backpropagation(miniBatch.getLabel());
                System.out.printf("\rTraining... (%d / %d)", i, this.trainDataset.getDatasetSize());
            }
            test(String.format(" Epoch : %d / %d ", epoch + 1, this.epoch));
            if (backup) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
                Calendar time = Calendar.getInstance();
                String formatTime = format.format(time.getTime());
                Save.save(this.neuralNetwork, formatTime + "_backup_settings.bin");
            }
        }
    }

    private void test(String epochData) throws Exception {
        if (testDataset == null) {
            throw new Exception("Error : Dataset for test must be added");
        }
        if (!isInitialized) {
            throw new Exception("Error : Neural network must be initialized");
        }
        int i = 0;
        double[][] result;
        int[] label;
        Batch miniBatch;
        int correct = 0;
        int wrong = 0;
        while (i < this.testDataset.getDatasetSize()) {
            miniBatch = new Batch();
            for (int j = 0; j < this.testBatch; j++) {
                if (i < testDataset.getDatasetSize()) {
                    miniBatch.addData(testDataset.getData(i));
                    i++;
                }
            }
            result = this.neuralNetwork.feedForward(miniBatch.getInput());
            label = miniBatch.getLabel();
            for (int k = 0; k < miniBatch.size; k++) {
                if (getMaxLabel(result[k]) == label[k]) {
                    correct++;
                } else {
                    wrong++;
                }
            }
            System.out.printf("\rTesting... (%d / %d)", i, this.testDataset.getDatasetSize());
        }
        System.out.printf("\rTest result : %sTotal : %d Wrong : %d Correct : %d Error rate %.3f\n", epochData, correct + wrong, wrong, correct, (float) wrong / (correct + wrong));
    }

    private int getMaxLabel(double[] data) {
        double test = data[0];
        int label = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] > test) {
                test = data[i];
                label = i;
            }
        }
        return label;
    }

}

class Batch {
    ArrayList<double[]> data = new ArrayList<>();
    ArrayList<Integer> label = new ArrayList<>();
    int size = 0;

    public void addData(Data data) {
        this.data.add(data.getData());
        this.label.add(data.getLabel());
        size++;
    }

    public double[][] getInput() {
        if (!this.data.isEmpty()) {
            double[][] result = new double[data.size()][data.get(0).length];
            for (int i = 0; i < this.data.size(); i++) {
                result[i] = data.get(i).clone();
            }
            return result;
        }
        return null;
    }

    public int[] getLabel() {
        if (!this.label.isEmpty()) {
            int[] result = new int[this.label.size()];
            for (int i = 0; i < this.label.size(); i++) {
                result[i] = label.get(i);
            }
            return result;
        }
        return null;
    }
}
