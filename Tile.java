
public class Tile 
{
	private int numMines;
	private boolean isMarked;
	private boolean isSeen;
	
	/**
	 * Tile Constructor. Requires number of mines, if marked, if seen.
	 * @param inNum The number of mines the tile has.
	 * @param inMark The boolean value of if the tile is marked.
	 * @param inSeen The boolean value of if the tile is seen.
	 */
	public Tile(int inNum, boolean inMark, boolean inSeen)
	{
	    numMines = inNum;
		isMarked = inMark;
		isSeen   = inSeen;
	}
	
	/**
	 * Returns the number value of the tile 0-9
	 * @return the number value of the tile
	 */
	public int getNumMines()
	{
		return numMines;
	}
	
	/**
	 * Returns the isMarked value of the tile
	 * @return the isMarked value of the tile
	 */
	public boolean isMarked()
	{
		return isMarked;
	}
	
	/**
     * Returns the isSeen value of the tile
     * @return returns the isSeen value of the tile. True for is visible and false for not visible
     */
    public boolean isSeen()
    {
        return isSeen;
    }
    
    /**
     * Changes the isMarked value to the parameter provided
     * @param k a boolean to change the isMarked value to
     */
    public void changeStatus(boolean k)
    {
        isMarked = k;
    }
	
	/**
	 * Changes the number value of the tile.
	 * @param n the number value to change the tile to
	 */
	public void setNum(int n)
	{
	    numMines = n;
	}
	
	/**
	 * Sets the isSeen value to seen. There is no reversing this.
	 */
	public void setSeen()
	{
		isSeen = true;
	}
}
