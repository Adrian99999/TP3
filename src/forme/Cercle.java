package forme;

import java.awt.Point;
import java.text.DecimalFormat;

import exception.ConstructeurException;

public class Cercle extends Forme
{
	private static final int NBR_COTE_CERCLE = 0;
	private double rayon = 0;
	DecimalFormat formatter = new DecimalFormat("#0.00");
	public Cercle (Point pCentre, double pRayon) throws ConstructeurException
	{
		this((int) pCentre.getX(), (int) pCentre.getY(), pRayon);
	}
	
	public Cercle (int pX, int pY, double pRayon) throws ConstructeurException
	{
		super (pX,pY);
		setRayon(pRayon);
		
	}
	/**
	 * Obtenir le rayon
	 * @return double, la valeur du rayon
	 */
	public double getRayon()
	{
		return rayon;
	}
	public static boolean validerRayon(double pRayon)
	{
		return pRayon > 0;
	}
	/**
	 * Mettre à jour la valeur du rayon
	 * @param pRayon le nouvelle valeur du rayon
	 * @return
	 */
	public boolean setRayon(double pRayon)
	{
		boolean ok = validerRayon(pRayon);
		if(ok) rayon = pRayon;
		return ok;
	}
	@Override
	public int compareTo(Forme o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimetre() {
		return rayon*2*Math.PI;
	}

	@Override
	public double aire() {
		return Math.pow(rayon, 2)*Math.PI;
	}

	@Override
	public int getNbrCote() {
		return NBR_COTE_CERCLE;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " Point X: " + getX()+ " Point Y: "+getY()+ " Rayon: " +getRayon() + " Aire : " +formatter.format( aire()) +" Perimetre : " + formatter.format(perimetre());
	}
}
