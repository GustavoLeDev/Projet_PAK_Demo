package parking.business;

/**
 * Classe du véhicule particulier Voiture
 */
public class Voiture extends Vehicule
{
	private static final long serialVersionUID = 8047176247375201388L;

	public Voiture(String imm, String mod, String mar, String prop)
	{
		super(imm, mod, mar, prop);
	}

	public boolean isTransporteur()
	{
		return false;
	}

}
