package application;

import java.util.Scanner;
import java.io.*;

public class Element
{
	int atomicNum;
	final int NUM_OF_ELEMENTS;

	public Element(int a)
	{
		atomicNum = a;
		NUM_OF_ELEMENTS = 89;
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return symbol;
	}

	public double getAtomicMass()
	{
		String atomicMass = "";
		try
		{
			Scanner scanMass = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 4 ;i++)
			{
				atomicMass = scanMass.nextLine();
			}
			scanMass.close();
			return Double.parseDouble(atomicMass);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public int getNumOfNeutrons()
	{
		String neutrons = "";
		try
		{
			Scanner scanGroup = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 5 ;i++)
			{
				neutrons = scanGroup.nextLine();
			}
			scanGroup.close();
			return Integer.parseInt(neutrons);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public int getGroup()
	{
		String group = "";
		try
		{
			Scanner scanGroup = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 6 ;i++)
			{
				group = scanGroup.nextLine();
			}
			scanGroup.close();
			return Integer.parseInt(group);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public int getPeriod()
	{
		String period = "";
		try
		{
			Scanner scanPeriod = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 7 ;i++)
			{
				period = scanPeriod.nextLine();
			}
			scanPeriod.close();
			return Integer.parseInt(period);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public String getDiatomic()
	{
		String diatomic = "";
		try
		{
			Scanner scanDiatomic = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 8 ;i++)
			{
				diatomic = scanDiatomic.nextLine();
			}
			scanDiatomic.close();
			return diatomic;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return "";
	}

	public int getIonizationEnergy()
	{
		String ionEn = "";
		try
		{
			Scanner scanIE = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 9 ;i++)
			{
				ionEn = scanIE.nextLine();
			}
			scanIE.close();
			return Integer.parseInt(ionEn);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public double getElectronegativity()
	{
		String electroNeg = "";
		try
		{
			Scanner scanEN = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 10 ;i++)
			{
				electroNeg = scanEN.nextLine();
			}
			scanEN.close();
			return Double.parseDouble(electroNeg);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	public int getMeltingPoint()
	{
		String meltingPt = "";
		try
		{
			Scanner scanMeltingPt = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 11 ;i++)
			{
				meltingPt = scanMeltingPt.nextLine();
			}
			scanMeltingPt.close();
			return Integer.parseInt(meltingPt);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	public int getBoilingPoint()
	{
		String boilingPt = "";
		try
		{
			Scanner scanBoilingPt = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 12 ;i++)
			{
				boilingPt = scanBoilingPt.nextLine();
			}
			scanBoilingPt.close();
			return Integer.parseInt(boilingPt);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	public double getDensity()
	{
		String density = "";
		try
		{
			Scanner scanDensity = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 13 ;i++)
			{
				density = scanDensity.nextLine();
			}
			scanDensity.close();
			return Double.parseDouble(density);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	public int getAtomicRadius()
	{
		String atomicRadius = "";
		try
		{
			Scanner scanAtomicRadius = new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 14 ;i++)
			{
				atomicRadius = scanAtomicRadius.nextLine();
			}
			scanAtomicRadius.close();
			return Integer.parseInt(atomicRadius);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	public String getElectronConfiguration()
	{
		String eConfig = "";
		try
		{
			Scanner scanEConfig= new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 15 ;i++)
			{
				eConfig = scanEConfig.nextLine();
			}
			scanEConfig.close();
			return eConfig;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return "";
	}
	
	public String getPhase()
	{
		String phase = "";
		try
		{
			Scanner scanPhase= new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 16 ;i++)
			{
				phase = scanPhase.nextLine();
			}
			scanPhase.close();
			return phase;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return "";
	}
	
	public String getType()
	{
		String type = "";
		try
		{
			Scanner scanType= new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 17 ;i++)
			{
				type = scanType.nextLine();
			}
			scanType.close();
			return type;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return "";
	}
	
	public int getOxidationState()
	{
		String oxidState = "";
		try
		{
			Scanner scanOxidState= new Scanner(new File("src\\application\\element database.txt"));
			for(int i=0; i<(atomicNum-1) * 18 + 15 ;i++)
			{
				oxidState = scanOxidState.nextLine();
			}
			scanOxidState.close();
			return Integer.parseInt(oxidState);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return -1;
	}
}
