package parking.business;

/**
 * Stratégie de calcul de cout basé sur le modele du véhicule
 *
 */
public class BrandFee implements FeeStrategy
{
	private static final long serialVersionUID = -4928362169669642830L;

	@Override
	public double calculerCout(Vehicule v)
	{
		if(v.getMarque().equals("BMW"))
			return 0; // Supreme Gentleman
		return 10;
	}

	@Override
	public String description()
	{
		return "selon la marque";
	}
}
