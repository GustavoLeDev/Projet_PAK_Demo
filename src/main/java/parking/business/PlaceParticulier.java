package parking.business;

import parking.exception.PlaceOccupeeException;

/**
 * Place ou seul les véhicules particuliers peuvent se garer
 */
public class PlaceParticulier extends Place
{
	@Override
	public void park(Vehicule v) throws PlaceOccupeeException
	{
		if(v.isTransporteur() || (reserve && !v.immatriculation.equals(reservedImmat))) throw new PlaceOccupeeException();
		else vehicule = v;
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean isTransporteur()
	{
		return false;
	}

}
