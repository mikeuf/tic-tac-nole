/*
TTTCell.java

The buttons for the tic tac toe game
*/

import javax.swing.*;
import java.awt.event.*;

public class TTTCell extends JButton implements ActionListener
{
    // is the cell in use?
    boolean cellUsed = false;

    // is the cell a nole or a gator?
    int cellStatus = -1;

    // each cell has its own number 1-9
    public int cellNum = 0;

    // images for the buttons
    ImageIcon nole;
    ImageIcon gator;


    public TTTCell()
    {
        // load the images into buttons
        nole = new ImageIcon(this.getClass().getResource("/images/nole_100.png"));
        gator = new ImageIcon(this.getClass().getResource("/images/gator_100.png"));

        // when button is pushed, do the actionPerformed
        this.addActionListener(this);

    }

    public void actionPerformed(ActionEvent aEvent)
    {
        // only do this if the cell is not already used
        if (!cellUsed)
        {
            // get access to the parent so we can update it
            /*
            Note: The idea of using getWindowsAncestor with casting is from StackOverflow:
            http://stackoverflow.com/questions/9650874/java-swing-obtain-window-jframe-from-inside-a-jpanel
             */
            TicTacToe parent = (TicTacToe) SwingUtilities.getWindowAncestor(this);

            // basically alternates cell status between 0 and 1 (nole and gator)
            switch (parent.gameTurn % 2)
            {
                case 0:
                    setIcon(nole);
                    this.cellStatus = 0;
                    this.cellUsed = true;
                    break;
                case 1:
                    setIcon(gator);
                    this.cellStatus = 1;
                    this.cellUsed = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error while setting cell icon.");
                    break;
            }

            // next turn
            parent.switchTurn();

            // update text fields
            parent.getEast();
            parent.getWest();

            // check to see if anyone won
            parent.evalutateBoard();
        }

    }

} // end
