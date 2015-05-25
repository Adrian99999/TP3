package forme;

import exception.ConstructeurException;
import java.awt.Point;
/**
 * Classe abstraite principale pour des formes g�om�triques plac�es dans un plan positif
 */
public abstract class Forme implements Comparable<Forme>, Deplasable
{
	/**
	 * Le point center de la forme
	 */
	private Point center = null;
	
	/**
	 * Constructeur avec parametres
	 * @throws ConstructeurException 
	 */
	public Forme (Point pCentre) throws ConstructeurException
	{
		this((int) pCentre.getX(), (int) pCentre.getY());
	}
	/**
	 * Constructeur avec parametres 
	 * @param pX coordonn�e X du point centre de la forme
	 * @param pY coordonn�e Y du point centre de la forme
	 * @throws ConstructeurException
	 */
	public Forme (int pX, int pY) throws ConstructeurException
	{
		boolean ok = setCentre (pX, pY);
		if (!ok)
		{
			throw new ConstructeurException();
		}
	}
	/**
	 * Obtenir le point centre de la forme
	 * @return 
	 */
	public Point getCentre()
	{
		return center;
	}
	/**
	 * Modifier la valeur du point centre d'une forme
	 * @param pX
	 * @param pY
	 * @return
	 */
	public boolean setCentre(int pX, int pY) {
		boolean ok = validerX(pX) && validerY(pY);
		if(ok)
		{
			//center.x = pX;
			//center.y = pY;
			center = new Point (pX, pY);
		}
		return ok;
	}
	/**
	 * Modifier la valeur du point centre d'une forme
	 * @param pCentre
	 * @return boolean, vrai si la mise � jour a �t� effectu�
	 */
	public boolean setCentre(Point pCentre)
	{
		boolean ok = validerPoint(pCentre);
		if (ok)
		{
			center = pCentre;
		}
		return ok;
	}
	/**
	 * Valider la coordonn�e X du point centre de la forme
	 * @param pX la coordonn�e X du point centre de la forme
	 * @return boolean, si la valeur de X >=0
	 */
	public static boolean validerX(int pX)
	{
		return pX>=0;
	}
	/**
	 * Valider la coordonn�e Y du point de la forme
	 * @param pY la coordonn�e Y du point centre de la forme
	 * @return boolean , si la valeur de Y >= 0
	 */
	public static boolean validerY(int pY)
	{
		return pY>=0;
	}
	/**
	 * Valider les coordonn�es que contient un object point pour devenir le centre de la forme
	 * @param pCentre le point � valider
	 * @return varai si les coordonn�es du point sont valides
	 */
	public static boolean validerPoint(Point pCentre)
	{
		return validerX((int)pCentre.getX()) && validerY((int) pCentre.getY());
	}
	/**
	 * Obtenir la valeur de la coordonn�e X du point centre de la forme
	 * @return
	 */
	public int getX() {
		return (int) this.center.getX();
	}
	public int getY()
	{
		return (int) this.center.getY();
	}
	/**
	 * Valeur du p�rim�tre de la forme
	 * @return double, la valeur du p�rim�tre
	 */
	public abstract double perimetre();
	/**
	 * Valeur de l'aire de la forme
	 * @return double, la valeur de l'aire
	 */
	public abstract double aire();
	/**
	 * Obtenir le nombre des cot�
	 * @return int, le nombre de cot�
	 */
	public abstract int getNbrCote();
	
	public abstract String toString();
	/**
	 * Permet de d�caler le point centre d'une forme g�om�trique de delta x et de delta y. Il faut faire attention de rester dans le plan positif entre
	 * [0, le plus grand des entiers]
	 * 
	 * @param pDeltaX le d�placement sur l'axe des x
	 * @param pDeltaY le d�placement sur l'axe des y
	 */
	public boolean deplacer(int pDeltax, int pDeltaY)
	{
		return false;
	}
}
