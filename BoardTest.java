// A Testing File for Board.java. Once this is fully created, it should be merged with Board.java
// Last Updated 2020.12.17
// Damian de Ugarte

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardTest extends JFrame //implements ActionListener
{
    public static final char HIDDEN = '=';
    public static final char MARKED = 'X';
    
    private int boardHeight;
    private int boardLength;
    private int boardMines;
    
    private JButton buttons[];
    private JButton markOrReveal;
    private Tile tileArray[][];
    private JPanel buttonPanel;
    private static JTextField heightTF = new JTextField(20);
    private static JTextField lengthTF = new JTextField(20);
    private static JTextField numMinesTF = new JTextField(20);
    
    private JPanel testText;
    Container frame;
    
    /*private int posFrom2D(int row, int col)
    {
        return row*boardLength + col;
    }
    
    private int rowFrom1D(int pos)
    {
        return (pos - colFrom1D(pos)) / boardLength;
    }
    
    private int colFrom1D(int pos)
    {
        return pos % boardLength;
    }*/
    
    /**
     *  Makes a default Board / Test. 10*10 with 15 mines
     */
    public BoardTest()
    {
        //Default Assignments for the board height, length, and number of mines
        boardHeight = 10;
        boardLength = 10;
        boardMines = 15;
        
        //Stuff not done above the compiler. Built from the Default values.
        buttons = new JButton[boardHeight * boardLength];
        tileArray = new Tile[boardHeight][boardLength];
        buttonPanel = new JPanel(new GridLayout(boardHeight, boardLength));
        
        frame = getContentPane();
        for (int index = 0; index < boardHeight * boardLength; index++)
        {
            buttons[index] = new JButton("" + HIDDEN);
            buttonPanel.add(buttons[index]);
        }
        
        markOrReveal = new JButton("REVEALING");
        
        testText = new JPanel(new FlowLayout());
        testText.add(heightTF);
        testText.add(lengthTF);
        testText.add(numMinesTF);
        testText.add(markOrReveal);
        
        //ACTION LISTENERS WILL GO HERE. Use a for loop for the array (with 1D to 2D stuff) and the toggle button. 
        
        // LAYOUT INFORMATION. Reference:
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        frame.setLayout(new BorderLayout());
        frame.add(testText, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        BoardTest b = new BoardTest();
    }

}
