
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
	
	public void changeStatus(boolean k)
	{
		isMarked = k;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public boolean isMarked()
	{
		return isMarked;
	}
	
	public void setNum(int n)
	{
		num = n;
	}
	
	public boolean isSeen()
	{
		return isSeen;
	}
	
	public void setSeen()
	{
		isSeen = true;
	}
}
