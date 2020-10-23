package neuralnetwork.datasetload;

import neuralnetwork.dataset.Data;
import neuralnetwork.dataset.Dataset;

import java.io.DataInputStream;
import java.io.FileInputStream;


public class LoadMNIST {
    public static Dataset load(String dataFile, String labelFile) throws Exception {
        Dataset dataset = new Dataset();
        int magicNumber;
        int itemSize;
        int rowSize;
        int columnSize;

        DataInputStream imagedata = new DataInputStream(new FileInputStream(dataFile));
        DataInputStream labeldata = new DataInputStream(new FileInputStream(labelFile));

        magicNumber = imagedata.readInt();
        itemSize = imagedata.readInt();
        rowSize = imagedata.readInt();
        columnSize = imagedata.readInt();

        if (magicNumber != 2051) {
            throw new Exception("Error : Data file is not Valid :" + dataFile);
        }

        MNISTFile imagedataFile = new MNISTFile(rowSize, columnSize, itemSize);
        System.out.printf("Image data loaded; image size : %d count : %d \n", imagedataFile.dataSize, imagedataFile.dataCount);

        magicNumber = labeldata.readInt();
        itemSize = labeldata.readInt();

        if (magicNumber != 2049) {
            throw new Exception("Error : Label file is not Valid :" + dataFile);
        }

        MNISTFile labeldataFile = new MNISTFile(itemSize);
        System.out.printf("Label data loaded; count : %d\n", labeldataFile.dataCount);

        if (labeldataFile.dataCount != imagedataFile.dataCount) {
            throw new Exception("Error : Size of Label File and Image File are not same");
        }

        int tempLabel;
        int[] tempImage = new int[imagedataFile.dataSize];

        for (int k = 0; k < imagedataFile.dataCount; k++) {
            tempLabel = labeldata.readUnsignedByte();
            for (int i = 0; i < imagedataFile.dataSize; i++) {
                tempImage[i] = imagedata.readUnsignedByte();
            }
            Data tempData = new Data(tempLabel, tempImage);
            dataset.addData(tempData);
//        //Test code for printing one image
//        int k = 0;
//        for (int i : tempData.getData()) {
//            System.out.printf("%d " ,i);
//            k = (k+1) % 28;
//            if (k ==0){
//                System.out.println();
//            }
//        }
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

