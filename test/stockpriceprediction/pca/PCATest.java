/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.pca;

import stockpriceprediction.datapreprocessing.pca.PCA;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stockpriceprediction.helper.Pair;

import org.la4j.Vector;
import org.la4j.Matrix;
import org.la4j.matrix.DenseMatrix;
import org.la4j.vector.DenseVector;

/**
 *
 * @author beekill
 */
public class PCATest {
    
    PCA instance;
    
    public PCATest() {
        double[][] data = new double[][] {
            {1.06, 0.89, 1.43, 1.02, 1.49, 1.32, 1.22, 1.1, 1.34, 1.12, 0.75, 1.13, 1.15, 1.09, 0.96, 1.16, 0.76, 1.05, 1.16, 1.2, 1.04, 1.07},
            {9.2, 10.3, 15.4, 11.2, 8.8, 13.5, 12.2, 9.2, 13, 12.4, 7.5, 10.9, 12.7, 12, 7.6, 9.9, 6.4, 12.6, 11.7, 11.8, 8.6, 9.3},
            {151, 202, 113, 168, 192, 111, 175, 245, 168, 197, 173, 178, 199, 96, 164, 252, 136, 150, 104, 148, 204, 174},
            {54.4, 57.9, 53, 56, 51.2, 60, 67.6, 57, 60.4, 53, 51.5, 62, 53.7, 49.8, 62.2, 56, 61.9, 56.7, 54, 59.9, 61, 54.3},
            {1.6, 2.2, 3.4, 0.3, 1, -2.2, 2.2, 3.3, 7.2, 2.7, 6.5, 3.7, 6.4, 1.4, -0.1, 9.2, 9, 2.7, -2.1, 3.5, 3.5, 5.9},
            {9077, 5088, 9212, 6423, 3300, 11127, 7642, 13082, 8406, 6455, 17441, 6154, 7179, 9673, 6468, 15991, 5714, 10140, 13507, 7287, 6650, 10093},
            {0, 25.3, 0, 34.3, 15.6, 22.5, 0, 0,0, 39.2, 0, 0, 50.2, 0, 0.9, 0, 8.3, 0, 0, 41.1, 0, 26.6},
            {0.628, 1.555, 1.058, 0.7, 2.044, 1.241, 1.652, 0.309, 0.862, 0.623, 0.768, 1.897, 0.527, 0.588, 1.4, 0.62, 1.92, 1.108, 0.636, 0.702, 2.116, 1.306}
        };
        
        instance = new PCA(data, 0.9);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getEigenVectorsValues method, of class PCA.
     */
    @Test
    public void testGetEigenVectorsValues() {
        System.out.println("getEigenVectorsValues");
        Pair[] expResult = null;
        Pair[] result = instance.getEigenVectorsValues();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReducedEigenVectorsValues method, of class PCA.
     */
    @Test
    public void testGetReducedEigenVectorsValues() {
        System.out.println("getReducedEigenVectorsValues");
        Pair[] expResult = null;
        Pair[] result = instance.getReducedEigenVectorsValues();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataInReducedPrincipalComponents method, of class PCA.
     */
    @Test
    public void testGetDataInReducedPrincipalComponents() {
        System.out.println("getDataInReducedPrincipalComponents");
        double[][] expResult = null;
        double[][] result = instance.getDataInReducedPrincipalComponents();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 
    @Test
    // only work with symmetric matrix
    // which is fine because correlation matrix is symmetric
    public void testCalculateEigenvectorsAndEigenvalues() {
        System.out.println("calculateEigenvectorsAndEigenvalues");
        double[][] input = new double[][] {
            {3, 2, 4},
            {2, 0, 2},
            {4, 2, 3}
        };
        Matrix m = DenseMatrix.from2DArray(input);
        Pair<Vector, Double>[] result = instance.calculateEigenvectorsAndEigenvalues(input);
        
        for (int i = 0; i < result.length; ++i) {
            Vector v = result[i].first;
            double val = result[i].second;
            
            Vector r = m.multiply(v);
            r = r.subtract(v.multiply(val));
            
            assertTrue(r.equals(Vector.zero(v.length()), 0.00001));
        }
    }
}
