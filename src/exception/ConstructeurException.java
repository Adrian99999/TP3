package exception;
/**
 * Classe d'exception utilisée lorsque un constructeur ne peut se terminer correctement.
 */
@SuppressWarnings("serial")
public class ConstructeurException extends RuntimeException
{

	public ConstructeurException()
	{
		super();
	}
	
	public ConstructeurException( String message)
	{
		super(message);
	}

}
