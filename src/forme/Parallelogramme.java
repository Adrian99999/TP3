package forme;

import java.awt.Point;
import java.text.DecimalFormat;

import exception.ConstructeurException;
/**
 * 
 * @author 1492489
 *Si un quadrilatère est un parallélogramme alors :
					il a un centre de symétrie : le point d'intersection de ses diagonales ;
    				ses diagonales ont le même milieu ;
    				ses côtés opposés ont la même longueur ;
    				ses angles opposés sont égaux ;
    				ses angles consécutifs sont supplémentaires.
 */
public class Parallelogramme extends Forme
{
	/**
	 * Nombre de côtés d'un parallélogramme
	 */
	private static final int NBR_COTE_PARALLELOGRAMME = 4;
	/**
	 * La longeur de la base du parallelogramme, doit être obligatoirement > 0
	 */
	private double base = 0;
	/**
	 * La hauteur de la base du parallelogramme, doit être obligatoirement > 0 
	 */
	private double hauteur = 0;
	
	DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public Parallelogramme(Point pCentre, double pBase, double pHauteur)
	{
		super(pCentre);
		boolean ok = validerBase(pBase) && validerHauteur(pHauteur);
		if(ok)
		{
			base = pBase;
			hauteur = pHauteur;
		}
	}
	
	/**
	 * Constructeur avec paramètres 
	 * @param pX
	 * @param pY
	 * @throws ConstructeurException
	 */
	public Parallelogramme(int pX, int pY, double pBase, double pHauteur) throws ConstructeurException 
	{
		super(pX, pY);
		boolean ok = validerBase(pBase) && validerHauteur(pHauteur);
		if(ok)
		{
			base = pBase;
			hauteur = pHauteur;
		}
	}
	
	public int compareTo(Forme pParralelogramme) {
		return this.compareTo(pParralelogramme);
	}

	@Override
	public double perimetre() {
		return 2*(base + hauteur);
	}

	@Override
	public double aire() 
	{
		return base * hauteur;
	}

	@Override
	public int getNbrCote() {
		return NBR_COTE_PARALLELOGRAMME;
	}

	public double getBase() {
		return base;
	}
	/**
	 * Valider la valeur de la base du rectangle, doit être obligatoirement > 0
	 * @param pBase la valeur à valider
	 * @return boolean, vrai si la valeur est valide
	 */
	public static boolean validerBase(double pBase)
	{
		return pBase > 0;
	}
	/**
	 * Modifier la valeur de la base
	 * @param pBase la nouvelle valeur
	 * @return boolean, vrai si la valeur a été modifiée
	 */
	public boolean setBase(double pBase) 
	{
		boolean ok = validerBase(pBase);
		if(ok) base = pBase;
		return ok;
	}
	/**
	 * Obtenir la valeur de la hauteur
	 * @return double, la valeur de la hauteur
	 */
	public double getHauteur() {
		return hauteur;
	}
	/**
	 * Valider la valeur de la hauteur du rectangle, doit être obligatoirement > 0
	 * @param pHauteur la valeur à valider
	 * @return boolean, vrai si la valeur est valide 
	 */
	public static boolean validerHauteur(double pHauteur)
	{
		return pHauteur > 0;
	}
	
	public boolean setHauteur(double pHauteur) {
		boolean ok = validerHauteur(pHauteur);
		if (ok) hauteur = pHauteur;
		return ok;
	}

	public String toString() {
		return this.getClass().getName() + " Point X : " + getX() + " Point Y : " + getY() + " Base : " + this.getBase() + " Hauteur : "+ this.getHauteur() + " Aire : " + formatter.format(this.aire()) + " Perimetre : " +formatter.format(this.perimetre());
	}

}
