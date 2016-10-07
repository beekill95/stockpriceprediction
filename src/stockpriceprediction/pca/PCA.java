/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.pca;

import stockpriceprediction.helper.BasicStatistics;
import stockpriceprediction.helper.Pair;

import org.la4j.decomposition.EigenDecompositor;
import org.la4j.matrix.DenseMatrix;
import org.la4j.Matrix;
import org.la4j.Vector;

import java.util.ArrayList;

/**
 *
 * @author beekill
 */

/**
 * Steps for principal component analysis
 * 1. Prepare the data:
 *  - Center the data: subtract the mean from each variables. This produces a data set whose mean is zero
 *  - Scale the data: if the variance of the variables in your data are significantly different, it's a good idea
 *  to scale the data to unit variance. This is achieved by dividing each variables by its standard deviation
 * 2. Calculate the covariance/correlation matrix
 * 3. Calculate the eigenvectors and the eigenvalues of the covariance matrix
 * 4. Choose pricipal components: eigenvectors are ordered by eigenvalues from highest to the lowest. The number of
 *  chosen eigenvectors will be the number of dimensions of the new dataset. eigenvectors = (eig_1, eig_2, ..., eig_n)
 * 5. Compute the new data set:
 *  - Transpose eigenvectors: rows are eigenvectors
 *  - Transpose the adjusted data (rows are variables and columns are individuals)
 *  - New data = eigenvectors(transposed) * adjustedData(transposed)
 */

public class PCA {    
    private final double[][] normalizedDataSet;
    
    private final Pair<Vector, Double>[] originalEigenVectorsValues;
    private final Pair<Vector, Double>[] reducedEigenVectorsValues;
    
    public PCA(double[][] normalizedDataSet, double cummulativePercentageThreshold) {
        this.normalizedDataSet = normalizedDataSet;
        double[][] covarianceMatrix = BasicStatistics.covarianceMatrix(normalizedDataSet);
        
        originalEigenVectorsValues = calculateEigenvectorsAndEigenvalues(covarianceMatrix);
        PCAHelper.bubbleSort(originalEigenVectorsValues);
        
        reducedEigenVectorsValues = reducePrincipalComponents(originalEigenVectorsValues, cummulativePercentageThreshold);
    }
    
    public Pair<Vector, Double>[] getEigenVectorsValues() {
        return originalEigenVectorsValues;
    }
    
    public Pair<Vector, Double>[] getReducedEigenVectorsValues() {
        return reducedEigenVectorsValues;
    }
    
    public double[][] getDataInReducedPrincipalComponents() {
        // convert to matrix
        Matrix eigenVectorsMatrix = PCAHelper.convertEigenPairsToLa4jMatrix(reducedEigenVectorsValues);
        
        // convert data to matrix
        Matrix dataMatrix = DenseMatrix.from2DArray(normalizedDataSet);
        
        // convert to principal componenents
        Matrix dataSetInPC = eigenVectorsMatrix.multiply(dataMatrix);
        
        return dataSetInPC.toDenseMatrix().toArray();
    }
    
    // making public for testing
    public Pair<Vector, Double>[] calculateEigenvectorsAndEigenvalues(double[][] matrix) {
        DenseMatrix covarianceMatrix = DenseMatrix.from2DArray(matrix);
        
        // decompose covarianceMatrix
        EigenDecompositor decompositor = new EigenDecompositor(covarianceMatrix);
        Matrix[] eigenvectors_values = decompositor.decompose();
        
        return PCAHelper.convertLa4jEigenMatrixToPair(eigenvectors_values);
    }
    
    private Pair<Vector, Double>[] reducePrincipalComponents(Pair<Vector, Double>[] sortedEigenVectorsValues, double cummulativePercentageThreshold) {
        double accumulateEigenValue = 0.0;
        for (int i = 0; i < sortedEigenVectorsValues.length; ++i)
            accumulateEigenValue += sortedEigenVectorsValues[i].second;
        
        ArrayList<Pair<Vector, Double>> reducedEigenVectorsValues = new ArrayList<>();
        
        double accumulateContributionRate = 0.0;
        int i = 0;
        while (accumulateContributionRate < cummulativePercentageThreshold) {
            reducedEigenVectorsValues.add(sortedEigenVectorsValues[i]);
            accumulateContributionRate += sortedEigenVectorsValues[i].second / accumulateEigenValue;
            ++i;
        }
        
        return (Pair<Vector, Double>[])reducedEigenVectorsValues.toArray(
                new Pair[reducedEigenVectorsValues.size()]);
    }
}
