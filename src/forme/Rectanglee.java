package forme;

import java.awt.Point;
import exception.ConstructeurException;

public class Rectanglee extends Parallelogramme
{
	/**
	 * Constructeur avec param�tres
	 * @param pCentre
	 * @param pBase
	 * @param pHauteur
	 * @throws ConstructeurException
	 */
	public Rectanglee (Point pCentre, double pBase, double pHauteur) throws ConstructeurException
	{
		super(pCentre, pBase, pHauteur);
	}
	/**
	 * Constructeur avec param�tres
	 * @param pX
	 * @param pY
	 * @param pBase
	 * @param pHauteur
	 * @throws ConstructeurException
	 */
	public Rectanglee(int pX, int pY, double pBase, double pHauteur) throws ConstructeurException 
	{
		super(pX, pY, pBase, pHauteur);
	}

}
