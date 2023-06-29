package parking.business;

import parking.exception.PlaceOccupeeException;

/**
 * Place ou tout le monde peut se garer
 */
public class PlaceTransporteur extends Place
{
	@Override
	public void park(Vehicule v) throws PlaceOccupeeException
	{
		if(reserve && !v.immatriculation.equals(reservedImmat)) throw new PlaceOccupeeException();
		vehicule = v;
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean isTransporteur()
	{
		return true;
	}
}
