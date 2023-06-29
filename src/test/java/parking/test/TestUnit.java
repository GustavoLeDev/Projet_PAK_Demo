package parking.test;

import org.junit.*;
import org.junit.rules.ExpectedException;

import parking.business.Camion;
import parking.business.Constante;
import parking.business.Parking;
import parking.business.Voiture;
import parking.exception.PlaceDisponibleException;
import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlusAucunePlaceException;

public class TestUnit
{
	Parking parking;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception
	{
		parking = Parking.getInstance();
	}

	@Test
	public void ParkOccupé() throws PlaceOccupeeException
	{
		thrown.expect(PlaceOccupeeException.class);
		parking.park(new Voiture("a", "a", "a", "a"), 0);
		parking.park(new Voiture("b", "b", "b", "b"), 0);
	}

	@Test
	public void ParkReservé() throws PlusAucunePlaceException
	{
		thrown.expect(PlusAucunePlaceException.class);
		parking.bookPlace(new Voiture("b", "b", "b", "b"), 1);
		parking.bookPlace(new Voiture("a", "a", "a", "a"), 1);
	}

	@Test
	public void NonTrouvé() throws PlaceOccupeeException 
	{
		parking.park(new Voiture("a", "a", "a", "a"));
		assert(parking.getLocation(new String("a")) != -1);
		assert(parking.getLocation(new String("a")) == 0);
		assert(parking.getLocation(new String("c")) == -1);
	}

	@Test
	public void TransporteurSurParticulier() throws PlaceOccupeeException 
	{
		parking.park(new Camion("a", "a", "a", "a"), Constante.nbPlaceParticulier + 1);
		thrown.expect(PlaceOccupeeException.class);
		parking.park(new Camion("b", "a", "a", "a"), 2);
	}

	@Test
	public void retirerVide() throws PlaceOccupeeException, PlaceLibreException, PlusAucunePlaceException 
	{
		thrown.expect(PlaceLibreException.class);
		parking.bookPlace(new Camion("b", "a", "a", "a"), 2).retirer();
	}

	@Test
	public void reserver2fois() throws PlaceOccupeeException, PlaceLibreException, PlusAucunePlaceException 
	{
		thrown.expect(PlusAucunePlaceException.class);
		parking.bookPlace(new Camion("b", "a", "a", "a"), 2);
		parking.bookPlace(new Camion("1", "a", "a", "a"), 2);
	}

	@Test
	public void unparkLibre() throws PlaceLibreException, PlaceOccupeeException
	{
		thrown.expect(PlaceLibreException.class);
		parking.unpark(2);
	}

	@Test
	public void freeLibre() throws PlaceDisponibleException
	{
		thrown.expect(PlaceDisponibleException.class);
		parking.freePlace(6);
	}
}
