import java.lang.Math;
public class Board 
{
	private Tile[][] arr;
	private int r;
	private int c;
	
	public Board(int a, int b)
	{
		arr = new Tile[a][b];
		r = a;
		c = b;
		
		for(int j = 0; j < r; j++)
		{
			for(int k = 0; k < c; k++)
			{
				arr[j][k] = new Tile(0, false, false);
			}
		}
	}
	
	/**
	 * Randomly populates a game field with mines.
	 * @param n the number of mines to be populated
	 */
	public void populateField(int n)	//randomly assigns 'n' mines to the array. Designated as 9.
	{
		int i = 0;
		while(i < n)
		{
			int a = (int)(Math.random()*r);
			int b = (int)(Math.random()*c);
			
			if(arr[a][b].getNum() != 9)
			{
				arr[a][b].setNum(9);
				i++;
			}
				
		}
	}
	
	/**
	 * Assigns values to tiles that are not mines based on the number of mines in their peripherals. 
	 */
	public void countMines()
	{
		for(int j = 0; j < r; j++)
		{
			for(int k = 0; k < c; k++)
			{
				if (arr[j][k].getNum() != 9)
				{
					int count = 0;
					
					if((j-1 >= 0 && k-1 >= 0) && arr[j-1][k-1].getNum() == 9)
						count++;
					
					if((k-1 >= 0) && arr[j][k-1].getNum() == 9)
						count++;
				
					if((j+1 < r && k-1 >= 0) && arr[j+1][k-1].getNum() == 9)
						count++;
				
					if((j-1 >= 0) && arr[j-1][k].getNum() == 9)
						count++;
				
					if((j+1 < r) && arr[j+1][k].getNum() == 9)
						count++;
				
					if((j-1 >= 0 && k+1 < c) && arr[j-1][k+1].getNum() == 9)
						count++;
				
					if((k+1 < c) && arr[j][k+1].getNum() == 9)
						count++;
				
					if((j+1 < r && k+1 < c) && arr[j+1][k+1].getNum() == 9)
						count++;
					
					arr[j][k].setNum(count);
				}
			}
		}
	}
	
	/**
	 * Flips the mine status at a coordinate from marked to not marked.
	 * @param a the row value
	 * @param b the column value
	 */
	public void flipMineStatus(int a, int b)
	{
		if ((a>=0 && a<r) && (b>=0 && b<c))
		{
			if(arr[a][b].isMarked())
				arr[a][b].changeStatus(false);
			else
				arr[a][b].changeStatus(true);
		}
	}
	
	/**
	 * Prints the game field to the console with proper markings. 
	 */
	public void printField()
	{
		for(Tile[] r: arr)
		{
			for(Tile c: r)
			{
				if(c.isMarked())
					System.out.print("x ");
				else
				{
					if(c.isSeen())
						System.out.print(c.getNum() + " ");
					else
						System.out.print("= ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Changes the isSeen value of a tile. Determines if a game is lost. 
	 * @param a The row value of the coordinate
	 * @param b The column value of the coordinate
	 * @return true if a mine was revealed
	 * 		   false is a regular tile was revealed
	 */
	public boolean revealTile(int a, int b) //will return a boolean value of if a mine was revealed and one should quit the game. 
	{
		if (arr[a][b].getNum() == 9)
			return true;
		else 
		{
			arr[a][b].setSeen();
			return false;
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
			int a = (int)(Math.random()*r);
			int b = (int)(Math.random()*c);
			
			if(arr[a][b].getNum() == 0)
			{
				arr[a][b].setSeen();
				cont = false;
			}
		}
	}
	
	/**
	 * Ends a game by revealing every tile.
	 */
	public void quitReveal()
	{
		for(int j = 0; j < r; j++)
		{
			for(int k = 0; k < c; k++)
			{
				arr[j][k].setSeen();
			}
		}
	}
}
