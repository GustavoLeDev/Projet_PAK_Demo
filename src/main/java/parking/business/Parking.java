package parking.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Stack;

import parking.exception.*;
import parking.gui.PlaceButton;

/**
 * Classe représantant un parking
 *
 */
public class Parking
{
	private static Parking instance;
	private List<Place> places;
	// A modifier pour changer la langue (Doit fournir le fichier *Langue*.txt
	private String langue = "Francais";

	/**
	 * @return L'instance du parking
	 */
	public static Parking getInstance()
	{
		if (instance == null) instance = new Parking();
		return instance;
	}

	protected Parking()
	{
		places = new ArrayList<Place>();
		for(int i = 0; i < Constante.nbPlaceParticulier; ++i)
			places.add(new PlaceParticulier());
		for(int i = 0; i < Constante.nbPlaceTranporteur; ++i)
			places.add(new PlaceTransporteur());
	}

	/**
	 * @param v
	 * @return vrai si le véhicule est dans le parking
	 */
	public boolean vehiculeExiste(Vehicule v)
	{
		return places.contains(v);
	}

	/**
	 * Place un véhicule dans le parking a la premiere place trouvée
	 * @param v
	 * @throws PlaceOccupeeException
	 */
	public void park(Vehicule v) throws PlaceOccupeeException
	{
		for (Place place : places)
		{
			try
			{
				place.park(v);
				return;
			}
			catch (PlaceOccupeeException poe) // On essaye de le placer dans le parking
			{
			}
		}
		throw new PlaceOccupeeException();
	}

	/**
	 * Essaye de placer un véhicule dans la place ayant pour numéro celui passé en parametre
	 * @param v
	 * @param place
	 * @throws PlaceOccupeeException
	 */
	public void park(Vehicule v, int place) throws PlaceOccupeeException
	{
		park(v, places.get(place));
	}

	/**
	 * Essaye de placer un véhicule dans une place donnée
	 * @param v
	 * @param place
	 * @throws PlaceOccupeeException
	 */
	public void park(Vehicule v, Place place) throws PlaceOccupeeException
	{
		if (!place.isReserve(v.getImmatriculation()) && place.isFree()) place.park(v);
		else throw new PlaceOccupeeException();
	}

	
	/**
	 * Essaye de retirer le véhicule d'une place donnée
	 * @param p
	 * @return Le véhicule qui se trouvait dans la place
	 * @throws PlaceLibreException
	 * @throws PlaceOccupeeException
	 */
	public Vehicule unpark(Place p) throws PlaceLibreException, PlaceOccupeeException
	{
		Vehicule v = p.getParkedVehicule();
		if (v == null) throw new PlaceLibreException();
		p.retirer();
		reorganiserPlace();
		return v;
	}

	/**
	 * Essaye de retirer le véhicule selon le numéro d'une place donnée
	 * @param place
	 * @return Le véhicule qui se trouvait dans la place
	 * @throws PlaceLibreException
	 * @throws PlaceOccupeeException
	 */
	public Vehicule unpark(int place) throws PlaceLibreException, PlaceOccupeeException
	{
		return unpark(places.get(place));
	}

	/**
	 * Affiche un représentation de l'état du parking sur la sortie standard
	 */
	public void etatParking()
	{
		LecteurFichierLangue lg = new LecteurFichierLangue(langue);
		System.out.print(lg.getEtat() + " : \n [");
		for (int i = 0; i < places.size() - 1; ++i)
		{
			System.out.print(lg.getPlaceNum() + " : " + i + " : ");
			if (places.get(i).isFree()) System.out.print(lg.getLibre());
			else System.out.print(lg.getOccuppee());
			if (places.get(i).isReserve()) System.out.println(lg
					.getReservee());
		}
		System.out.print("];");
	}

