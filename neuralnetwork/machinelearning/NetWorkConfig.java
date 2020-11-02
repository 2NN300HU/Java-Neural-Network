package neuralnetwork.machinelearning;

import neuralnetwork.activatefunction.ActivateFunction;
import neuralnetwork.activatefunction.ReLU;
import neuralnetwork.activatefunction.Softmax;
import neuralnetwork.inputfunction.InputNormalize;

// 관심사 분리
public class NetWorkConfig {

    private NetWorkConfig() {}
    public static InputNormalize getInputNormalize() { return new InputNormalize(); }
    public static Softmax getSoftMax() { return new Softmax(); }
    public static ActivateFunction getFunction() { return new ReLU(); }

}
