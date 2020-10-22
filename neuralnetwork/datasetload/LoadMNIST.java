package neuralnetwork.datasetload;

import neuralnetwork.dataset.Data;
import neuralnetwork.dataset.Dataset;

import java.io.DataInputStream;
import java.io.FileInputStream;


public class LoadMNIST {
    private static int changeByteToInt(byte[] data) {
        int temp = 0;
        for (byte i : data) {
            temp = (temp << 8) + i;
        }
        return temp;
    }

    private static int changeUnsignedByteToInt(byte[] data) {
        int temp = 0;
        for (byte i : data) {
            temp = (temp << 8) + i;
        }
        return temp & 0xff;
    }

    public static Dataset load(String dataFile, String labelFile) throws Exception {
        Dataset dataset = new Dataset();
        byte[] magicNumber = new byte[4];
        byte[] itemSize = new byte[4];
        byte[] rowSize = new byte[4];
        byte[] columnSize = new byte[4];
        byte[] label = new byte[1];
        byte[] pixel = new byte[1];
        DataInputStream imagedata = new DataInputStream(new FileInputStream(dataFile));
        DataInputStream labeldata = new DataInputStream(new FileInputStream(labelFile));

        imagedata.read(magicNumber);
        imagedata.read(itemSize);
        imagedata.read(rowSize);
        imagedata.read(columnSize);

        if (changeByteToInt(magicNumber) != 2051) {
            throw new Exception("Data file is not Valid :" + dataFile);
        }

        MNISTFile imagedataFile = new MNISTFile(changeByteToInt(rowSize), changeByteToInt(columnSize), changeByteToInt(itemSize));
        System.out.printf("Image data loaded; image size : %d count : %d \n", imagedataFile.dataSize, imagedataFile.dataCount);

        labeldata.read(magicNumber);
        labeldata.read(itemSize);

        if (changeByteToInt(magicNumber) != 2049) {
            throw new Exception("Label file is not Valid :" + dataFile);
        }

        MNISTFile labeldataFile = new MNISTFile(changeByteToInt(itemSize));
        System.out.printf("Label data loaded; count : %d\n", labeldataFile.dataCount);

        if (labeldataFile.dataCount != imagedataFile.dataCount) {
            throw new Exception("Size of Label File and Image File are not same");
        }

        int tempLabel;
        int[] tempImage = new int[imagedataFile.dataSize];
        for (int k = 0; k < imagedataFile.dataCount; k++) {
            labeldata.read(label);
            tempLabel = changeUnsignedByteToInt(label);
            for (int i = 0; i < imagedataFile.dataSize; i++) {
                imagedata.read(pixel);
                tempImage[i] = changeUnsignedByteToInt(pixel);
            }
            Data tempData = new Data(tempLabel, tempImage);
            dataset.addData(tempData);
        }

        return dataset;
    }
}

class MNISTFile {
    public int dataSize;
    public int dataCount;

    MNISTFile(int row, int col, int dataSize) {
        this.dataSize = row * col;
        this.dataCount = dataSize;
    }

    MNISTFile(int dataCount) {
        this.dataSize = 1;
        this.dataCount = dataCount;
    }

}

