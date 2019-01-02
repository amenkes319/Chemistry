package application;

import java.util.Scanner;
import java.io.*;

public class Element
{
	int atomicNum;
	String name;
	String symbol;
	double mass;
	int neutrons;
	int group;
	int period;
	String diatomic;
	int ionization;
	double electronegativity;
	int melting;
	int boiling;
	double density;
	int radius;
	String eConfig;
	String phase;
	String type;
	String states;

	public Element(int a)
	{
		atomicNum = a;

		try
		{
			Scanner scanInfo = new Scanner(new File("src\\application\\element database.txt"));

			for(int i=0; i<(atomicNum-1) * 18;i++)
			{
				scanInfo.nextLine();
			}

			name = scanInfo.nextLine();
			symbol = scanInfo.nextLine();
			scanInfo.nextLine();
			mass = Double.parseDouble(scanInfo.nextLine());
			neutrons = Integer.parseInt(scanInfo.nextLine());
			group = Integer.parseInt(scanInfo.nextLine());
			period = Integer.parseInt(scanInfo.nextLine());
			diatomic = scanInfo.nextLine();
			ionization = Integer.parseInt(scanInfo.nextLine());
			electronegativity = Double.parseDouble(scanInfo.nextLine());
			melting = Integer.parseInt(scanInfo.nextLine());
			boiling = Integer.parseInt(scanInfo.nextLine());
			density = Double.parseDouble(scanInfo.nextLine());
			radius = Integer.parseInt(scanInfo.nextLine());
			eConfig = scanInfo.nextLine();
			phase = scanInfo.nextLine();
			type = scanInfo.nextLine();
			states = scanInfo.nextLine();

			scanInfo.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public int getAtomicNum()
	{
		return atomicNum;
	}

	public String getName()
	{
		return name;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public static int symbolToNum(String symbol)
	{
		int num = -1;
		try
		{
			Scanner scanSymbol = new Scanner(new File("src\\application\\symbol.txt"));

			for(int i=1; num!=-1; i++)
			{
				if(symbol.equals(scanSymbol.next()))
				{
					num =  i;
				}
			}
			scanSymbol.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return num;
	}

	public double getAtomicMass()
	{
		return mass;
	}

	public int getNeutrons()
	{
		return neutrons;
	}

	public int getGroup()
	{
		return group;
	}

	public int getPeriod()
	{
		return period;
	}

	public String getDiatomic()
	{
		return diatomic;
	}

	public int getIonizationEnergy()
	{
		return ionization;
	}

	public double getElectronegativity()
	{
		return electronegativity;
	}

	public int getMeltingPoint()
	{
		return melting;
	}

	public int getBoilingPoint()
	{
		return boiling;
	}

	public double getDensity()
	{
		return density;
	}

	public int getAtomicRadius()
	{
		return radius;
	}

	public String getEConfig()
	{
		return eConfig;
	}

	public String getPhase()
	{
		return phase;
	}

	public String getType()
	{
		return type;
	}

	public String getOxidationState()
	{
		return states;
	}

	public int[] getStates()
	{
		String[] arr = states.split(", ");
		int[] numbers = new int[arr.length];

		for(int i = 0; i < arr.length; i++)
		{
			numbers[i] = Integer.parseInt(arr[i]);
		}

		return numbers;
	}
}
