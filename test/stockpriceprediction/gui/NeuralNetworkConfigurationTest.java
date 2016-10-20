/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.gui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 *
 * @author beekill
 */
public class NeuralNetworkConfigurationTest {
    
    private NeuralNetworkConfiguration configuration;
    private final int maxIterations = 300;
    private final double learningRate = 0.1;
    private final double trainingError = 0.5;
    private final double learningMomentum = 0.3;
    
    private final int numOfHiddenLayers = 3;
    private final int[] hiddenLayersNode = {
        10, 20, 30, 40
    };
    
    private final double trainingPercentage = 0.8;
    
    public NeuralNetworkConfigurationTest() {
    }
    
    @Before
    public void setUpConfiguration() {
        configuration = new NeuralNetworkConfiguration(
                learningRate, maxIterations, trainingError, learningMomentum, numOfHiddenLayers,
                hiddenLayersNode, trainingPercentage
        );
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getLearningRate method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetLearningRate() {
        System.out.println("getLearningRate");
        double expResult = 0.1;
        double result = configuration.getLearningRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLearningRate method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetLearningRate() {
        System.out.println("setLearningRate");
        double learningRate = 0.5;
        configuration.setLearningRate(learningRate);
        double expResult = configuration.getLearningRate();
        assertEquals(expResult, learningRate, 0.0);
    }

    /**
     * Test of getMaxIterations method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetMaxIterations() {
        System.out.println("getMaxIterations");
        int expResult = 300;
        int result = configuration.getMaxIterations();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaxIterations method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetMaxIterations() {
        System.out.println("setMaxIterations");
        int maxIterations = 50;
        configuration.setMaxIterations(maxIterations);
        
        int result = configuration.getMaxIterations();
        assertEquals(maxIterations, result);
    }

    /**
     * Test of getTrainingError method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetTrainingError() {
        System.out.println("getTrainingError");
        double expResult = 0.5;
        double result = configuration.getTrainingError();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTrainingError method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetTrainingError() {
        System.out.println("setTrainingError");
        double trainingError = 0.8;
        configuration.setTrainingError(trainingError);
        
        double result = configuration.getTrainingError();
        assertEquals(trainingError, result, 0.0);
    }

    /**
     * Test of getLearningMomentum method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetLearningMomentum() {
        System.out.println("getLearningMomentum");
        double expResult = 0.3;
        double result = configuration.getLearningMomentum();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLearningMomentum method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetLearningMomentum() {
        System.out.println("setLearningMomentum");
        double learningMomentum = 0.5;
        configuration.setLearningMomentum(learningMomentum);
    
        double result = configuration.getLearningMomentum();
        assertEquals(learningMomentum, result, 0.0);
    }

    /**
     * Test of getNumOfHiddenLayers method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetNumOfHiddenLayers() {
        System.out.println("getNumOfHiddenLayers");
        int expResult = 3;
        int result = configuration.getNumOfHiddenLayers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumOfHiddenLayers method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetNumOfHiddenLayers() {
        System.out.println("setNumOfHiddenLayers");
        int numOfHiddenLayers = 2;
        configuration.setNumOfHiddenLayers(numOfHiddenLayers);

        int result = configuration.getNumOfHiddenLayers();
        assertEquals(numOfHiddenLayers, result);
    }

    /**
     * Test of getHiddenLayerNodes method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetHiddenLayerNodes() {
        System.out.println("getHiddenLayerNodes");
        int[] expResult = {10, 20, 30};
        int[] result = configuration.getHiddenLayerNodes();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setHiddenLayerNodes method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetHiddenLayerNodesWithNullArrays() {
        System.out.println("testSetHiddenLayerNodesWithNullArrays");
        configuration.setHiddenLayerNodes(null);
        
        int[] expResult = {10, 20, 30};
        int[] result = configuration.getHiddenLayerNodes();
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testSetHiddenLayerNodesWithArraySizeLessThanNumOfHiddenLayer() {
        System.out.println("testSetHiddenLayerNodesWithArraySizeLessThanNumOfHiddenLayer");
        int currentArraySize = configuration.getNumOfHiddenLayers();
        
        int[] newArray = {10, 20};
        int newArraySize = newArray.length;
        
        assertTrue(newArraySize < currentArraySize);
        configuration.setHiddenLayerNodes(newArray);
        
        int[] expResult = {10, 20, 30};
        int[] result = configuration.getHiddenLayerNodes();
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testSetHiddenLayerNodesWithArraySizeEqualNumOfHiddenLayer() {
        System.out.println("testSetHiddenLayerNodesWithArraySizeEqualNumOfHiddenLayer");
        int currentArraySize = configuration.getNumOfHiddenLayers();
        
        int[] newArray = {40, 50, 60};
        int newArraySize = newArray.length;
        assertEquals(currentArraySize, newArraySize);
        
        configuration.setHiddenLayerNodes(newArray);
        
        int[] result = configuration.getHiddenLayerNodes();
        assertArrayEquals(newArray, result);
    }
    
    @Test
    public void testSetHiddenLayerNodesWithArraySzeGreaterNumOfHiddenLayer() {
        System.out.println("testSetHiddenLayerNodesWithArraySzeGreaterNumOfHiddenLayer");
        int currentArraySize = configuration.getNumOfHiddenLayers();
        
        int[] newArray = {70, 80, 90, 100};
        int newArraySize = newArray.length;
        assertTrue(newArraySize > currentArraySize);
        
        configuration.setHiddenLayerNodes(newArray);
        
        int[] result = configuration.getHiddenLayerNodes();
        assertArrayEquals(Arrays.copyOf(newArray, currentArraySize), result);
    }

    /**
     * Test of getTrainingPercentage method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testGetTrainingPercentage() {
        System.out.println("getTrainingPercentage");
        double expResult = 0.8;
        double result = configuration.getTrainingPercentage();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTrainingPercentage method, of class NeuralNetworkConfiguration.
     */
    @Test
    public void testSetTrainingPercentage() {
        System.out.println("setTrainingPercentage");
        double trainingPercentage = 0.7;
        configuration.setTrainingPercentage(trainingPercentage);

        double result = configuration.getTrainingPercentage();
        assertEquals(trainingPercentage, result, 0.0);
    }
    
}
