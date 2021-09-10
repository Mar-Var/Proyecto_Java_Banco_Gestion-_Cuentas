package exceptionsProyect;
/**
 * La clase extiende de la interfaz {@link Exception} y se lanza cuando una cuenta ya existe dentro del sistema.
 * @author Marcos Esteban Vargas Avella
 *
 */

public class AccountAlreadyExistException extends Exception{
	public AccountAlreadyExistException(String msg) {
		super(msg);
	}
	

}
