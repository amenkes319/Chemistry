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

	public String getName()
	{
		return name;
	}

	public String getSymbol()
	{
		return symbol;
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

	public boolean isMetallic()
	{
		if(getType().equals("Nonmetal"))
			return false;

		return true;
	}
}
