package parking.business;

/**
 * Classe du véhicule particulier Moto
 */
public class Moto extends Vehicule
{
	private static final long serialVersionUID = 1L;

	public Moto(String imm, String mod, String mar, String prop)
	{
		super(imm, mod, mar, prop);
	}

	public boolean isTransporteur()
	{
		return false;
	}
}
