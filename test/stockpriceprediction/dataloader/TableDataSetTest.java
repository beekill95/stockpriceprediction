/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.dataloader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stockpriceprediction.helper.Pair;

/**
 *
 * @author beekill
 */
public class TableDataSetTest {
    
    public TableDataSetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setColumnNames method, of class TableDataSet.
     */
    @Test
    public void testSetColumnNames() {
        System.out.println("setColumnNames");
        String[] columnNames = null;
        TableDataSet instance = new TableDataSet();
        instance.setColumnNames(columnNames);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnNames method, of class TableDataSet.
     */
    @Test
    public void testGetColumnNames() {
        System.out.println("getColumnNames");
        TableDataSet instance = new TableDataSet();
        String[] expResult = null;
        String[] result = instance.getColumnNames();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRow method, of class TableDataSet.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        int rowIndex = 0;
        TableDataSet instance = new TableDataSet();
        Pair expResult = null;
        Pair result = instance.getRow(rowIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRow method, of class TableDataSet.
     */
    @Test
    public void testAddRow() {
        System.out.println("addRow");
        Pair attributes = null;
        TableDataSet instance = new TableDataSet();
        instance.addRow(attributes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumn method, of class TableDataSet.
     */
    @Test
    public void testGetColumn_int() {
        System.out.println("getColumn");
        int columnIndex = 0;
        TableDataSet instance = new TableDataSet();
        double[] expResult = null;
        double[] result = instance.getColumn(columnIndex);
        assertArrayEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumn method, of class TableDataSet.
     */
    @Test
    public void testGetColumn_String() {
        System.out.println("getColumn");
        String columnName = "";
        TableDataSet instance = new TableDataSet();
        double[] expResult = null;
        double[] result = instance.getColumn(columnName);
        assertArrayEquals(expResult, result, 0.0);
    }

    /**
     * Test of getColumnIndexOf method, of class TableDataSet.
     */
    @Test
    public void testGetColumnIndexOf() {
        System.out.println("getColumnIndexOf");
        String columnName = "";
        TableDataSet instance = new TableDataSet();
        int expResult = 0;
        int result = instance.getColumnIndexOf(columnName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("Untested");
    }

    /**
     * Test of getNumOfColumns method, of class TableDataSet.
     */
    @Test
    public void testGetNumOfColumns() {
        System.out.println("getNumOfColumns");
        TableDataSet instance = new TableDataSet();
        int expResult = 0;
        int result = instance.getNumOfColumns();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("Untested");
    }

    /**
     * Test of getNumOfRows method, of class TableDataSet.
     */
    @Test
    public void testGetNumOfRows() {
        System.out.println("getNumOfRows");
        TableDataSet instance = new TableDataSet();
        int expResult = 0;
        int result = instance.getNumOfRows();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("Untested");
    }

    /**
     * Test of to2dArrayRecordEach method, of class TableDataSet.
     */
    @Test
    public void testTo2dArrayRecordEach() {
        System.out.println("to2dArrayRecordEach");
        TableDataSet instance = new TableDataSet();
        instance.addRow(new Pair<>("Quan", new double[] {1, 2, 3, 4, 5}));
        instance.addRow(new Pair<>("Dep", new double[] {3, 4, 5, 6, 7}));
        instance.addRow(new Pair<>("Trai", new double[] {5, 6, 7, 8 , 9}));
        instance.addRow(new Pair<>("Qua", new double[] {7, 8, 9, 10, 11}));
        double[][] expResult = new double[][] {
            {1, 2, 3, 4, 5},
            {3, 4, 5, 6, 7},
            {5, 6, 7, 8, 9},
            {7, 8, 9, 10, 11}
        };
        double[][] result = instance.to2dArrayRecordEach();
        
        for (int i = 0; i < expResult.length; ++i)
            assertArrayEquals(expResult[i], result[i], 0.0);
    }

    /**
     * Test of to2dArrayFieldEach method, of class TableDataSet.
     */
    @Test
    public void testTo2dArrayFieldEach() {
        System.out.println("to2dArrayFieldEach");
        TableDataSet instance = new TableDataSet();
        instance.addRow(new Pair<>("Quan", new double[] {1, 2, 3, 4, 5}));
        instance.addRow(new Pair<>("Dep", new double[] {3, 4, 5, 6, 7}));
        instance.addRow(new Pair<>("Trai", new double[] {5, 6, 7, 8 , 9}));
        instance.addRow(new Pair<>("Qua", new double[] {7, 8, 9, 10, 11}));
        double[][] expResult = new double[][] {
            {1, 3, 5, 7},
            {2, 4, 6, 8},
            {3, 5, 7, 9},
            {4, 6, 8, 10},
            {5, 7, 9, 11}
        };
        double[][] result = instance.to2dArrayFieldEach();

        for (int i = 0; i < expResult.length; ++i)
            assertArrayEquals(expResult[i], result[i], 0.0);
    }
    
}
