package parking.business;

import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlusAucunePlaceException;

/**
 * Classe abstraite représentant une place
 */
public abstract class Place extends java.util.Observable
{
	static int nbInstance = 0;

	int numero;
	String reservedImmat;
	boolean reserve = false;

	protected Vehicule vehicule = null; // le vehicule garé à cette place

	public Place()
	{
		numero = nbInstance++;
	}

	/**
	 * @return true si aucune voiture n'est garée sur cette place
	 */
	public boolean isFree()
	{
		return vehicule == null;
	}

	/**
	 * @return true si une réservation a été faite sur cette place
	 */
	public boolean isReserve()
	{
		return reserve;
	}

	/**
	 * @param immat
	 * @return true si une réservation a été faite pour ce véhicule, false dans le cas contraire ou si l'immatricule correspond au véhicule qui a effectuée la reservation
	 */
	public boolean isReserve(String immat)
	{
		return !immat.equals(reservedImmat) && reserve;
	}

	/**
	 * @return Le véhicule stationné
	 */
	public Vehicule getParkedVehicule()
	{
		return vehicule;
	}

	/**
	 * Retire la réservation faite sur cette place
	 */
	public void liberer()
	{
		reserve = false;
		reservedImmat = null;
		setChanged();
		notifyObservers();
	}

	/**
	 * Réserve la place pour un véhicule
	 * @param immat
	 * @throws PlusAucunePlaceException
	 */
	public void reserver(String immat) throws PlusAucunePlaceException
	{
		if (reserve) throw (new PlusAucunePlaceException());
		else
		{
			reservedImmat = immat;
			reserve = true;
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return Le numéro de place
	 */
	public int getNumero()
	{
		return numero;
	}

	/**
	 * @return true si cette place est faite pour les transporteurs
	 */
	public abstract boolean isTransporteur();

	/**
	 * Essaye de placer un véhicule dans cette place
	 * @param v
	 * @throws PlaceOccupeeException
	 */
	public abstract void park(Vehicule v) throws PlaceOccupeeException;

	/**
	 * Retire le véhicule de cette place
	 * @throws PlaceLibreException 
	 */
	public void retirer() throws PlaceLibreException
	{
		if(vehicule == null) throw new PlaceLibreException();
		Vehicule tmp = vehicule;
		vehicule = null;
		setChanged();
		System.out.println("Notification");
		notifyObservers(tmp);
	}
}
