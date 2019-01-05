package application;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.cam.ch.wwmm.opsin.NameToStructure;

public class Compound
{
	String cformula;
	String[] name;
	String smiles;
	Element element1;
	int quantity1;
	Element element2;
	int quantity2;
	String bondType;
	String bondPolarity;
	String shape;
	String moleculeShape;
	String moleculePolarity;

	public Compound(String formula)
	{
		formulaParser(formula);
		name = searchIUPACName(formula);

		NameToStructure nts = NameToStructure.getInstance();
		smiles = nts.parseToSmiles(getName());

		findBondPolarity();
		findBondType();
		findMoleculeShape();
		findMoleculePolarity();
	}

	public String[] searchIUPACName(String chemicalFormula)
    {
        org.jsoup.nodes.Document doc = null;

		try
		{
			doc = org.jsoup.Jsoup.connect("http://www.endmemo.com/chem/chemsearch.php")
			        .data("Search", "Search").data("name", chemicalFormula).data("sel", "f").post();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

        org.jsoup.select.Elements elements = doc.getElementById("note").getElementsByClass("cmline");

        if (elements.isEmpty())
            return new String[] { "No results" };

        String[] names = new String[elements.size() - 1];

        for (int i = 1; i < elements.size(); i++)
        {
            names[i - 1] = elements.get(i).getElementsByClass("cmname").get(0).getElementsByTag("a").get(0).text();
        }

        for(int i=0; i<names.length; i++)
        {
        	try
        	{
        		InputStream in = Compound.class.getResourceAsStream("/resources/name.txt");
				Scanner scanNames = new Scanner(in);
				while(scanNames.hasNextLine())
				{
					if(names[i].equals(scanNames.nextLine()))
							names[i] = "";
				}
				scanNames.close();
			} catch (Error e) {
				e.printStackTrace();
			}
        }

        return names;
    }

	public static ArrayList<String> searchCompounds(Element element1, Element element2)
	{
		ArrayList<String> temp = new ArrayList<String>();
		String num = "";

		org.jsoup.nodes.Document doc1 = null, doc2 = null;
		org.jsoup.select.Elements elements1 = null, elements2 = null;

		try {
			doc1 = org.jsoup.Jsoup.connect("http://www.endmemo.com/chem/chemsearch.php").data("Search", "Search").data("name", element1.getSymbol() + num + " " + element2.getSymbol() ).data("sel", "f").post();
			elements1 = doc1.getElementById("note").getElementsByClass("cmline");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doc2 = org.jsoup.Jsoup.connect("http://www.endmemo.com/chem/chemsearch.php").data("Search", "Search").data("name", element2.getSymbol() + num + " " + element1.getSymbol() ).data("sel", "f").post();
			elements2 = doc2.getElementById("note").getElementsByClass("cmline");
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<String> results = new ArrayList<String>();

		if(elements1!=null && elements2!=null)
		{

			for(org.jsoup.nodes.Element element : elements1)
			{
				if(element.getElementsByClass("cmformula").text()!=null)
					temp.add(element.getElementsByClass("cmformula").text());
			}

			for(org.jsoup.nodes.Element element : elements2)
			{
				if(element.getElementsByClass("cmformula").text()!=null)
					temp.add(element.getElementsByClass("cmformula").text());
			}


		    for(int i = 0; i<temp.size(); i++)
		    {
		    	if(element1.getSymbol().length() + element2.getSymbol().length() == temp.get(i).replaceAll("\\d", "").length() && temp.get(i).contains(element1.getSymbol())
		    			&& temp.get(i).contains(element2.getSymbol()) && !results.contains(subscript(temp.get(i))))
		    	{
		    		results.add(subscript(temp.get(i)));
		    	}
		    }
		}

	    if (results.isEmpty())
	    {
	        results.add("No Results!");
	    }

	    return results;
	}

	public static String subscript(String str)
	{
		str = str.replaceAll("0", "₀");
	    str = str.replaceAll("1", "₁");
	    str = str.replaceAll("2", "₂");
	    str = str.replaceAll("3", "₃");
	    str = str.replaceAll("4", "₄");
	    str = str.replaceAll("5", "₅");
	    str = str.replaceAll("6", "₆");
	    str = str.replaceAll("7", "₇");
	    str = str.replaceAll("8", "₈");
	    str = str.replaceAll("9", "₉");
	    return str;
	}

	public static String removeSubscript(String str)
	{
		str = str.replaceAll("₀", "0");
	    str = str.replaceAll("₁", "1");
	    str = str.replaceAll("₂", "2");
	    str = str.replaceAll("₃", "3");
	    str = str.replaceAll("₄", "4");
	    str = str.replaceAll("₅", "5");
	    str = str.replaceAll("₆", "6");
	    str = str.replaceAll("₇", "7");
	    str = str.replaceAll("₈", "8");
	    str = str.replaceAll("₉", "9");
	    return str;
	}

	public void formulaParser(String formula)
	{
		cformula = formula;

        final Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new LinkedHashMap<>());
        final Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)");
        final Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            final String match = matcher.group();
            if (match.equals("(")) {
                stack.push(new LinkedHashMap<>());
            } else if (match.startsWith(")")) {
                final Map<String, Integer> top = stack.pop();
                final int multiple = match.length() > 1 ? Integer.parseInt(match.substring(1, match.length())) : 1;
                for (final String name : top.keySet()) {
                    stack.peek().put(name, stack.peek().getOrDefault(name, 0) + top.get(name) * multiple);
                }
            } else {
                int i = 1;
                while (i < match.length() && Character.isLowerCase(match.charAt(i))) {
                    i++;
                }
                final String name = match.substring(0, i);
                final int count = i < match.length() ? Integer.parseInt(match.substring(i, match.length())) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + count);
            }
        }

        final StringBuilder sb = new StringBuilder();
        for (final String name : stack.peek().keySet()) {
            sb.append(name);
            sb.append(" ");

            final int count = stack.peek().get(name);
            if (count > 0) {
                sb.append(String.valueOf(count));
                sb.append(" ");
            }
        }

        String[] parsed = sb.toString().split(" ");

        for(int i = 0; i <= 3; i++)
        {
        	if(i==0)
        	{
        		element1 = new Element(symbolToNum(parsed[i]));
        	}
        	else if(i==1)
        	{
        		quantity1 = Integer.parseInt(parsed[i]);
        	}
        	else if(i==2)
        	{
        		element2 = new Element(symbolToNum(parsed[i]));
        	}
        	else if(i==3)
        	{
        		quantity2 = Integer.parseInt(parsed[i]);
        	}
        }

	}

	public int symbolToNum(String symbol)
	{
		int num = -1;
		try
		{
			InputStream in = Compound.class.getResourceAsStream("/resources/symbol.txt");
			Scanner scanSymbol = new Scanner(in);

			for(int i=1; num == -1; i++)
			{
				if(symbol.equals(scanSymbol.next()))
				{
					num =  i;
				}
			}
			scanSymbol.close();
		}
		catch (Error e)
		{
			e.printStackTrace();
		}

		return num;
	}

	public String getFormula()
	{
		return cformula;
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

	public String getBondType()
	{
		return bondType;
	}

	public String getBondPolarity()
	{
		return bondPolarity;
	}

	public String getMoleculeShape()
	{
		return moleculeShape;
	}

	public String getMoleculePolarity()
	{
		return moleculePolarity;
	}

	public void setQuantity1(int num)
	{
		quantity1 = num;
	}

	public int getQuantity1()
	{
		return quantity1;
	}

	public void setQuantity2(int num)
	{
		quantity2 = num;
	}

	public int getQuantity2()
	{
		return quantity2;
	}

	public void setQuantities(int num1, int num2)
	{
		quantity1 = num1;
		quantity2 = num2;
	}

	private void findBondPolarity()
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

	private void findBondType()
	{
		if(element1.getType()=="Metal" && element2.getType() == "Metal")
			bondType = "Metallic";
		else if(bondPolarity == "Nonpolar" || bondPolarity == "Polar")
			bondType = "Covalent";
		else if(bondPolarity == "Ionic")
			bondType = "Ionic";
	}

	private void findMoleculeShape()
	{
		moleculeShape = "Shape";
	}

	private void findMoleculePolarity()
	{

		if(getMoleculeShape().equalsIgnoreCase("Linear") || getMoleculeShape().equalsIgnoreCase("Planar") ||
		   getMoleculeShape().equalsIgnoreCase("Tetrahedral") || getMoleculeShape().equalsIgnoreCase("Octahedral") ||
		   getMoleculeShape().equalsIgnoreCase("Square Planar"))
			moleculePolarity = "Nonpolar";
		else
			moleculePolarity = "Polar";
	}


}
