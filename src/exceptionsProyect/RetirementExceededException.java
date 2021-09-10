package exceptionsProyect;

import model.CurrentAccount;
import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando se quiera retirar una suma que exceda el limite de  de la cuenta para cuentas tipo {@link DepositAccount}.
 * @author Marcos Esteban Vargas Avella
 * 
 */
public class RetirementExceededException extends Exception {
	public RetirementExceededException(String msg) {
		super(msg);
	}

}
