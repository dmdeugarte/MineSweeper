// A Testing File for Board.java. Once this is fully created, it should be merged with Board.java
// Last Updated 2020.12.17
// Damian de Ugarte

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class BoardTest extends JFrame //implements ActionListener
{
    public static final char HIDDEN = ' ';
    public static final char MARKED = 'X';
    
    private static int boardHeight;
    private static int boardLength;
    private int boardMines;
    
    private static JButton    buttons[]; // IF BUTTONS HAS ISSUE, Check the static thing.
    private static JButton    markOrReveal;
    private static Tile       tileArray[][];
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
        
        tileArray = new Tile[boardHeight][boardLength];
        for(int j = 0; j < boardHeight; j++)
        {
            for(int k = 0; k < boardLength; k++)
            {
                tileArray[j][k] = new Tile(0, false, false);
            }
        }
        
        this.populateField();
        this.countMines();
        
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
    
    /**
     * Creates an ActionListener for a button in the Board's grid
     * @param tileNum The button's bucket number
     * @return the ActionListener in question.
     */
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
                        
                        int x = rowFrom1D(tileNum);
                        int y = colFrom1D(tileNum);
                        
                        if(markMode)
                        {
                            if (buttons[tileNum].getText().equals("" + HIDDEN))
                            {
                                buttons[tileNum].setText("" + MARKED);
                                tileArray[x][y].changeStatus(true);
                            }
                            else if (buttons[tileNum].getText().equals("" + MARKED))
                            {
                                buttons[tileNum].setText("" + HIDDEN);
                                tileArray[x][y].changeStatus(false);
                            }
                        }
                        else if (!markMode && buttons[tileNum].getText().equals("" + HIDDEN))
                        {
                            if (revealTile(x, y))
                                quitReveal();
                        }
                    }
                };
    }
    
    /**
     * Randomly populates a game field with mines.
     * @param n the number of mines to be populated
     */
    public void populateField()    //randomly assigns 'n' mines to the array. Designated as 9.
    {
        int i = 0;
        while(i < boardMines)
        {
            int a = (int)(Math.random()*boardHeight);
            int b = (int)(Math.random()*boardLength);
            
            if(tileArray[a][b].getNumMines() != 9)
            {
                tileArray[a][b].setNum(9);
                i++;
            }
        }
    }
    
    /**
     * Assigns values to tiles that are not mines based on the number of mines in their peripherals. 
     */
    public void countMines()
    {
        for(int j = 0; j < boardHeight; j++)
        {
            for(int k = 0; k < boardLength; k++)
            {
                if (tileArray[j][k].getNumMines() != 9)
                {
                    int count = countTilePeripherals(j, k, true);
                    
                    tileArray[j][k].setNum(count);
                }
            }
        }
    }
    
    /**
     * Checks a valid Tile from the tileArray and updates the Tile's numFlags and numMines
     * @param x The x coord of the Tile from the tileArray
     * @param y The x coord of the Tile from the tileArray
     * @param forMine Whether the count is for the number of mines or flags in peripheral.
     * @return 
     */
    public static int countTilePeripherals(int x, int y, boolean forMine)
    {
        if(x >= 0 && x < boardLength && y >= 0 && y < boardHeight)
        {
            int count = 0;
            
            if(x-1 >= 0 && y-1 >= 0)
                count += quickCount(x-1, y-1, forMine);
            
            if(y-1 >= 0)
                count += quickCount(x, y-1, forMine);
            
            if(x+1 < boardHeight && y-1 >= 0)
                count += quickCount(x+1, y-1, forMine);
        
            if(x-1 >= 0)
                count += quickCount(x-1, y, forMine);
        
            if(x+1 < boardHeight)
                count += quickCount(x+1, y, forMine);
        
            if(x-1 >= 0 && y+1 < boardLength)
                count += quickCount(x-1, y+1, forMine);
        
            if(y+1 < boardLength)
                count += quickCount(x, y+1, forMine);
        
            if(x+1 < boardHeight && y+1 < boardLength)
                count += quickCount(x+1, y+1, forMine);
            
            return count;
        }
        
        return -1;
    }
    
    /**
     * Assists countMinePeripherals with the forMine boolean. Shortens code.
     * @param x The x coord of the Tile from the tileArray
     * @param y The x coord of the Tile from the tileArray
     * @param forMine Whether the count is for the number of mines or flags in peripheral.
     * @return 1 for the tile is a mine or the tile is flagged(depends on tag), 0 otherwise.
     */
    public static int quickCount(int x, int y, boolean forMine)
    {
        if(forMine && tileArray[x][y].getNumMines() == 9)
            return 1;
        
        if (!forMine && tileArray[x][y].isMarked())
            return 1;
        
        return 0;
    }
    
    /**
     * Changes the isSeen value of a tile. Determines if a game is lost. 
     * @param x The row value of the coordinate
     * @param y The column value of the coordinate
     * @param cont a boolean used to tell revealTile to recursively check for more values
     * @return true if a mine was revealed
     *         false is a regular tile was revealed
     */
    public static boolean revealTile(int x, int y) //will return a boolean value of if a mine was revealed and one should quit the game. 
    {
        if(x >= 0 && x < boardLength && y >= 0 && y < boardHeight)
        {
            if (tileArray[x][y].getNumMines() == 9)
                return true;
            else if (!tileArray[x][y].isSeen())
            {
                tileArray[x][y].setSeen();
                buttons[posFrom2D(x, y)].setText("" + tileArray[x][y].getNumMines());
                    
                if (tileArray[x][y].getNumMines() == 0 || countTilePeripherals(x,y,false) == tileArray[x][y].getNumMines())
                {
                    revealTile(x-1, y-1);
                    revealTile(x, y-1);
                    revealTile(x+1, y-1);
                    revealTile(x-1, y);
                    revealTile(x+1, y);
                    revealTile(x-1, y+1);
                    revealTile(x, y+1);
                    revealTile(x+1, y+1);
                }
                
                return false;
            }
        }
        return false;
    }
    
    /**
     * Ends a game by revealing every tile.
     */
    public static void quitReveal()
    {
        for(int j = 0; j < boardHeight; j++)
        {
            for(int k = 0; k < boardLength; k++)
            {
                tileArray[j][k].setSeen();
                buttons[posFrom2D(j,k)].setText("" + tileArray[j][k].getNumMines());
            }
        }
    }
    
    /**
     * Starts a game by revealing a random tile with no mines near it.
     */
    public void startGame()
    {
        boolean cont = true;
        
        while(cont)
        {
            int a = (int)(Math.random()*boardHeight);
            int b = (int)(Math.random()*boardLength);
            
            if(tileArray[a][b].getNumMines() == 0)
            {
                revealTile(a, b);
                cont = false;
            }
        }
    }
    
    private static int posFrom2D(int row, int col)
    {
        return row*boardLength + col;
    }
    
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
        
        b.startGame();
    }

}
