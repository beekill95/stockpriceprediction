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
public class Perceptron {

     int numInput;
    double[] w;
    // array of input
    double[] x;
    double bias;
    double error;
    
    public Perceptron(int numInput) {
        this.numInput = numInput;   
        w = new double[this.numInput];
        x = new double[this.numInput];
        error = 0;
        bias = 0;//Math.random();
        for (int i = 0; i < this.numInput; i++) {
            w[i] =0;// Math.random();
        }
    }

    public double getOutput(double[] x) {
        double net = 0;
        for (int i = 0; i < numInput; i++) {
            net += x[i] * w[i];
        }
        return getSigmoid(net + bias);
    }
    public double getErr(double net){
        double err = getSigmoid(net);
        return err*(1-err);
    }

    public double getSigmoid(double net) {
//        System.out.println("ann.Perceptron.getSigmoid(): "+1 / (1 + Math.exp(-net)));
        return 1 / (1 + Math.exp(-net));
    }

}
