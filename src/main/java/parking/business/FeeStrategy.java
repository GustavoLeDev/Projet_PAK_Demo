package parking.business;

import java.io.Serializable;


/**
 * Interface définissant la stratégie de calcul du tarif
 */
public interface FeeStrategy extends Serializable
{
	/**
	 * @param v
	 * @return Le tarif de base pour un véhicule donné
	 */
	public double calculerCout(Vehicule v);
	/**
	 * @return Une chaine décrivant comment est opéré le calcul du tarif de base
	 */
	public String description();
}
