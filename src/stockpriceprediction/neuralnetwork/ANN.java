/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.neuralnetwork;

/**
 *
 * @author Van
 */
public class ANN {

    private class Tuple {

        double[] inputArr;
        double expectedOutput;
        int numInput;

        public Tuple(double[] input) {
            // pass row including output -> 
            numInput = input.length - 1;
            inputArr = new double[numInput];
            expectedOutput = input[input.length - 1];
            for (int i = 0; i < numInput; i++) {
                inputArr[i] = input[i];
            }
        }
    };

    private class Layer {

        int numPerceptrons;
        Perceptron[] perceptronLst;
        double[] inputArr;
        double[] outputArr;

        public Layer(int numOfPerceptrons, int numInput) {
            numPerceptrons = numOfPerceptrons;
            perceptronLst = new Perceptron[numPerceptrons];
            inputArr = new double[numInput];
            outputArr = new double[numPerceptrons];
            for (int i = 0; i < numPerceptrons; i++) {
                perceptronLst[i] = new Perceptron(numInput);
            }
        }

        // this function use to input data to first layer
        public void copy(Tuple row) {
            for (int i = 0; i < row.numInput; i++) {
                inputArr[i] = row.inputArr[i];
            }
        }

        // previous layer's output is current layer's input
        public void copy(double[] outputArr) {
            for (int i = 0; i < outputArr.length; i++) {
                inputArr[i] = outputArr[i];
            }
        }
    };
    /**
     * @param args the command line arguments
     */
    // these attributes are read from configural file
    final int NUM_INPUT;
    final int NUM_LAYER;
    final int NUM_LOOP;
    final double ALPHA;
    final double THRESHOLD;
    final int[] numPerEachLayer;
    // table for storing training data
    Tuple[] table;
    // ann
    Layer[] ann;

    public ANN() {
        // TODO: read from config
        NUM_INPUT = 1;
        NUM_LAYER = 2;
        ALPHA = 0.5;
        NUM_LOOP = 10000;
        THRESHOLD = 0.003;
        numPerEachLayer = new int[NUM_LAYER];

        // hidden layer
        numPerEachLayer[0] = 4;
        // maybe put some layer here
        // .....
        //    numPerEachLayer[1] = 10;
        //     numPerEachLayer[2] = 5;
        // last layer is the ouput
        numPerEachLayer[NUM_LAYER - 1] = 1;

        ann = new Layer[NUM_LAYER];

        // initialize ann
        // for first layer
        ann[0] = new Layer(numPerEachLayer[0], NUM_INPUT);
        for (int i = 1; i < NUM_LAYER; i++) {
            // number of input of each perceptron equal to number of perceptron 
            // in previous layer
            ann[i] = new Layer(numPerEachLayer[i], numPerEachLayer[i - 1]);
        }
    }

    public void readFile(double[][] arr) {
        // each row is a tuple
        table = new Tuple[arr.length];
        for (int row = 0; row < arr.length; row++) {
            table[row] = new Tuple(arr[row]);
        }
    }
    public double min = Double.MAX_VALUE;

