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
import stockpriceprediction.helper.Pair;

import stockpriceprediction.dataloader.CsvDataSetLoader;
import stockpriceprediction.dataloader.TableDataSet;

import org.la4j.Vector;
import org.la4j.Matrix;
import org.la4j.matrix.DenseMatrix;
import org.la4j.vector.DenseVector;

/**
 *
 * @author beekill
 */
public class PCATest {
    
    static String dataPath = "/home/beekill/learning/university/data_mining/bai_tap_lon/data/table.csv";
    TableDataSet dataSet;
    PCA instance;
    
    public PCATest() {
        instance = new PCA(new double[][] {
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5}
        }, 0);
        
        // load data
        dataSet = CsvDataSetLoader.loadDataSet(new java.io.File(dataPath), true);
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
