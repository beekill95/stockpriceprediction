/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.helper;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author beekill
 */
public class BasicStatisticsTest {
    
    public BasicStatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of mean method, of class BasicStatistics.
     */
    @Test
    public void testMean() {
        System.out.println("mean");
        double[] values = new double[] {2.1, 2.5, 4.0, 3.6};
        double expResult = 3.05;
        double result = BasicStatistics.mean(values);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of standardDeviation method, of class BasicStatistics.
     */
    @Test
    public void testStandardDeviation() {
        System.out.println("standardDeviation");
        double[] values = new double[] {2.1, 2.5, 4.0, 3.6};
        double expResult = 0.8962886;
        double result = BasicStatistics.standardDeviation(values);
        assertEquals(expResult, result, 0.00005);
    }

    /**
     * Test of variance method, of class BasicStatistics.
     */
    @Test
    public void testVariance() {
        System.out.println("variance");
        double[] values = new double[] {2.1, 2.5, 4.0, 3.6};
        double expResult = 0.80333;
        double result = BasicStatistics.variance(values);
        assertEquals(expResult, result, 0.000005);
    }

    /**
     * Test of covariance method, of class BasicStatistics.
     */
    @Test
    public void testCovariance() {
        System.out.println("covariance");
        double[] x = new double[] {65.21, 64.75, 65.26, 65.76, 65.96};
        double[] y = new double[] {67.25, 66.39, 66.12, 65.70, 66.64};
        double expResult = -0.058;
        double result = BasicStatistics.covariance(x, y);
        assertEquals(expResult, result, 0.0005);
    }

    /**
     * Test of correlation method, of class BasicStatistics.
     */
    @Test
    public void testCorrelation() {
        System.out.println("correlation");
        double[] x = new double[] {14.2, 16.4, 11.9, 15.2, 18.5, 22.1, 19.4, 25.1, 23.4, 18.1, 22.6, 17.2};
        double[] y = new double[] {215, 325, 185, 332, 406, 522, 412, 614, 544, 421, 445, 408};
        double expResult = 0.9575;
        double result = BasicStatistics.correlation(x, y);
        assertEquals(expResult, result, 0.00005);
    }

    /**
     * Test of covarianceMatrix method, of class BasicStatistics.
     */
    @Test
    public void testCovarianceMatrix() {
        System.out.println("covarianceMatrix");
        double[][] values = new double[][] {
            {4.0, 4.2, 3.9, 4.3, 4.1},
            {2.0, 2.1, 2.0, 2.1, 2.2},
            {0.6, 0.59, 0.58, 0.62, 0.63}
        };
        double[][] expResult = new double[][] {
            {0.025, 0.0075, 0.00175},
            {0.0075, 0.0070, 0.00135},
            {0.00175, 0.00135, 0.00043}
        };
        double[][] result = BasicStatistics.covarianceMatrix(values);
        
        for (int i = 0; i < expResult.length; ++i) {
            assertArrayEquals(expResult[i], result[i], 0.00005);
        }
    }
    
    @Test
    public void testCorrelationMatrix() {
        System.out.println("correlationMatrix");
        double[][] values = new double[][] {
            {1, 4, 7},
            {6, 5, 4},
            {9, 5, 1}
        };
        double[][] expResult = new double[][] {
            {1, -1, -1},
            {-1, 1, 1},
            {-1, 1, 1}
        };
        double[][] result = BasicStatistics.correlationMatrix(values);
        
        for (int i = 0; i < expResult.length; ++i) {
            assertArrayEquals(expResult[i], result[i], 0.00005);
        }
    }
}
