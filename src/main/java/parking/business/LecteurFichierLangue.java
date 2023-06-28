package parking.business;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Classe permettant de lire la représentation d'une chaine littérale a partir d'un fichier
 */
public class LecteurFichierLangue
{
	String etat;
	String placeNum;
	String libre;
	String occuppee;
	String reservee;

	public LecteurFichierLangue(String langue)
	{
		InputStream ips;
		try
		{
			ips = new FileInputStream("assets/" + langue + ".txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			etat = br.readLine();
			placeNum = br.readLine();
			libre = br.readLine();
			occuppee = br.readLine();
			reservee = br.readLine();
			br.close();
			ips.close();
		}
		catch (FileNotFoundException e)
		{
			ips = getClass().getResourceAsStream("/assets/" + langue+ ".txt");
		}
		catch (IOException e)
		{
			System.err.println("Erreur de lecture, enjoy");
		}
	}

	public String getEtat()
	{
		return etat;
	}

	public String getPlaceNum()
	{
		return placeNum;
	}

	public String getLibre()
	{
		return libre;
	}

	public String getOccuppee()
	{
		return occuppee;
	}

	public String getReservee()
	{
		return reservee;
	}
}
