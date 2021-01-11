/**
 @author: Aks Chunara
 @class: Game
 @description: Contains methods that run the game
 */

//Imports
import javax.swing.*;

public class Game {
    //Instance Initialization
    private String difficulty;
    private Level level;

    //Constructor
    public Game(String difficulty) {
        this.difficulty = difficulty.toLowerCase();
    }

    //Sets the difficulty and initializes level
    public void start() {
        if (difficulty.equals("easy")){
            level = new Easy();
        } else if (difficulty.equals("medium")){
            level = new Medium();
        } else if (difficulty.equals("hard")){
            level = new Hard();
        }

        level.getBoard().placeMines();
        level.getBoard().placeLand();
    }

    //When button is clicked this plays one round
    public void buttonClicked(JPanel[][] gameBoard, JButton button){
        String[] index = button.getActionCommand().split(",");
        int y = Integer.parseInt(index[0]);
        int x = Integer.parseInt(index[1]);

        if(level.getBoard().blockIsMine(y,x)){
            //If mine, game over
            boom(gameBoard);
        } else if(level.getBoard().getBlock(y,x).getNumOfSurroundingMines() == 0){
            //display all surrounding zeros
            System.out.println("Zero");
            blockIsZero(gameBoard, y, x);
        } else {
            //display block's number of surrounding mines
            showValue(gameBoard[y][x], String.valueOf(level.getBoard().getBlock(y,x).getNumOfSurroundingMines()));
        }
    }

    //Flags a button
    public void flag(JButton button, JLabel numOfFlags){
        int flags = Integer.parseInt(numOfFlags.getText());
        String text = "\uD83C\uDFF4";
        if(button.getText().equals("\uD83C\uDFF4")){
            text = " ";
            flags++;
        } else{
            flags--;
        }
        button.setText(text);
        numOfFlags.setText(String.valueOf(flags));
    }

    //When a mine is clicked everything is shown
    public void boom(JPanel[][] gameBoard){
        String text = "";
        for (int i = 0; i < getGridSize(); i++) {
            for (int j = 0; j < getGridSize(); j++) {
                if(level.getBoard().getBlock(i,j).isMine()){
                    text = "\uD83D\uDCA3";
                } else if (level.getBoard().getBlock(i,j).getNumOfSurroundingMines() == 0) {
                    text = " ";
                } else {
                    text = String.valueOf(level.getBoard().getBlock(i,j).getNumOfSurroundingMines());
                }
                showValue(gameBoard[i][j], text);
            }
        }
    }

    //If block is zero show all surrounding block that are zero and all numbers of bordering Blocks
    public void blockIsZero(JPanel[][] gameBoard, int y, int x){
        for (int i = -1; i <= 1; i++) {
            boolean zeroBorder = true;
            for (int j = -1; j <= 1; j++) {
                if (0 <= (i+y) && (i+y) < getGridSize() && 0 <= (j+x) && (j+x) < getGridSize()) {
                    if(level.getBoard().getBlock((i+y),(j+x)).getNumOfSurroundingMines() == 0 && !level.getBoard().getBlock((i+y),(j+x)).isRevealed()) {
                        level.getBoard().getBlock((i+y),(j+x)).setRevealed(true);
                        showValue(gameBoard[i+y][j+x], " ");
                        blockIsZero(gameBoard, (i+y), (j+x));
                    } else if(zeroBorder && !level.getBoard().getBlock((i+y),(j+x)).isRevealed()){
                        showValue(gameBoard[i+y][j+x], String.valueOf(level.getBoard().getBlock(i+y,j+x).getNumOfSurroundingMines()));
                        zeroBorder = false;
                    }
                }
            }
        }
        return;
    }

    //Removes the button in the panel and replaces it with the number of mines of the block
    public void showValue(JPanel panel, String value){
        panel.removeAll();
        JLabel l = new JLabel(value, SwingConstants.CENTER);
        panel.add(l);
    }

    //Getters
    public int getNumOfMines(){
        return level.getNumOfMines();
    }

    public int getGridSize(){
        return level.getGridSize();
    }

    //Prints the whole board without covering
//    public void cheat(){
//        for (int i = 0; i < getGridSize(); i++) {
//            for (int j = 0; j < getGridSize(); j++) {
//                int num = level.getBoard().getBlock(i,j).getNumOfSurroundingMines();
//                if(num != -1){
//                    System.out.print("| "+num+"|");
//                } else {
//                    System.out.print("|"+num+"|");
//                }
//            }
//            System.out.println();
//        }
//    }

}
