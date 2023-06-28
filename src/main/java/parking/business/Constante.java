package parking.business;

/**
 * Classe contenant des constante accessible partout.
 */
public class Constante
{
	/**
	 * Nombre de Places pour particuliers dans le parking
	 */
	public static final int nbPlaceParticulier = 20;
	/**
	 * Nombre de Places pour transporteurs dans le parking
	 */
	public static final int nbPlaceTranporteur = 10;
	/**
	 * Valeur de la TVA
	 */
	public static final double TVA = 19.6;
	/**
	 * Strategie de calcul du tarif
	 */
	public static final FeeStrategy tarif = new BrandFee();
}
