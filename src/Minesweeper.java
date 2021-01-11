/**
 @author: Aks Chunara
 @class: Minesweeper
 @description: Connects the GUI with rest of the code
 */

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame {
    //Instance Initialization
    private JPanel board;
    public JPanel main;
    private JComboBox difficulty;
    private JLabel numOfMines;
    private JLabel numOfFlags;
    private JButton startButton;
    private Game game;
    private JPanel[][] gameBoard;

    //Constructor
    public Minesweeper(String title) {
        super(title);
        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Adds difficulties
        difficulty.addItem("Easy");
        difficulty.addItem("Medium");
        difficulty.addItem("Hard");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Checks if start button is triggered
                //Initializes game and the components inside JFrame
                String chosenDifficulty = (String) difficulty.getSelectedItem();
                game = new Game(chosenDifficulty);
                game.start();

                gameBoard = new JPanel[game.getGridSize()][game.getGridSize()];

                numOfMines.setText(String.valueOf(game.getNumOfMines()));
                numOfFlags.setText(String.valueOf(game.getNumOfMines()));

                resetBlocks();
                repaint();
                addBlocks(game.getGridSize());

                setVisible(true);
            }
        });
    }

    //Adds buttons to the JPanel board
    public void addBlocks(int gridSize) {
        board.setLayout(new GridLayout(gridSize, gridSize));

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                JPanel panel = new JPanel();
                JButton button = new JButton();
                button.setActionCommand(i + "," + j);
                panel.setLayout(new BorderLayout(0, 0));
                panel.add(button);
                gameBoard[i][j] = panel;
                board.add(gameBoard[i][j]);
                button.addMouseListener(new MouseAdapter() {//Checks if button is triggered by a mouse
                    public void mousePressed(MouseEvent mouseEvent) {
                        int modifiers = mouseEvent.getModifiers();
                        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {//Left Click
                            //Plays a round
                            game.buttonClicked(gameBoard, button);
                        }
                        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {//Right Click
                            //Flags or unflags a block
                            game.flag(button, numOfFlags);
                        }
                        setVisible(true);
                    }
                });
                }
            }
        }

    //Clears all buttons
    public void resetBlocks(){
        board.removeAll();
    }
}
