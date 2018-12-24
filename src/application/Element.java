package application;

import java.util.Scanner;
import java.io.*;

public class Element
{
	int atomicNum;

	public Element(int a)
	{
		atomicNum = a;
	}

	public String getName()
	{
		String name = "";
		try
		{
			Scanner scanName = new Scanner(new File("\\src\\application\\name.txt"));
			for(int i=0; i<atomicNum; i++)
			{
				name = scanName.next();
			}
			scanName.close();
		}
		catch(Exception e)
		{

		}
		return name;
	}

	public String getSymbol()
	{
		String symbol = "";
		try
		{
			Scanner scanSymbol = new Scanner(new File("\\src\\application\\symbol.txt"));
			for(int i=0; i<atomicNum; i++)
			{
				symbol = scanSymbol.next();
			}
			scanSymbol.close();
		}
		catch(Exception e)
		{

		}
		return symbol;
	}
	
	public double getAtomicMass()
	{
		
		
		return -1;
	}
}
