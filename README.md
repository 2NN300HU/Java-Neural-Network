# Java Neural Network
* Neural Network package written in Java
* MNIST.HandWriting is example using [MNIST dataset](http://yann.lecun.com/exdb/mnist/)
 
## Example
### NeuralNetWork Object
1. Make New NeuralNetwork object and set learningRate
    ```java
    NeuralNetwork nn = new NeuralNetwork(0.01);
    ```

2. Add layers and pass Input function, Activate function objects and set Node size
    ```java
    nn.addInputLayer(new InputNormalize(), 784);
    nn.addHiddenLayer(new ReLU(), 300);
    nn.addOutputLayer(new Softmax(), 10);
    ``` 
   
### Dataset object
* Make new Dataset object and set data files and label files' path 
    ```java
    Dataset train = LoadMNIST.load("./data/train-images-idx3-ubyte", "./data/train-labels-idx1-ubyte");
    Dataset test = LoadMNIST.load("./data/t10k-images-idx3-ubyte", "./data/t10k-labels-idx1-ubyte");
    ```
### Train object
1. Make new Train object and pass NeuralNetwork object 
    ```java
    Train tr = new Train(nn);
    ```
2. Pass InitializeMethod object
    * To Init newly
        ```java
        tr.Initialize(new HeInitialization());
      ```
    * To Init from ./Settings/settings.bin
        ```java
        tr.Initialize(new LoadSettingFile());
        ```
3. Pass train/test Dataset object
    ```java
   tr.setTrainDataset(train);
   tr.setTestDataset(test);
    ```
4. Run Train object
    * To train
    ```java
    tr.run(4, 5, true); // tr.run(batch size, epoch size, backup or not) 
    ```
   backup datas are saved as yyyyMMdd_HHmmss_backup_settings.bin
    * Just testing
    ```java
    tr.runTest();
    ```
5. Save trained weight and bias at ./Settings/setting.bin
```java
Save.save(nn);
```

## Packages
### neuralnetwork.activatefunction
### neuralnetwork.calculate
### neuralnetwork.dataset
### neuralnetwork.dataload
### neuralnetwork.initailizemethod
### neuralnetwork.inputfunction
### neuralnetwork.layer
### neuralnetwork.machinelearning