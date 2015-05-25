package forme;
/**
 * Interface permettant d'uniformiser si une forme g�om�trique est d�pla�able (translation) ou pas
 */
public interface Deplasable 
{
	/**
	 * Permet de d�caler le point centre d'une forme g�om�trique de delta x et de delta y. Il faut faire attention de rester dans le plan positif entre [0, le plus grand des entiers]
	 * @param pDeltaX le d�placement sur l'axe des X
	 * @param pDeltaY le d�placement sur l'axe des Y
	 */
	public boolean deplacer (int pDeltaX, int pDeltaY);
}
