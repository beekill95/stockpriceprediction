/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.dataloader;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import stockpriceprediction.helper.Pair;

/**
 *
 * @author beekill
 */
public class CsvDataSetLoader {
    static public TableDataSet loadDataSet(File dataFile, boolean hasHeaders) {
        TableDataSet dataSet = new TableDataSet();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(dataFile));
            String line = null;
            
            if (hasHeaders) {
                line = reader.readLine();
                String columnNames[] = readColumnNames(line);
                dataSet.setColumnNames(columnNames);
            }
            
            while ((line = reader.readLine()) != null) {
                Pair<String, double[]> row = readRow(line);
                dataSet.addRow(row);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) { }
        }
        
        return dataSet;
    }
    
    static private String[] readColumnNames(String line) {
        return line.split(",");
    }
    
    static private Pair<String, double[]> readRow(String line) {
        String[] columns = line.split(",");
        String[] doubleData = Arrays.copyOfRange(columns, 1, columns.length);
        return new Pair<>(columns[0], readDoubleData(doubleData));
    }
    
    static private double[] readDoubleData(String[] data) {
        double[] doubleData = new double[data.length];
        for (int i = 0; i < doubleData.length; ++i)
            doubleData[i] = Double.parseDouble(data[i]);
        return doubleData;
    }
}
