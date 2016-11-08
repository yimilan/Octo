/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octo;

/**
 *
 * @author anyel
 */
public class Tablero {

    public int FIL = 13;
    public int COL = 9;
    public int[][] tablero = new int[][]{
        {0, 0, 0, 0, 2, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 0},
        {3, 1, 1, 1, 1, 1, 1, 1, 3},
        {1, 1, 1, 0, 1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 1, 1, 1, 1, 1, 1, 1, 2},
        {0, 1, 1, 1, 1, 1, 1, 1, 0},
        {0, 0, 1, 1, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 3, 0, 0, 0, 0}
    };

}
