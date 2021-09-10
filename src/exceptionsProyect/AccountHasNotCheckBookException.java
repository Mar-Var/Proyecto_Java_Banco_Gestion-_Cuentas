package exceptionsProyect;

import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando se quiera añadir un cheque a una cuenta tipo {@link DepositAccount}
 * @author Marcos Esteban Vargas Avella
 *
 */

public class AccountHasNotCheckBookException extends Exception{

	public AccountHasNotCheckBookException(String msg) {
		super(msg);
	}

}
