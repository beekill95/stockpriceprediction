/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.datapreprocessing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author beekill
 */
public class ZeroToOneNormalizationTest {
    
    private ZeroToOneNormalization instance;
    
    public ZeroToOneNormalizationTest() {
        instance = new ZeroToOneNormalization();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of normalizeData method, of class ZeroToOneNormalization.
     */
    @Test
    public void testNormalizeData() {
        System.out.println("normalizeData");
        double[][] data = new double[][] {
            {1.06, 0.89, 1.43, 1.02, 1.49, 1.32},
            {9.2, 10.3, 15.4, 11.2, 8.8, 13.5},
            {151, 202, 113, 168, 192, 111},
            {54.4, 57.9, 53, 56, 51.2, 60}
        };
        double[][] expResult = new double[][] {
            
        };
        double[][] result = instance.normalizeData(data);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalizeNewData method, of class ZeroToOneNormalization.
     */
    @Test
    public void testNormalizeNewData() {
        System.out.println("normalizeNewData");
        int index = 0;
        double newData = 0.0;
        double expResult = 0.0;
        double result = instance.normalizeNewData(index, newData);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toOriginalData method, of class ZeroToOneNormalization.
     */
    @Test
    public void testToOriginalData() {
        System.out.println("toOriginalData");
        int index = 0;
        double normalizedData = 0.0;
        double expResult = 0.0;
        double result = instance.toOriginalData(index, normalizedData);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
