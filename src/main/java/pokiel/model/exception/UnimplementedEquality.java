package pokiel.model.exception;

public class UnimplementedEquality extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_MESSAGE = "Une égalité non implémenté à été trouvé !";
	
	public UnimplementedEquality() {
		super(ERROR_MESSAGE);
	}

}
