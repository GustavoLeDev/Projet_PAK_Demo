package parking.business;

import java.io.Serializable;

/**
 * Classe abstraite représentant un véhicule
 */
public abstract class Vehicule implements Serializable
{
	private static final long serialVersionUID = 6908533054919923910L;
	String immatriculation;
	String modele;
	String marque;
	String proprietaire;

	public Vehicule(String imm, String mod, String mar, String prop)
	{
		this.immatriculation = imm;
		this.modele = mod;
		this.marque = mar;
		this.proprietaire = prop;
	}

	/**
	 * @return true si le véhicule est de type transporteur
	 */
	public abstract boolean isTransporteur();

	public String getImmatriculation()
	{
		return immatriculation;
	}

	public String getModele()
	{
		return modele;
	}

	public String getMarque()
	{
		return marque;
	}

	public String getProprietaire()
	{
		return proprietaire;
	}
	
	public String toString()
	{
		return getClass().getSimpleName() + " immatriculé " + immatriculation + ", appartenant a " + proprietaire + ", de la marque "  + marque
				+ ", ayant pour modele " + modele;
	}
}
