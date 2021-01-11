/**
 @author: Aks Chunara
 @class: Level
 @description: Contains method to set difficulty
 */

public class Level {
    //Instance Initialization
    private int numOfMines;
    protected int gridSize;
    protected MineField board;

    //Constructor
    public Level(int numOfMines, int gridSize) {
        this.numOfMines = numOfMines;
        this.gridSize = gridSize;
        this.board = new MineField(numOfMines,gridSize);
    }

    //Getters
    public MineField getBoard() {
        return board;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public int getGridSize(){
        return gridSize;
    }
}
