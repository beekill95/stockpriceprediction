/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.datapreprocessing.normalization;

import stockpriceprediction.helper.BasicStatistics;

/**
 *
 * @author beekill
 */
public interface DataNormalization {
    double[][] normalizeData(double[][] data);
    double normalizeNewData(int index, double newData);
    double toOriginalData(int index, double normalizedData);
}
