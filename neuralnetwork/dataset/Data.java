package neuralnetwork.dataset;

public class Data {
    private int label;
    private double[] data;

    public Data(int label, double[] data) {
        this.label = label;
        this.data = data.clone();
    }

    public double[] getData() {
        return data;
    }

    public int getLabel() {
        return label;
    }
}
