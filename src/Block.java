/**
 @author: Aks Chunara
 @class: Block
 @description: Structures the Block class
 */

public class Block {
    //Instance Initialization
    private boolean mine;
    private int surroundingMines;
    private boolean revealed;

    //Constructor
    public Block(boolean mine) {
        this.mine = mine;
        if(mine){
            this.surroundingMines = -1;
        }
        revealed = false;
    }

    //Getters and Setters
    public void setSurroundingMines(int surroundingMines) {
        this.surroundingMines = surroundingMines;
    }

    public int getNumOfSurroundingMines() {
        return surroundingMines;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    //Returns whether the booleans are true
    public boolean isMine() {
        return mine;
    }

    public boolean isRevealed(){
        return revealed;
    }
}
