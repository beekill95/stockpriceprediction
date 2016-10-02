/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.dataloader;

import java.util.ArrayList;

import stockpriceprediction.helper.Pair;
/**
 *
 * @author beekill
 */
public class TableDataSet {
    
    private String[] columnNames;
    private ArrayList<Pair<String, double[]>> rows;
    private int numOfColumns = -1;
    
    public TableDataSet() {
        rows = new ArrayList<>();
    }
    
    public void setColumnNames(String[] columnNames) {
        if (numOfColumns == -1)
            numOfColumns = columnNames.length;
        else if (numOfColumns != columnNames.length)
            throw new RuntimeException("Column names don't match previously added rows");
        
        this.columnNames = columnNames;
    }
    
    public String[] getColumnNames() {
        return columnNames;
    }
    
    public Pair<String, double[]> getRow(int rowIndex) {
        return rows.get(rowIndex);
    }
    
    public void addRow(Pair<String, double[]> attributes) {
        if (numOfColumns == -1) {
            // first insertion
            numOfColumns = attributes.second.length + 1;
        } else if (numOfColumns != attributes.second.length + 1)
            throw new RuntimeException("Row doesn't match previously added rows");
        
        rows.add(attributes);
    }
    
    public double[] getColumn(int columnIndex) {
        if (columnIndex >= numOfColumns || columnIndex < 0)
            return null;
        
        double[] column = new double[rows.size()];
        for (int i = 0; i < rows.size(); ++i) {
            double[] col = rows.get(i).second;
            column[i] = col[columnIndex];
        }
        
        return column;
    }
    
    public double[] getColumn(String columnName) {
        if (columnNames == null)
            return null;
        
        int columnIndex = getColumnIndexOf(columnName);
        return getColumn(columnIndex);
    }
    
    public int getColumnIndexOf(String columnName) {
        int i = 0;
        for (String name : columnNames) {
            if (name.equals(columnName))
                return i;
            i++;
        }
        
        // not found
        return -1;
    }
    
    public int getNumOfColumns() {
        return numOfColumns;
    }
    
    public int getNumOfRows() {
        return rows.size();
    }
}
