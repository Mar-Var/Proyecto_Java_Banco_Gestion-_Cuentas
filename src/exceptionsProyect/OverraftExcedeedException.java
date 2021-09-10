package exceptionsProyect;

import model.CurrentAccount;
import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando se quiera retirar una suma que exceda el limite de sobregiro de la cuenta para cuentas tipo {@link CurrentAccount}.
 * @author Marcos Esteban Vargas Avella
 *
 */

public class OverraftExcedeedException extends Exception {
	public OverraftExcedeedException(String msg) {
		super();
	}

}
