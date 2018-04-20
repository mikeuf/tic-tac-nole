/*
Black, Mike
COP-3252
Project 1
3/1/2015 (overnight)

TicTacToe.java

The main GUI for the tic tac toe game

*/


import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class TicTacToe extends JFrame
{
    // main game counter
    int gameTurn = 0;

    // various GUI components
    JPanel gameBoard = new JPanel();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();
    JLabel westText = new JLabel();
    JLabel eastText = new JLabel();

    // set up 9 cells for tic tac toe
    TTTCell tttcell[] = new TTTCell[9];


    public static void main(String args[])
    {
        TicTacToe tictactoe = new TicTacToe();
    }

    // set up main GUI
    public TicTacToe()
    {
        super("Tic-Tac-Toe");
        setLocationRelativeTo(null);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane();

        pane.add(getHeader(), BorderLayout.NORTH);

        // set up grid of cells for tic tac toe
        gameBoard.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; ++i)
        {
            tttcell[i] = new TTTCell();
            gameBoard.add(tttcell[i]);
            tttcell[i].cellNum = i;
        }
        add(gameBoard);


        pane.add(getFooter(), BorderLayout.SOUTH);
        pane.add(getWest(), BorderLayout.WEST);
        pane.add(getEast(), BorderLayout.EAST);
        setVisible(true);
    }

    // the header, with text
    public JComponent getHeader()
    {
        JLabel headerText = new JLabel("FSU", JLabel.CENTER);
        return headerText;
    }

    // the main game board, with the cells
    public JComponent getGameBoard(int cell)
    {
        tttcell[cell] = new TTTCell();
        return tttcell[cell];
    }

    // add a footer text
    public JComponent getFooter()
    {
        JLabel footerText = new JLabel("By Mike Black", JLabel.CENTER);
        return footerText;
    }

    // update the text of the left field
    public JComponent getWest()
    {
        if ((gameTurn % 2) == 0)
        {
            westText.setText("<html>" +
                    "<p>Player 1 (Noles)" +
                    "<br /><strong>Your turn!</strong></p>" +
                    "</html>");
        }
        else
        {
            westText.setText("<html>" +
                    "<p>Player 1 (Noles)</p>" +
                    "</html>");
        }
        return westText;
    }

    // update the text of the right field
    public JComponent getEast()
    {
        if ((gameTurn % 2) == 1)
        {
            eastText.setText("<html>" +
                    "<p>Player 2 (Gators)" +
                    "<br /><strong>Your turn!</strong></p>" +
                    "</html>");
        }
        else
        {
            eastText.setText("<html>" +
                    "<p>Player 2 (Gators)</p>" +
                    "</html>");
        }
        return eastText;
    }

    // updates to the next turn and changes players
    public int switchTurn()
    {
        ++gameTurn;

        return (gameTurn % 2);
    }

    public int reinitializeBoard()
    {
        // reset all cells and set the game turn back to 0
        for (int i = 0; i < 9; ++i)
        {
            gameTurn = 0;
            tttcell[i].cellStatus = -1;
            tttcell[i].cellUsed = false;
            tttcell[i].setIcon(null);
            getEast();
            getWest();

        }
        return 0;
    }

    // display victory message and ask if the player wants to restart
    public int victory(int whichPlayer)
    {
        if (whichPlayer == 1)
        {
            JOptionPane.showMessageDialog(null, "Player 1 (Noles) wins!");
        }

        else if (whichPlayer == 0)
        {

            JOptionPane.showMessageDialog(null, "Player 2 (Gators) wins!");
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Tie Game!");
        }

        // ask if you want to replay?
        int restart = JOptionPane.showConfirmDialog(null, "Play again?", "Question", JOptionPane.YES_NO_OPTION);

        if (restart == 1)
        {
            System.exit(0);
        }
        else
        {
            reinitializeBoard();
        }

        return 0;
    }

    // looks for "3 in a row" formations
    public int evalutateBoard()
    {

        // Check to see if Noles won
        if ((tttcell[0].cellStatus == 0 &&
                tttcell[1].cellStatus == 0 &&
                tttcell[2].cellStatus == 0) ||

                (tttcell[3].cellStatus == 0 &&
                        tttcell[4].cellStatus == 0 &&
                        tttcell[5].cellStatus == 0) ||

                (tttcell[6].cellStatus == 0 &&
                        tttcell[7].cellStatus == 0 &&
                        tttcell[8].cellStatus == 0) ||

                (tttcell[0].cellStatus == 0 &&
                        tttcell[3].cellStatus == 0 &&
                        tttcell[6].cellStatus == 0) ||

                (tttcell[1].cellStatus == 0 &&
                        tttcell[4].cellStatus == 0 &&
                        tttcell[7].cellStatus == 0) ||

                (tttcell[2].cellStatus == 0 &&
                        tttcell[5].cellStatus == 0 &&
                        tttcell[8].cellStatus == 0) ||

                (tttcell[0].cellStatus == 0 &&
                        tttcell[4].cellStatus == 0 &&
                        tttcell[8].cellStatus == 0) ||

                (tttcell[2].cellStatus == 0 &&
                        tttcell[4].cellStatus == 0 &&
                        tttcell[6].cellStatus == 0) ||

                (tttcell[0].cellStatus == 0 &&
                        tttcell[1].cellStatus == 0 &&
                        tttcell[2].cellStatus == 0))
        {
            // nole victory
            victory(1);
        }

        // Check to see if Gators won
        else if ((tttcell[0].cellStatus == 1 &&
                tttcell[1].cellStatus == 1 &&
                tttcell[2].cellStatus == 1) ||

                (tttcell[3].cellStatus == 1 &&
                        tttcell[4].cellStatus == 1 &&
                        tttcell[5].cellStatus == 1) ||

                (tttcell[6].cellStatus == 1 &&
                        tttcell[7].cellStatus == 1 &&
                        tttcell[8].cellStatus == 1) ||

                (tttcell[0].cellStatus == 1 &&
                        tttcell[3].cellStatus == 1 &&
                        tttcell[6].cellStatus == 1) ||

                (tttcell[1].cellStatus == 1 &&
                        tttcell[4].cellStatus == 1 &&
                        tttcell[7].cellStatus == 1) ||

                (tttcell[2].cellStatus == 1 &&
                        tttcell[5].cellStatus == 1 &&
                        tttcell[8].cellStatus == 1) ||

                (tttcell[0].cellStatus == 1 &&
                        tttcell[4].cellStatus == 1 &&
                        tttcell[8].cellStatus == 1) ||

                (tttcell[2].cellStatus == 1 &&
                        tttcell[4].cellStatus == 1 &&
                        tttcell[6].cellStatus == 1) ||

                (tttcell[0].cellStatus == 1 &&
                        tttcell[1].cellStatus == 1 &&
                        tttcell[2].cellStatus == 1))
        {
            // gator victory
            victory(0);
        }

        // check for tie game
        else if (gameTurn > 8)
        {
            victory(3);
        }

        return 0;
    }
}


