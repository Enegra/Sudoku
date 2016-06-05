package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by agnie on 4/5/2016.
 */
public class Sample {

    private int[][] content;
    private int base;
    private int rowLength;

    public Sample(int[][] sampleContent, int base) throws Exception {
        this.base = base;
        rowLength = base * base;
        if (!isValid(sampleContent)) throw new Exception("Invalid format");
        this.content = sampleContent;
    }

    public Sample(Sample another) {
        this.base = another.base;
        this.rowLength = another.rowLength;
        this.content = another.content;
    }


    public boolean isValid(int[][] sampleContent) {
        if (sampleContent.length != rowLength) {
            return false;
        }
        for (int i = 0; i < sampleContent.length; i++) {
            if (sampleContent[i].length != rowLength) {
                return false;
            }
        }
        return true;
    }

    public int[] getRow(int rowNumber) {
        return content[rowNumber];
    }

    public void setRow(int[] row, int rowNumber) {
        for (int i = 0; i < content[rowNumber].length; i++) {
            content[rowNumber][i] = row[i];
        }
    }

    public void resetRow(int rowNumber) {
        for (int i = 0; i < content[rowNumber].length; i++) {
            content[rowNumber][i] = 0;
        }
    }

    public int[] getColumn(int columnNumber) {
        int[] column = new int[rowLength];
        for (int i = 0; i < column.length; i++) {
            column[i] = content[i][columnNumber];
        }
        return column;
    }

    public void setColumn(int[] column, int columnNumber) {
        for (int i = 0; i < content.length; i++) {
            content[i][columnNumber] = column[i];
        }
    }

    public void resetColumn(int columnNumber){
        for (int i = 0; i < content.length; i++) {
            content[i][columnNumber] = 0;
        }
    }

    public int[][] getContent() {
        return content;
    }

    public int[][] getGrid(int gridNumber) {
        int xDeviation = (gridNumber % base) * base;
        int yDeviation = (int) Math.floor(gridNumber / base) * base;
        return calculateGrid(xDeviation, yDeviation);
    }

    private int[][] calculateGrid(int xDeviation, int yDeviation) {
        int[][] grid = new int[base][base];
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < base; j++) {
                grid[i][j] = content[i + yDeviation][j + xDeviation];
            }
        }
        return grid;
    }

    public int getGridNumber(int rowNumber, int columnNumber) {
        int xPosition = columnNumber / base;
        int yPosition = rowNumber / base;
        int gridNumber = 0;
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < base; j++) {
                if (i == yPosition && j == xPosition) {
                    break;
                }
                gridNumber++;
            }
            if (i == yPosition) {
                break;
            }
        }
        return gridNumber;
    }

    private void setGrid(int grid[][], int gridNumber) {
        int xDeviation = (gridNumber % base) * base;
        int yDeviation = (int) Math.floor(gridNumber / base) * base;
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < base; j++) {
                content[i + yDeviation][j + xDeviation] = grid[i][j];
            }
        }
    }

    public void resetGrid(int gridNumber){
        int xDeviation = (gridNumber % base) * base;
        int yDeviation = (int) Math.floor(gridNumber / base) * base;
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < base; j++) {
                content[i + yDeviation][j + xDeviation] = 0;
            }
        }
    }


    public boolean rowContains(int number, int rowNumber) {
        int[] row = getRow(rowNumber);
        for (int rowEntry : row) {
            if (rowEntry == number) {
                return true;
            }
        }
        return false;
    }

    public boolean columnContains(int number, int columnNumber) {
        int[] column = getColumn(columnNumber);
        for (int columnEntry : column) {
            if (columnEntry == number) {
                return true;
            }
        }
        return false;
    }

    public boolean gridContains(int number, int rowNumber, int columnNumber) {
        int[][] grid = getGrid(getGridNumber(rowNumber, columnNumber));
        for (int[] gridRow : grid) {
            for (int rowEntry : gridRow) {
                if (rowEntry == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSafe(int number, int rowNumber, int columnNumber) {
        System.out.println("checking number: " + number + " at " + rowNumber + " " + columnNumber);
        if (rowContains(number, rowNumber)) {
            return false;
        }
        if (columnContains(number, columnNumber)) {
            return false;
        }
        if (gridContains(number, rowNumber, columnNumber)) {
            return false;
        }
        return true;
    }

    public int[] getOccurrences() {
        int[] occurrences = new int[rowLength];
        for (int[] row : content) {
            for (int cell : row) {
                for (int i = 1; i < rowLength + 1; i++) {
                    if (cell == i) {
                        occurrences[i - 1] += 1;
                        break;
                    }
                }
            }
        }
        return occurrences;
    }

    public int getBase() {
        return base;
    }

    public void set(int number, int rowNumber, int columnNumber) {
        content[rowNumber][columnNumber] = number;
    }

    public int get(int rowNumber, int columnNumber) {
        return content[rowNumber][columnNumber];
    }

    public int getLength() {
        return base * base;
    }

}


