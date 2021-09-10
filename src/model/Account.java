package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import exceptionsProyect.AccountHasNotCheckBookException;
import model.CurrentAccount.CheckBook;
/**
 * Clase tipo abstracto que implementa los metodos de la Interface ActionsAccount 
 */


public abstract class Account implements ActionsAccount {

	protected String number;
	protected double residue;
	protected Calendar dateCreation;
	/**
	 * Constructor de la clase Account vacio
	 */
	public Account() {
		
	}
	/**
	 * Constructor de la clase Account
	 * @param number El parametro number representa el numero con el que se identifica una cuenta en el banco.
	 * @param residue El parametro initialResidue es de tipo String y representa el saldo de la cuenta de banco.
	 * @param dateCreation2 El parametro dateCreation2 representa un dato de tipo Calendar para guardar la fecha actual.
	 */
	
	public Account(String number, double residue, Calendar dateCreation2) {
		this.number = number;
		this.residue = residue;
		this.dateCreation = dateCreation2;
	}
	/**
	 * Metodo abstracto para añadir una billetera a una cuenta
	 * @param numberFrom Parametro tipo String que define la cuenta desde donde se realiza una transaccion
	 * @param numberTo Parametro tipo String que define la cuenta a donde se va a realizar una transaccion.
	 * @return Dato tipo booleano, true si la llamada a la operacion  addCheckBook de {@link CurrentAccount} es true, de lo contrario es false o manda excepciones.
	 */
	
	public abstract boolean addCheckBook(String numberFrom, String numberTo);
	/**
	 * Metodo que le da formato "dd/MM/yyyy - HH:mm:ss" a el Calendar alojado en el objeto.
	 * @return Dato de tipo String en un formato "dd/MM/yyyy - HH:mm:ss"
	 */
	public String convertCalendarToFormat() {
		// En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.		
		return formatter.format(this.dateCreation.getTime());
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getResidue() {
		return residue;
	}
	
	
	
	
	
	
	
	

}
