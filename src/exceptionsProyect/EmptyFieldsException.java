package exceptionsProyect;

import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando como entrada de un input no haya ningun valor
 * @author Marcos Esteban Vargas Avella
 *
 */
public class EmptyFieldsException extends Exception {
	public EmptyFieldsException(String msg) {
		super();
	}

}
