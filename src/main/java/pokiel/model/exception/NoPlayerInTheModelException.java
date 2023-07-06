package pokiel.model.exception;

public class NoPlayerInTheModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_MESSAGE = "Il n'y a aucun joueur dans le modèle !";

	public NoPlayerInTheModelException() {
		super(ERROR_MESSAGE);
	}
}
