package parking.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le systeme de facturation
 */
public class Facture implements Serializable
{
	private static final long serialVersionUID = -9039700930999803534L;

	private static int numFactures = 0;
	private static List<Facture> factures = load();
	private FeeStrategy tarif = Constante.tarif;
	private int numFacture = ++numFactures;

	Vehicule vehicule;

	/**
	 * @return La liste de toutes les factures enregistrées
	 */
	public static List<Facture> getFactures()
	{
		return factures;
	}
	
	/**
	 * Génere une facture pour un véhicule donné
	 * @param v
	 */
	public Facture(Vehicule v)
	{
		vehicule = v;
		factures.add(this);
	}
	
	/**
	 * @return Le prix de stationnement Hors Taxe
	 */
	public double getHT()
	{
		return tarif.calculerCout(vehicule);
	}

	/**
	 * @return Le prix de stationnement TTC
	 */
	public double getTTC()
	{
		return getHT() + getHT() * Constante.TVA / 100;
	}
	
	public String toString()
	{
		return "Facture n" + numFacture + ":\n" + vehicule + "\nTarif calculé " + tarif.description() + "\n--------------------------------------------------\n" + getHT() + " HT; " + getTTC() + "TTC\n";
	}

	@SuppressWarnings("unchecked")
	private static List<Facture> load()
	{
		List<Facture> factures = new ArrayList<Facture>();
		File fact = new File("factures/factures.bin");
		if(!fact.exists()) return factures;
		FileInputStream fos;
		ObjectInputStream oos;
		try
		{
			fos = new FileInputStream("factures/" + "factures.bin");
			oos = new ObjectInputStream(fos);
			factures = (List<Facture>) oos.readObject();
			numFactures = factures.size();
			oos.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factures;
	}
	
	/**
	 * Sauvegarde toute les facture a enregistrer
	 */
	public static void save()
	{
		File dir = new File("factures");
		if(!dir.exists())
			dir.mkdir();
		FileOutputStream fos;
		try
		{
			fos = new FileOutputStream("factures/" + "factures.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(factures);
			oos.close();
		}
		catch (Exception e)
		{
			System.err.print("N'a pas pu sauvegarder les factures, enjoy.");
		}
	}
}
