# Java Neural Network
* Neural Network package written in Java
* MNIST/HandWriting.java is example using [MNIST dataset](http://yann.lecun.com/exdb/mnist/)
 
## Installation
Download neuralnetwork package
## Example
See example `/MNINST/HandWriting.java`
### Making NeuralNetwork Object
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
   
### Making Dataset object
* Make new Dataset object and set data files and label files' path 
    ```java
    Dataset train = LoadMNIST.load("./data/train-images-idx3-ubyte", "./data/train-labels-idx1-ubyte");
    Dataset test = LoadMNIST.load("./data/t10k-images-idx3-ubyte", "./data/t10k-labels-idx1-ubyte");
    ```
### Make Train object and train
1. Make new Train object and pass NeuralNetwork object 
    ```java
    Train tr = new Train(nn);
    ```
2. Pass InitializeMethod object
    * To init newly
        ```java
        tr.Initialize(new HeInitialization());
      ```
    * To init from ./Settings/settings.bin
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
   backup data are saved as yyyyMMdd_HHmmss_backup_settings.bin
    * Just testing
    ```java
    tr.runTest();
    ```
5. Save trained weight and bias at /Settings/setting.bin
```java
Save.save(nn);
```

## Documentation
### neuralnetwork.activatefunction
Provided activate functions: 
* ReLU `ReLU`
* Sigmoid `Sigmoid`
* Softmax   `Softmax`
* To add more, implement interface ActivateFunction

ex) `new ReLU()`
### neuralnetwork.dataload
Provided dataset styles:
* MNIST

#### neuralnetwork.dataload.LoadMNIST

`public static Dataset load(String dataFile, String labelFile)`

dataFile : path of data file

labelFile : path of label file
* Load dataset
* Prints the dataset's info


ex) `Dataset ds = LoadMNIST.load(dataFilePath, labelFilePath)`

### neuralnetwork.initailizemethod
Provided initialize method: 
* He initialization (uniform) `HeInitialization`
* Xavier initialization (uniform)  `XavierInitialization`
* Load settings.bin file   `LoadSettingFile`
* To add more, implement interface InitializeMethod

ex) `new HeInitialization()`
### neuralnetwork.inputfunction
Provided input function: 
* Input normalize `InputNormalize`
* To add more, implement interface InputFunction

ex) `new InputNormalize()`
### neuralnetwork.machinelearning
#### neuralnetwork.machinelearning.NeuralNetwork
`public NeuralNetwork(double learningRate)`

`public void addInputLayer(InputFunction inputFunction, int size)`

`public void addHiddenLayer(ActivateFunction function, int size)`

`public void addOutputLayer(ActivateFunction function, int size)`

size  : node size (Must be same with dataset size)

* Must add Input Layer, Hidden Layer(Optional), Output Layer in order
* NeuralNetwork must include 1 Input Layer, 1 Output Layer
* Hidden Layer can be added more than one or not added at all

ex)    
```java
NeuralNetwork nn = new NeuralNetwork(0.01);
nn.addInputLayer(new InputNormalize(), 784);    
nn.addHiddenLayer(new ReLU(), 400);
nn.addHiddenLayer(new ReLU(), 200);
nn.addOutputLayer(new Softmax(), 10);
``` 

#### neuralnetwork.machinelearning.Train
`public Train(NeuralNetwork neuralNetwork)`

`public void setTrainDataset(Dataset trainDataset)`

* If you are just testing, you don't need to set train dataset

`public void setTestDataset(Dataset testDataset)`

* Must add test dataset even if you are just want to train. 
 It tests network after training one epoch. 


`public void Initialize(InitializeMethod initializeMethod)`

* Setting initializing method for weight and bias.
* you can pass Initialize method for Initialize newly,
or you can load weight and bias value from /Settings/settings.bin with:
neuralnetwork.initializemethod.LoadSettingFile

`public void run(int batch, int epoch, boolean backup)`

`public void run(int batch, int epoch)`

batch : batch size (Batch size doesn't have to divide dataset equally, it adjusts automatically)

epoch : epoch size

backup : whether to back up. (back up when true) If the field is empty , it doesn't back up

* Trains the neural network
* Prints the network's structure and setting
* Test the network after training one epoch and prints result
* Require both train and test dataset

`public void runTest()`
* Just tests the network
* Do not require train dataset

ex)
* Train
```java
Train tr = new Train(nn);
tr.Initialize(new HeInitialization());
tr.setTrainDataset(train);
tr.setTestDataset(test);
tr.run(4, 5, true);
```

* Just test
```java
Train tr = new Train(nn);
tr.Initialize(new LoadSettingFile());
tr.setTestDataset(test);
tr.runTest();
```

#### neuralnetwork.machinelearning.Save
`public static void save(NeuralNetwork neuralNetwork)`
* Save the neural network's weight and bias to /Settings/settings.bin
