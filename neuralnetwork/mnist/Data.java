package neuralnetwork.mnist;

public class Data {
    private int label;
    private int[] data;

    Data(int label, int[] data) {
        this.label = label;
        this.data = data.clone();
    }

    public int[] getData() {
        return data;
    }

    public int getLabel() {
        return label;
    }
}
