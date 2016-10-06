/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.pca;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.DenseMatrix;

import stockpriceprediction.helper.Pair;

/**
 *
 * @author beekill
 */
public class PCAHelperTest {
    
    public PCAHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of bubbleSort method, of class PCAHelper.
     */
    @Test
    public void testBubbleSort() {
        System.out.println("bubbleSort");
        Pair[] array = new Pair[] {
            new Pair<Vector, Double>(null, 0.5),
            new Pair<Vector, Double>(null, 1.5),
            new Pair<Vector, Double>(null, -15.0),
            new Pair<Vector, Double>(null, 25.0),
            new Pair<Vector, Double>(null, 15.0),
            new Pair<Vector, Double>(null, 10.0),
            new Pair<Vector, Double>(null, 8.0),
            new Pair<Vector, Double>(null, -25.0),
            new Pair<Vector, Double>(null, 50.0)
        };
        PCAHelper.bubbleSort(array);
        
        System.out.println("* Testing PCAHelper: bubbleSort");
        for (int i = 0; i < array.length - 1; ++i) {
            Pair<Vector, Double> former = array[i];
            Pair<Vector, Double> latter = array[i + 1];
            
            assertTrue("Test FAILED at " + i, former.second > latter.second);
        }
    }

    /**
     * Test of convertLa4jEigenMatrixToPair method, of class PCAHelper.
     */
    @Test
    public void testConvertLa4jEigenMatrixToPair() {
        System.out.println("convertLa4jEigenMatrixToPair");
        Matrix[] matrix = {
            DenseMatrix.from2DArray(new double[][] {
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6},
                {3, 4, 5, 6, 7},
                {4, 5, 6, 7, 8},
                {5, 6, 7, 8, 9}
            }),
            DenseMatrix.diagonal(5, 10)
        };
        Pair<Vector, Double>[] expResult = new Pair[] {
            new Pair<>(Vector.fromArray(new double[] {1, 2, 3, 4, 5}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {2, 3, 4, 5, 6}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {3, 4, 5, 6, 7}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {4, 5, 6, 7, 8}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {5, 6, 7, 8, 9}), 10.0)
        };
        Pair<Vector, Double>[] result = PCAHelper.convertLa4jEigenMatrixToPair(matrix);
        
        for (int i = 0; i < expResult.length; ++i) {
            assertArrayEquals(expResult[i].first.toDenseVector().toArray(), result[i].first.toDenseVector().toArray(), 0.1);
            assertTrue(Math.abs(expResult[i].second - result[i].second) < 0.1);
        }
    }

    /**
     * Test of convertEigenPairsToLa4jMatrix method, of class PCAHelper.
     */
    @Test
    public void testConvertEigenPairsToLa4jMatrix() {
        System.out.println("convertEigenPairsToLa4jMatrix");
        Pair<Vector, Double>[] eigenVectorsValues = new Pair[] {
            new Pair<>(Vector.fromArray(new double[] {1, 2, 3, 4, 5}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {2, 3, 4, 5, 6}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {3, 4, 5, 6, 7}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {4, 5, 6, 7, 8}), 10.0),
            new Pair<>(Vector.fromArray(new double[] {5, 6, 7, 8, 9}), 10.0)
        };
        Matrix expResult = DenseMatrix.from2DArray(new double[][] {
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6},
                {3, 4, 5, 6, 7},
                {4, 5, 6, 7, 8},
                {5, 6, 7, 8, 9}
        });
        
        Matrix result = PCAHelper.convertEigenPairsToLa4jMatrix(eigenVectorsValues);
        
        assertTrue(expResult.equals(result, 0.001));
    }
    
}
