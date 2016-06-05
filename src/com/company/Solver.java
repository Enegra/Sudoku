package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 4/10/2016.
 */
public class Solver {

    private Sample sample;
    private int calls;

    public Solver(Sample sample) {
        this.sample = sample;
    }

    private boolean isSolved() {
        int[][] solution = sample.getContent();
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                if (solution[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isUnsolvedCell(int rowNumber, int columnNumber) {
        return (sample.get(rowNumber, columnNumber) == 0);
    }

    private Cell getNext(int rowNumber, int columnNumber) {
        if (rowNumber == sample.getLength() - 1 && columnNumber == sample.getLength() - 1) {
            return null;
        }
        if (columnNumber == sample.getLength() - 1) {
            return new Cell(rowNumber + 1, 0);
        } else {
            return new Cell(rowNumber, columnNumber + 1);
        }
    }

    public boolean solve(int rowNumber, int columnNumber) {
        if (isSolved()) {
            return true;
        }
        if (!isUnsolvedCell(rowNumber, columnNumber)) {
            Cell nextCell = getNext(rowNumber, columnNumber);
                return solve(nextCell.row, nextCell.column);
        }
        for (int number = 1; number < sample.getLength() + 1; number++) {
            if (!sample.isSafe(number, rowNumber, columnNumber)) {
                continue;
            }
            sample.set(number, rowNumber, columnNumber);
            Cell nextCell = getNext(rowNumber, columnNumber);
            if (nextCell != null) {
                if (solve(nextCell.row, nextCell.column)) {
                    return true;
                } else {
                    sample.set(0, rowNumber, columnNumber);
                }
            }
            else {
                if (isSolved()){
                    return true;
                }
            }
        }
        return false;
    }

    public void solve() {
        if (solve(0, 0)) {
            System.out.println("Sudoku solved");
        } else {
            System.out.println("No solution available");
        }
    }

    public void solveFw() {
        if (solveFw(0, 0)) {
            System.out.println("Sudoku solved");
        } else {
            System.out.println("No solution available");
        }
    }


    public boolean solveFw(int rowNumber, int columnNumber) {
        if (isSolved()) {
            return true;
        }
        if (!isUnsolvedCell(rowNumber, columnNumber)) {
            Cell nextCell = getNext(rowNumber, columnNumber);
            return solveFw(nextCell.row, nextCell.column);
        }
        ArrayList<Integer> domain = getDomain();
        for (int number : domain) {
            if (!sample.isSafe(number, rowNumber, columnNumber)) {
                continue;
            }
            sample.set(number, rowNumber, columnNumber);
            Cell nextCell = getNext(rowNumber, columnNumber);
            if (nextCell != null) {
                if (solveFw(nextCell.row, nextCell.column)) {
                    return true;
                } else {
                    sample.set(0, rowNumber, columnNumber);
                }
            }
            else {
                if (isSolved()){
                    return true;
                }
            }
        }
        return false;
    }

    private int getMostCommonNumber(int[] occurrences){
        int mostCommon = occurrences[0];
        int number = 0;
        for (int i=0; i<occurrences.length; i++){
            if (occurrences[i] > mostCommon){
                mostCommon = occurrences[i];
                number = i+1;
            }
        }
        return number;
    }

    private int[] sortByOccurrence(int[] occurrences){
        int[] numbers = new int[sample.getLength()];
        for (int i=0; i<numbers.length; i++){
            numbers[i]=i+1;
        }
        boolean swapped = true;
        while (swapped) {
            swapped=false;
            for (int i = 0; i < occurrences.length - 1; i++) {
                if (occurrences[i] < occurrences[i + 1]) {
                    int temp = occurrences[i];
                    occurrences[i] = occurrences[i + 1];
                    occurrences[i + 1] = temp;
                    temp = numbers[i];
                    numbers[i] = numbers[i+1];
                    numbers[i+1] = temp;
                    swapped=true;
                }
            }
        }
        return numbers;
    }

    private ArrayList<Integer> getDomain(){
        int numberBase = sample.getLength();
        ArrayList<Integer> domain = new ArrayList<Integer>();
        int[] occurrences = sample.getOccurrences();
        int[] sortedNumbers = sortByOccurrence(occurrences);
        for (int i=0; i<numberBase; i++){
            System.out.println(occurrences[sortedNumbers[i]-1]);
            if (occurrences[sortedNumbers[i]-1]!=numberBase){
                domain.add(sortedNumbers[i]);
            }
        }
        return domain;
    }

    public int getCallsNumber(){
        return calls;
    }
}

