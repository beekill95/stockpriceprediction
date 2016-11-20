/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.gui;

import java.io.File;
import java.lang.Thread;

import javax.swing.SwingUtilities;
import stockpriceprediction.helper.Pair;

import stockpriceprediction.neuralnetwork.ANN;
import static stockpriceprediction.neuralnetwork.ANN.divideTest;
import static stockpriceprediction.neuralnetwork.ANN.genSinTest;
import static stockpriceprediction.neuralnetwork.ANN.shuffle;

/**
 *
 * @author beekill
 */
public class MainWindow extends javax.swing.JFrame
        implements ResultPanel.ResultPanelInteractionHandler
{
    
    private ANN ann;
    private javax.swing.JFrame parent;
    private File networkModelFile;
    
    private final NeuralNetworkConfiguration defaultConfiguration = new NeuralNetworkConfiguration(
                0.1, 10000, 0.4, 0.0001, 0.9,
                2, new int[]{50, 40, 100, 50},
                0.0001
    );
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow(javax.swing.JFrame parent) {
        initComponents();
        
        this.parent = parent;
        networkModelPanel.setConfiguration(defaultConfiguration, null);
        resultPanel.setInteractionHandler(this);
    }
    
    public MainWindow(javax.swing.JFrame parent, File networkModelFile) {
        initComponents();
        
        this.parent = parent;
        this.networkModelFile = networkModelFile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        networkModelPanel = new stockpriceprediction.gui.NetworkModelPanel();
        dataSetPanel = new stockpriceprediction.gui.DataSetPanel();
        resultPanel = new stockpriceprediction.gui.ResultPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stock Price Prediction");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.addTab("Neural Network", networkModelPanel);
        jTabbedPane1.addTab("Data Set", dataSetPanel);
        jTabbedPane1.addTab("Result", resultPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.out.println("My close event handler is called");
        
        if (parent == null)
            System.exit(0);
        else {
            this.setVisible(false);
            parent.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosed

    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainWindow().setVisible(true);
//            }
//        });
//    }

    @Override
    public void onTrainButtonClicked() {
        double[][] trainingData = dataSetPanel.getTrainingData();
        final NeuralNetworkConfiguration currentConfig = networkModelPanel.getCurrentConfiguration();
        Pair<double[][], double[][]> data = divideTest(trainingData, currentConfig.getTrainingPercentage());
        if (trainingData != null /*true*/) {
            ann = new ANN(
                    /* num input */ trainingData.length - 1,
                    /* num layer */ currentConfig.getNumOfHiddenLayers(),
                    /* num loop */ currentConfig.getMaxIterations(),
                    /* learning rate */ currentConfig.getLearningRate(),
                    /* threshold */ currentConfig.getTrainingError(),
                    /* delta threshold */ currentConfig.getDeltaThreshold(),
                    /* number of node per each layer */ currentConfig.getHiddenLayerNodes()
            );
            
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    ann.readFile(data.first);
                    ann.run();
                    
                    // check the result
                    double[][] predictResult = new double[data.second.length][1];
                    for (int i = 0; i < data.second.length; ++i) {
                        double[][] row = new double[1][data.second[i].length];
                        row[0] = data.second[i];
                        
                        predictResult[i] = ann.predict(row);
                    }
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            double[] realValues = new double[trainingData.length];
                            for (int i = 0; i < trainingData.length; ++i)
                                realValues[i] = dataSetPanel.toOriginalData(trainingData[i][trainingData[0].length - 1]);
                            
                            double[] predictedValues = new double[predictResult.length];
                            for (int i = 0; i < predictedValues.length; ++i)
                                predictedValues[i] = dataSetPanel.toOriginalData(predictResult[i][0]);
                            
                            resultPanel.onTrainFinished(realValues, predictedValues);
                        }
                    });
                }
            };
            
//            Runnable r1 = new Runnable() {
//                @Override
//                public void run() {
//                    ANN ann = new ANN();
//                    
//                    double[][] arr = genSinTest(1000);
//                    shuffle(arr, 500);
//                    Pair<double[][], double[][]> data = divideTest(arr, 0.25);
//                    ann.readFile(data.first);
//                    ann.run();
//
//                    // check the result
//                    double[][] predictResult = new double[data.second.length][1];
//                    for (int i = 0; i < data.second.length; ++i) {
//                        double[][] row = new double[1][data.second[i].length];
//                        row[0] = data.second[i];
//                        
//                        predictResult[i] = ann.predict(row);
//                    }
//                    
//                    SwingUtilities.invokeLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            double[] realValues = new double[arr.length];
//                            for (int i = 0; i < arr.length; ++i)
//                                realValues[i] = arr[i][1];
//                            
//                            double[] predictedValues = new double[predictResult.length];
//                            for (int i = 0; i < predictedValues.length; ++i)
//                                predictedValues[i] = predictResult[i][0];
//                            
//                            resultPanel.onTrainFinished(realValues, predictedValues);
//                        }
//                    });
//                }
//            };
            
            Thread t = new Thread(r);
            t.start();
        }
    }

    @Override
    public void onPredictButtonClicked(double[] data) {
        double[] predictData = dataSetPanel.preprocessData(data);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private stockpriceprediction.gui.DataSetPanel dataSetPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private stockpriceprediction.gui.NetworkModelPanel networkModelPanel;
    private stockpriceprediction.gui.ResultPanel resultPanel;
    // End of variables declaration//GEN-END:variables
}
