package forme;
/**
 * Interface permettant d'uniformiser si une forme géométrique est déplaçable (translation) ou pas
 */
public interface Deplasable 
{
	/**
	 * Permet de décaler le point centre d'une forme géométrique de delta x et de delta y. Il faut faire attention de rester dans le plan positif entre [0, le plus grand des entiers]
	 * @param pDeltaX le déplacement sur l'axe des X
	 * @param pDeltaY le déplacement sur l'axe des Y
	 */
	public boolean deplacer (int pDeltaX, int pDeltaY);
}
