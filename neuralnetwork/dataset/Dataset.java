package neuralnetwork.dataset;

import java.util.ArrayList;

public class Dataset {
    private ArrayList<Data> dataArrayList = new ArrayList<>();

    public void addData(Data data) {
        this.dataArrayList.add(data);
    }

    public Data getData(int index) {
        return this.dataArrayList.get(index);
    }
}
