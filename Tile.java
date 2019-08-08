
public class Tile 
{
	private int num;
	private boolean isMarked;
	
	public Tile(int inNum, boolean inBool)
	{
		num = inNum;
		isMarked = inBool;
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
}
