package model;

import exceptionsProyect.RetirementExceededException;

public interface ActionsAccount {
	/**
	 * Metodo que modifica la variable residue en objetos de tipo Account
	 * @param value Dato de tipo double que hace referencia al valor a adicionar a una cuenta.
	 */
	public void deposit(double value);
	/**
	 * Metodo que permite sustraer del valor de la variable residue en los objetos de tipo Account cierta cantidad, teniendo en cuenta el valor de overDraft y minResidue
	 * @param value Dato de tipo double que hace referencia al valor a sustraer de una cuenta.
	 * @return Dato tipo boleano, {@code false} si no puede ralizar la operacion , en caso contrario {@code true}
	 * Excepcion que es lanzada cuando la cantidad que va a ser retirada de una cuenta excede su limite.
	 * @throws RetirementExceededException  Excepcion que extiende de la interfaz Exception para cuando se quiera retirar una suma que exceda el limite de  de la cuenta para cuentas tipo {@link DepositAccount}.
	 */
	public boolean retirement(double value)throws RetirementExceededException;

}
