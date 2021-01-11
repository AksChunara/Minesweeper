/**
 @author: Aks Chunara
 @class: Main
 @description: Main class where the game is started
 */

//Imports
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Displays and starts the application
        JFrame frame = new Minesweeper("Minesweeper");
        frame.pack();
        frame.setVisible(true);
    }
}
