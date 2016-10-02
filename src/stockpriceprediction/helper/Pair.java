/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockpriceprediction.helper;

/**
 *
 * @author beekill
 */
public class Pair<X, Y> {
    public final X first;
    public final Y second;
    
    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}