	/**
	 * Marque la premiere place libre trouvé en tant que réservée pour un véhicule donné, seul ce véhicule pourra s'y garer
	 * @param v
	 * @return La place réservée
	 * @throws PlusAucunePlaceException
	 */
	public Place bookPlace(Vehicule v) throws PlusAucunePlaceException
	{
		for (Place place : places)
			try
			{
				return bookPlace(v, place);
			}
			catch(PlusAucunePlaceException pape)
			{
			}
		throw new PlusAucunePlaceException();
	}

	
	/**
	 * Essaye de réserver la place pour un véhicule donné a un numéro de place donné
	 * @param v
	 * @param emplacement
	 * @return La place réservée
	 * @throws PlusAucunePlaceException
	 */
	public Place bookPlace(Vehicule v, int emplacement) throws PlusAucunePlaceException
	{
		return bookPlace(v, places.get(emplacement));
	}

	/**
	 * Essaye de réserver la place pour un véhicule donné a une de place donné
	 * @param v
	 * @param p
	 * @return La place réservée
	 * @throws PlusAucunePlaceException
	 */
	public Place bookPlace(Vehicule v, Place p) throws PlusAucunePlaceException
	{
		if (!p.isReserve() && p.isFree())
		{
			p.reserver(v.getImmatriculation());
			return p;
		}
		throw new PlusAucunePlaceException();
	}

	/**
	 * Annule la réservation de la place au numéro donné
	 * @param i
	 * @throws PlaceDisponibleException
	 */
	public void freePlace(int i) throws PlaceDisponibleException
	{
		freePlace(places.get(i));
	}

	/**
	 * Annule la réservation de la place donné
	 * @param p
	 * @throws PlaceDisponibleException
	 */
	public void freePlace(Place p) throws PlaceDisponibleException
	{
		if (!p.isReserve()) throw new PlaceDisponibleException();
		else p.liberer();
	}

	
	/**
	 * @param immat
	 * @return La position dans le parking de la voiture ayant l'immatricule donné en parametre
	 */
	public int getLocation(String immat)
	{
		for (int i = 0; i < places.size() - 1; ++i)
			if (!(places.get(i).isFree())
					&& places.get(i).getParkedVehicule().getImmatriculation().equals(immat)) return i;
		return -1;
	}

	/**
	 * Retire du parking un véhicule selon son immatricule
	 * @param immat
	 * @return null si le véhicule n'a pas été trouvé, sinon le véhicule
	 * @throws PlaceLibreException
	 */
	public Vehicule retirerVehicule(String immat) throws PlaceLibreException
	{
		try
		{
			return unpark(getLocation(immat));
		}
		catch (PlaceOccupeeException e)
		{
		}
		return null;
	}

	protected void reorganiserPlace() throws PlaceLibreException, PlaceOccupeeException
	{
		Stack<Place> placesLibres = new Stack<Place>();
		for (Place p : places.subList(0, Constante.nbPlaceParticulier))
			if (!p.isTransporteur() && p.isFree() && !p.isReserve()) placesLibres.push(p);
		for (Place p : places.subList(Constante.nbPlaceParticulier,
				Constante.nbPlaceParticulier + Constante.nbPlaceTranporteur))
			if (!p.isFree() && !p.getParkedVehicule().isTransporteur()) park(
					unpark(places.get(p.getNumero())), placesLibres.pop());
	}
	
	/**
	 * Place des observateur sur les places, la liste doit contenir autant d'observateur que de place a observer
	 * @param observer
	 * @throws PasAssezDObservateurException
	 */
	public void observePlaces(List<? extends Observer> observer) throws PasAssezDObservateurException
	{
		if(observer.size() != places.size()) throw new PasAssezDObservateurException();
		Iterator<Place> placeIt = places.iterator();
		Iterator<? extends Observer> obsIt = observer.iterator();
		while(placeIt.hasNext() && obsIt.hasNext())
		{
			Place p = placeIt.next();
			PlaceButton pb = (PlaceButton) obsIt.next();
			pb.setPlace(p);
			p.addObserver(pb);
		}
	}
}
