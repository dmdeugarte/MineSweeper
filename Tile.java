
public class Tile 
{
	private int num;
	private boolean isMarked;
	private boolean isSeen;
	
	public Tile(int inNum, boolean inBool, boolean inSeen)
	{
		num = inNum;
		isMarked = inBool;
		isSeen = inSeen;
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
	 * Returns the number value of the tile 0-9
	 * @return the number value of the tile
	 */
	public int getNum()
	{
		return num;
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
	 * Changes the number value of the tile.
	 * @param n the number value to change the tile to
	 */
	public void setNum(int n)
	{
		num = n;
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
	 * Sets the isSeen value to seen. There is no reversing this.
	 */
	public void setSeen()
	{
		isSeen = true;
	}
}
