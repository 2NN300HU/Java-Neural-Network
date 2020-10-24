package neuralnetwork.dataset;

import java.util.ArrayList;

public class Dataset {
    private ArrayList<Data> dataArrayList = new ArrayList<>();
    private int datasetSize;

    public void addData(Data data) {
        this.dataArrayList.add(data);
    }

    public Data getData(int index) {
        return this.dataArrayList.get(index);
    }

    public int getDatasetSize() {
        return datasetSize;
    }

    public void setDatasetSize(int datasetSize) {
        this.datasetSize = datasetSize;
    }
}
