package neuralnetwork.machinelearning;

import neuralnetwork.initializemethod.HeInitialization;
import neuralnetwork.initializemethod.InitializeMethod;
import neuralnetwork.initializemethod.XavierInitialization;

public enum TrainMethod {
    He(new HeInitialization()), Xavier(new XavierInitialization());
    private final InitializeMethod initializeMethod;
    TrainMethod(InitializeMethod initializeMethod) {
        this.initializeMethod = initializeMethod;
    }
    public InitializeMethod get() {
        return initializeMethod;
    }
}
