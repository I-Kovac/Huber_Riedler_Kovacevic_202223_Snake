package com.example.huber_riedler_kovacevic_202223_snake.model;

/**
 * Repräsentiert eine einzelne Position auf dem Spielfeld
 * Eigenschaften:
 *  col: Spalte
 *  row: Zeile
 */
public class Position {
    private int col;
    private int row;

    /**
     * Kostruktor
     * @param col Spalte
     * @param row Zeile
     */
    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * Getter von col
     * @return Spalte
     */
    public int getCol() {
        return col;
    }

    /**
     * Getter von row
     * @return Zeile
     */
    public int getRow() {
        return row;
    }

    /**
     * Setter von col
     * @param col Spalte
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Setter von row
     * @param row Zeile
     */
    public void setRow(int row) {
        this.row = row;
    }
}
