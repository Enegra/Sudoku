import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnie on 4/5/2016.
 */
public class Sample {

    private int[][] content;
    private int base;
    private int rowLenght;

    public Sample(int[][] sampleContent, int base) throws Exception{
        this.base=base;
        rowLenght = base*base;
        if (!isValid(sampleContent)) throw new Exception("Invalid format");
        this.content=sampleContent;
    }


    public boolean isValid(int[][] sampleContent){
        if (sampleContent.length != rowLenght){
            return false;
        }
        for (int i=0; i<sampleContent.length; i++){
            if (sampleContent[i].length !=rowLenght){
                return false;
            }
        }
        return true;
    }

    public int[] getRow(int rowNumber){
        return content[rowNumber];
    }

    public int[] getColumn(int columnNumber){
        int[] column = new int[rowLenght];
        for (int i=0; i<column.length; i++){
            column[i] = content[i] [columnNumber];
        }
        return column;
    }

    public int[][] getGrid(int gridNumber){
        int xDeviation =   gridNumber%base;
        int yDeviation = gridNumber/base;
        return calculateGrid(xDeviation, yDeviation);
    }

    private int[][] calculateGrid(int xDeviation, int yDeviation){
        int[][] grid = new int[base][base];
        for (int i=0; i<base; i++){
            for (int j=0; j<base; j++){
                grid[i][j] = content[i+yDeviation][j+xDeviation];
            }
        }
        return grid;
    }

}


