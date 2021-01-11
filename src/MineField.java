/**
 @author: Aks Chunara
 @class: MineField
 @description: Structures the MineField class
 */

//Imports
import java.util.ArrayList;
import java.util.Random;

public class MineField {
    //Instance Initialization
    private int numOfMines;
    private int gridSize;
    private Block[][] mineField;
    private ArrayList<String> minePos;

    //Constructor
    public MineField(int numOfMines, int gridSize) {
        this.numOfMines = numOfMines;
        this.gridSize = gridSize;
        mineField = new Block[gridSize][gridSize];
        minePos = new ArrayList<String>();
    }

    //Randomly places mines
    public void placeMines() {
        Random rand = new Random();

        int i = 0;
        while (i < numOfMines) {
            int y = rand.nextInt(gridSize-1);
            int x = rand.nextInt(gridSize-1);

            if(!minePos.contains(y + "," + x)){
                mineField[y][x] = new Block(true);
                minePos.add(y + "," + x);
                i++;
            }
        }
    }

    //Places land and calculates the number of surroundingMines
    public void placeLand() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int surroundingMines = 0;
                if(mineField[i][j] == null){
                    for (int y = -1; y <= 1; y++) {
                        for (int x = -1; x <= 1; x++) {
                            if (0 <= (i+y) && (i+y) < gridSize && 0 <= (j+x) && (j+x) < gridSize) {
                                if(mineField[i+y][j+x] != null && mineField[i+y][j+x].isMine()) {
                                    surroundingMines += 1;
                                }
                            }
                        }
                    }
                    mineField[i][j] = new Block(false);
                    mineField[i][j].setSurroundingMines(surroundingMines);
                }
            }
        }
    }

    //Getters
    public Block[][] getMineField() {
        return mineField;
    }

    public Block getBlock(int y, int x) {
        return mineField[y][x];
    }

    //Returns whether block is a Mine or not
    public boolean blockIsMine(int y, int x){
        return getBlock(y,x).isMine();
    }
}
