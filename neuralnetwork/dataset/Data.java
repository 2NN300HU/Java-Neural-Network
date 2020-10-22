package neuralnetwork.dataset;

public class Data {
    private int label;
    private int[] data;

    public Data(int label, int[] data) {
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
