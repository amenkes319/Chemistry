package application;
import org.openscience.cdk.*;

public class Compound
{
	AtomContainer comp;
	String name;
	Element element1;
	int quantity1;
	Element element2;
	int quantity2;
	String bondType;
	String bondPolarity;
	String shape;

	public Compound(Element one, int x, Element two, int y)
	{
		element1 = one;
		quantity1 = x;
		element2 = two;
		quantity2 = y;

		findBondPolarity();
		findBondType();

		createContainer();
	}


	public void createContainer()
	{
		AtomContainer x = new AtomContainer();
		Atom one = new Atom(element1.getSymbol());
		Atom two = new Atom(element2.getSymbol());

		for(int i = 0; i<quantity1; i++)
		{
			x.addAtom(one);
		}

		for(int i = 0; i<quantity2; i++)
		{
			x.addAtom(two);
		}

		comp = x;
	}

	public String getName()
	{
		return name;
	}

	public void findBondType()
	{
		if(element1.getType()=="Metal" && element2.getType() == "Metal")
			bondType = "Metallic";
		else if(bondPolarity == "Nonpolar" || bondPolarity == "Polar")
			bondType = "Covalent";
		else if(bondPolarity == "Ionic")
			bondType = "Ionic";
	}

	public String getBondType()
	{
		return bondType;
	}

	public void findBondPolarity()
	{
		String polarity = null;

		Double e1 = element1.getElectronegativity();
		Double e2 = element2.getElectronegativity();
		Double difference = (Math.max(e1, e2) - Math.min(e1,e2));

		if( difference >= 0 && difference <=.3 )
			polarity = "Nonpolar";
		else if( difference > .3 && difference <= 1.7)
			polarity = "Polar";
		else if(difference > 1.7)
			polarity = "Ionic";

		bondPolarity = polarity;
	}

	public String getBondPolarity()
	{
		return bondPolarity;
	}

	public String getMoleculeShape()
	{
		String moleculeShape = "Shape";


		return moleculeShape;
	}

}
