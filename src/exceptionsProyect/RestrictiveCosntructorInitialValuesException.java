package exceptionsProyect;

import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando se añadir una nueva cuenta tipo {@link DepositAccount} y el valor de saldo inicial es manor que el minimo
 	permitido por la cuenta
 * @author Marcos Esteban Vargas Avella
 *
 */
public class RestrictiveCosntructorInitialValuesException extends Exception {
	public RestrictiveCosntructorInitialValuesException(String msg) {
		super(msg);
	}

}
