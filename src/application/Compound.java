package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

		findFormula();
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


	public static ArrayList<String> searchCompounds(Element element1, Element element2)
	{
		ArrayList<String> temp = new ArrayList<String>();

		org.jsoup.nodes.Document doc1 = null, doc2 = null;
		try {
			doc1 = org.jsoup.Jsoup.connect("http://www.endmemo.com/chem/chemsearch.php").data("Search", "Search").data("name", element1.getSymbol() + " " + element2.getSymbol() ).data("sel", "f").post();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			doc2 = org.jsoup.Jsoup.connect("http://www.endmemo.com/chem/chemsearch.php").data("Search", "Search").data("name", element2.getSymbol() + " " + element1.getSymbol() ).data("sel", "f").post();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    org.jsoup.select.Elements elements1 = doc1.getElementById("note").getElementsByClass("cmline");
	    org.jsoup.select.Elements elements2 = doc2.getElementById("note").getElementsByClass("cmline");

	    for(org.jsoup.nodes.Element element : elements1)
	    {
	    	temp.add(element.getElementsByClass("cmformula").text());
	    }

	    for(org.jsoup.nodes.Element element : elements2)
	    {
	    	temp.add(element.getElementsByClass("cmformula").text());
	    }

	    ArrayList<String> results = new ArrayList<String>();

	    for(int i = 0; i<temp.size(); i++)
	    {
	    	if(element1.getSymbol().length() + element2.getSymbol().length() == temp.get(i).replaceAll("\\d", "").length() && temp.get(i).contains(element1.getSymbol()) && temp.get(i).contains(element2.getSymbol()) && !results.contains(temp.get(i)))
	    	{
	    		results.add(temp.get(i));
	    	}
	    }

	    if (results.isEmpty())
	    {
	        results.add("No Results!");
	    }

	    return results;
	}

	public void findFormula()
	{

	}

	public Element getElement1()
	{
		return element1;
	}

	public Element getElement2()
	{
		return element2;
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

	public void setQuantity1(int num)
	{
		quantity1 = num;
	}

	public void setQuantity2(int num)
	{
		quantity2 = num;
	}

	public void setQuantities(int num1, int num2)
	{
		quantity1 = num1;
		quantity2 = num2;
	}

	public String getMoleculeShape()
	{
		String moleculeShape = "Shape";
		return moleculeShape;
	}

//	public void order()
//	{
//		ArrayList<Element> elements = new ArrayList<Element>(Arrays.asList(element1,element2));
//		ArrayList<String> symbols = new ArrayList<String>(Arrays.asList(element1.getSymbol(), element2.getSymbol()));
//
//		if(bondType=="Covalent")
//		{
//			int order1 = 0;
//			int order2 = 0;
//			int[] orders = new int[]{order1, order2};
//
//			Collections.sort(symbols);
//
//			if(symbols.contains("C"))
//			{
//				for(int i = 0; i < 1; i++)
//				{
//					if(symbols.get(i).equals("C"))
//						orders[i]=0;
//					else if(symbols.get(i).equals("H"))
//						orders[i]=1;
//					else
//						orders[i]=2;
//				}
//			}
//			else
//			{
//				for(int i = 0; i < 1; i++)
//				{
//					if(symbols.get(i).equals("H"))
//						orders[i]=0;
//					else
//						orders[i]=1;
//				}
//			}
//			if(orders[1]<orders[0])
//				Collections.swap(elements, 0, 1);
//		}
//		else if(bondType=="Ionic")
//		{
//			if(element1.getElectronegativity()>element2.getElectronegativity())
//				Collections.swap(elements, 0, 1);
//		}
//
//		element1 = elements.get(0);
//		element2 = elements.get(1);
//	}
}
