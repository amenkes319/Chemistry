package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import uk.ac.cam.ch.wwmm.opsin.NameToStructure;

public class Compound
{
	String formula;
	String[] name;
	String smiles;
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

		if(quantity1 == 1 && quantity2 ==1)
			formula = element1.getSymbol() + element2.getSymbol();
		else if(quantity1 == 1)
			formula = element1.getSymbol() + element2.getSymbol() + quantity2;
		else if(quantity2 == 1)
			formula = element1.getSymbol() + quantity1 + element2.getSymbol();
		else
			formula = element1.getSymbol() + quantity1 + element2.getSymbol() + quantity2;

		name = searchIUPACName(formula);

		NameToStructure nts = NameToStructure.getInstance();
		smiles = nts.parseToSmiles(getName());

		findBondPolarity();
		findBondType();
	}

	public static String[] searchIUPACName(String chemicalFormula)
    {
        org.jsoup.nodes.Document doc = null;
		try {
			doc = org.jsoup.Jsoup.connect("http://www.endmemo.com/chem/chemsearch.php")
			        .data("Search", "Search").data("name", chemicalFormula).data("sel", "f").post();
		} catch (IOException e) {
			e.printStackTrace();
		}
        org.jsoup.select.Elements elements = doc.getElementById("note").getElementsByClass("cmline");
        if (elements.isEmpty())
            return new String[] { "No results" };
        String[] names = new String[elements.size() - 1];
        for (int i = 1; i < elements.size(); i++) {
            names[i - 1] = elements.get(i).getElementsByClass("cmname").get(0).getElementsByTag("a").get(0).text();
        }

        for(int i=0; i<names.length; i++)
        {
        	try {
				Scanner scanNames = new Scanner(new File("src\\application\\name.txt"));
				while(scanNames.hasNextLine())
				{
					if(names[i].equals(scanNames.nextLine()))
							names[i] = "";
				}
				scanNames.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }

        return names;
    }

	public String getSmiles()
	{
		return smiles;
	}

	public String getName()
	{
    	for(int i = 0;i<name.length;i++)
    	{
    		if(!name[i].equals(""))
    			return name[i];
    	}
    	return "Not Found";
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