    public void run() {
        for (int loop = 0; loop < NUM_LOOP; loop++) {
            // for each tuple
            for (int row = 0; row < table.length; row++) {

                // for each layer
                for (int i = 0; i < NUM_LAYER; i++) {
                    // set up input array for layer
                    // first layer will get imput from table
                    // other layer will get from output of previous layer
                    if (i == 0) {
                        ann[i].copy(table[row]);
                    } else {
                        ann[i].copy(ann[i - 1].outputArr);
                    }

                    // for each perceptron
                    // compute output base on currently weight
                    for (int j = 0; j < ann[i].numPerceptrons; j++) {
                        ann[i].outputArr[j] = ann[i].perceptronLst[j].getOutput(ann[i].inputArr);
                    }
                    if (i == NUM_LAYER - 1) {
                        //                   System.out.println("out put: " + ann[i].outputArr[0]);
                    }
                }
                // error for output(last) layer
                for (int j = 0; j < ann[NUM_LAYER - 1].numPerceptrons; j++) {
                    double output = ann[NUM_LAYER - 1].outputArr[j];
                    ann[NUM_LAYER - 1].perceptronLst[j].error = output * (1 - output) * (table[row].expectedOutput - output);
                    //                            System.out.println("input: " + table[row].row[0] + " expected: " + table[row].row[table[row].numInput]);
   //                 System.out.println("Input: " + table[row].expectedOutput + " output: " + output);
                }

                // compute error for hidden layer
                // from last to first hidden
                for (int l = NUM_LAYER - 2; l >= 0; l--) {
                    for (int j = 0; j < ann[l].numPerceptrons; j++) {
                        double output = ann[l].outputArr[j];

                        double sumErr = 0;
                        for (int k = 0; k < ann[l + 1].numPerceptrons; k++) {
                            // TODO: check if get w[j] of layer l or (l + 1)
                            sumErr += ann[l + 1].perceptronLst[k].error * ann[l + 1].perceptronLst[k].w[j];
                        }
                        ann[l].perceptronLst[j].error = output * (1 - output) * sumErr;
                    }
                }
                // update weight and bias
                for (int i = 0; i < NUM_LAYER; i++) {
                    for (int j = 0; j < ann[i].numPerceptrons; j++) {
                        for (int k = 0; k < ann[i].perceptronLst[j].w.length; k++) {
                            double deltaWeight = ALPHA * ann[i].perceptronLst[j].error * ann[i].inputArr[k];
                            ann[i].perceptronLst[j].w[k] += deltaWeight;
                        }
//                        ann[i].perceptronLst[j].error += deltaWeight;

                        double deltaBias = ALPHA * ann[i].perceptronLst[j].error;
                        ann[i].perceptronLst[j].bias += deltaBias;
                    }
                }
            }
            // all of tuple have trained
            // compute sum of error          
            double sumEr = 0;
            for (int row = 0; row < table.length; row++) {
                for (int i = 0; i < NUM_LAYER; i++) {
                    if (i == 0) {
                        ann[i].copy(table[row]);
                    } else {
                        ann[i].copy(ann[i - 1].outputArr);
                    }
                    for (int j = 0; j < ann[i].numPerceptrons; j++) {
                        ann[i].outputArr[j] = ann[i].perceptronLst[j].getOutput(ann[i].inputArr);
                    }
                }
                double out = ann[NUM_LAYER - 1].outputArr[0];
                double t = table[row].expectedOutput;
                sumEr += Math.abs(out - t);
            }
            // stop training condition
            //        System.out.println("sum err: " + sumEr);
            if (min > sumEr) {
                min = sumEr;
                System.out.println(" min: " + min);
            }
            if (sumEr <= THRESHOLD) {
                System.out.println("STOP WITH THRESHOLD");
                break;
            }
        }
    }

    int[] genFibo() {
        int N = 30;
        int[] fibo = new int[N];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i < N; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        return fibo;
    }

    public void predict(double[][] arr) {
        // 
        for (int row = 0; row < arr.length; row++) {
            Tuple tuple = new Tuple(arr[row]);
            for (int i = 0; i < NUM_LAYER; i++) {
                if (i == 0) {
                    ann[i].copy(tuple);
                } else {
                    ann[i].copy(ann[i - 1].outputArr);
                }
                for (int j = 0; j < ann[i].numPerceptrons; j++) {
//                    System.out.println("weight");
//                    for (int n = 0; n < ann[i].perceptronLst[j].w.length; n++) {
//                        System.out.print(" " + ann[i].perceptronLst[j].w[n]);
//                    }
                    ann[i].outputArr[j] = ann[i].perceptronLst[j].getOutput(ann[i].inputArr);
//                    System.out.print(" " + ann[i].outputArr[j]);
//                    System.out.println("");
                }
            }
            System.out.println("expected: "+ tuple.expectedOutput + " Output is: " + ann[NUM_LAYER - 1].outputArr[0]);
            System.out.println("expected: "+ row + " Output is: " + ann[NUM_LAYER - 1].outputArr[0]*1000);
        }
    }

    public static double[][] genTest() {
        double arr[][] = new double[1000][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = (double)i / 1000;
            arr[i][1] = (double)i / 1000;
            //      System.out.println(arr[i][1]);
        }
        return arr;
    }

    public static void main(String[] args) {
        ANN ann = new ANN();
        //    double[][] arr = {{1, 0, 0}, {0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
        // gen test
        double[][] arr = genTest();
        ann.readFile(arr);
        ann.run();

        //     double[][] arr1 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        ann.predict(arr);

    }
}
