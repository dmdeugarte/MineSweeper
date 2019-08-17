import java.util.Scanner;
import java.lang.String;
public class Game 
{
	public static void main(String[] args)
	{
		Scanner scanNum = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		
		printInstructions();
		
		System.out.println("You can set the game to it's own size!\nFirst dimension?");
		int size1 = scanNum.nextInt();
		
		System.out.println("Second dimension?");
		int size2 = scanNum.nextInt();
		
		Board game = new Board(size1, size2);
		
		System.out.println("Number of mines?");
		int numMines = scanNum.nextInt();
		
		game.populateField(numMines);
		game.countMines();
		
		game.printField();
		System.out.println("-----------------");
		
		game.printField();
		
		boolean cont = true;
		while(cont)
		{
			game.printField();
			printMenu();
			int choice = scanNum.nextInt();
			
			System.out.println("Coordinates?");		//PUT INSIDE SWITCH!
			String coords = scan.nextLine();
			
			int a = Integer.parseInt(coords.substring(0, coords.indexOf("_")));
			int b = Integer.parseInt(coords.substring(coords.indexOf("_")+1));
			
			switch(choice)
			{
			case 1: //game.revealTile(a, b);
				break;
			case 2: game.flipMineStatus(a, b);
				break;
			case 9: //game.quitReveal();
				break;
			}
				
		}
	}
	
	public static void printMenu()
	{
		System.out.println("Please choose a move.");
		System.out.println("1) Reveal a Tile");
		System.out.println("2) Mark a Tile");
		System.out.println("9) Quit and Reveal");
	}
	
	public static void printInstructions()	//separating so, if necessary, I can easily add, change, or remove instructions
	{
		System.out.println("Please note:\nWhen inputting coordinates, please input one coord, a space and a second coord.\n ie. 12_6");
	}
}
