
public class Game 
{
	public static void main(String[] args)
	{
		Board game = new Board(9, 9);
		
		game.populateField(15);
		game.countMines();
		
		game.printField();
		
		game.setMineStatus(1, 1, true);
		
		game.printField();
	}

}
