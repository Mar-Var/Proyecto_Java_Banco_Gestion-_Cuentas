package exceptionsProyect;

import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando no se encuentre ningun objeto con el identificador ingresado.
 * @author Marcos Esteban Vargas Avella
 *
 */

public class NumberAccountNotFoundException extends Exception{
	public NumberAccountNotFoundException(String msg) {
		super(msg);
	}

}
