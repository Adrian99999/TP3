package forme;

import java.awt.Point;
import exception.ConstructeurException;

public class Carre extends Rectanglee
{
	public Carre (Point pCentre, double pCote) throws ConstructeurException
	{
		this ((int) pCentre.getX(), (int) pCentre.getY(), pCote);
	}
	
	public Carre (int pX, int pY, double pCote) throws ConstructeurException
	{
		super(pX,pY, pCote,pCote);
	}
	/**
	 * Modifier la valeur de la base si elle = à la hauteur
	 * @param pBase la novelle base
	 * @return boolean, vrai si la valeur a été modifiée
	 */
	public boolean setBase (double pBase)
	{
		//boolean ok = pBase == this.getHauteur();
		return this.setBase(pBase);
	}
	/**
	 * Modifier la valeur de la hauteur si elle = à la base
	 * @param pHauteur la nouvelle hauteur
	 * @return boolean, vrai si la valeur a été modifiée
	 */
	public boolean setHauteure (double pHauteur)
	{
		//boolean ok = pHauteur == this.getBase();
		return this.setHauteur(pHauteur);
		
	}
	public static boolean validerCote(double pCote)
	{
		return pCote > 0;
	}
	/**
	 * Valider la valeur du côté du carré, doit être abligatoirement > 0
	 * @param pCote
	 * @return boolean, vrai si la valeur est valide
	 */
	public boolean setCote(double pCote)
	{
		boolean ok = validerCote(pCote);
		if(ok)
		{
			setHauteur(pCote);
			setBase(pCote);
		}
		return false;
	}
}
