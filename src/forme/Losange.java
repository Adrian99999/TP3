package forme;

import java.awt.Point;
import exception.ConstructeurException;

public class Losange extends Parallelogramme 
{

	public Losange(Point pCentre, double pGrandeDiagonal, double pPetiteDiagonal) throws ConstructeurException 
	{
		super(pCentre, pGrandeDiagonal, pPetiteDiagonal);
	}
	
	public Losange(int pX, int pY, double pGrandeDiagonal, double pPetiteDiagonal) throws ConstructeurException
	{
		super (pX, pY, pGrandeDiagonal, pPetiteDiagonal);
	}
	public double aire()
	{
		return (this.getBase() * this.getHauteur()) / 2;
	}
/*	public String toString()
	{
		return this.getClass().getName() + " Point X : " + getX() + " Point Y : " + getY() + " Base : " + this.getBase() + " Hauteur : "+ this.getHauteur();  
	}*/
}
