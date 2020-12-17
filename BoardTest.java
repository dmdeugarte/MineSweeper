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
    
    private static int boardHeight;
    private static int boardLength;
    private int boardMines;
    
    private static JButton buttons[]; // IF BUTTONS HAS ISSUE, Check the static thing.
    private static JButton markOrReveal;
    private static Tile tileArray[][];
    private static JTextField heightTF = new JTextField(20);
    private static JTextField lengthTF = new JTextField(20);
    private static JTextField numMinesTF = new JTextField(20);
    
    private JPanel buttonPanel;
    private JPanel northPanel;

    Container frame;
    
    /**
     *  Makes a default Board / Test. 10*10 with 15 mines
     */
    public BoardTest()
    {
        //Default Assignments for the board height, length, and number of mines
        boardHeight = 10;
        boardLength = 10;
        boardMines = 15;
        
        /*tileArray = new Tile[boardHeight][boardLength];
        for(int j = 0; j < boardHeight; j++)
        {
            for(int k = 0; k < boardLength; k++)
            {
                tileArray[j][k] = new Tile(0, false, false);
            }
        }*/
        
        //Stuff not done above the compiler. Built from the Default values.
        buttons = new JButton[boardHeight * boardLength];
        buttonPanel = new JPanel(new GridLayout(boardHeight, boardLength));
        
        frame = getContentPane();
        for (int index = 0; index < boardHeight * boardLength; index++)
        {
            buttons[index] = new JButton("" + HIDDEN);
            buttonPanel.add(buttons[index]);
            
            buttons[index].addActionListener(tileClickedListener(index));
        }
        
        markOrReveal = new JButton("REVEALING");
        markOrReveal.addActionListener(new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        if (markOrReveal.getText().equals("REVEALING"))
                            markOrReveal.setText("MARKING");
                        else
                            markOrReveal.setText("REVEALING");
                    }
                });
        
        northPanel = new JPanel(new FlowLayout());
        northPanel.add(heightTF);
        northPanel.add(lengthTF);
        northPanel.add(numMinesTF);
        northPanel.add(markOrReveal);
        
        // LAYOUT INFORMATION. Reference:
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        frame.setLayout(new BorderLayout());
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public static ActionListener tileClickedListener(final int tileNum)
    {
        return new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        boolean markMode = true;
                        if (markOrReveal.getText().equals("REVEALING"))
                            markMode = false;
                        
                        if(markMode && buttons[tileNum].getText().equals("" + HIDDEN))
                        {
                            if (buttons[tileNum].getText().equals("" + HIDDEN))
                                buttons[tileNum].setText("" + MARKED);
                            else if (buttons[tileNum].getText().equals("" + MARKED))
                                buttons[tileNum].setText("" + HIDDEN);
                        }
                        else if (!markMode && buttons[tileNum].getText().equals("" + HIDDEN))
                        {
                            buttons[tileNum].setText("Num");
                        }
                    }
                };
    }
    
    /*private int posFrom2D(int row, int col)
    {
        return row*boardLength + col;
    }*/
    
    private static int rowFrom1D(int pos)
    {
        return (pos - colFrom1D(pos)) / boardLength;
    }
    
    private static int colFrom1D(int pos)
    {
        return pos % boardLength;
    }
    
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        BoardTest b = new BoardTest();
    }

}
