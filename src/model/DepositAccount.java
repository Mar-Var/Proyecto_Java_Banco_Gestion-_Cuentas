package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;

import exceptionsProyect.AccountHasNotCheckBookException;
import exceptionsProyect.RestrictiveCosntructorInitialValuesException;

/**
 * Clase que hereda de la la clase Account
 */

import exceptionsProyect.RetirementExceededException;

public class DepositAccount extends Account {
	private int minResidue;
	final static float INTEREST_RATE=0.05f;
	/**
	 * Constructor vacio de la clase DepositAccount
	 */
	public DepositAccount() {
		
	}
	/**
	 * Constructor de la clase DepositAccount
	 * @param number El parametro number representa el numero con el que se identifica una cuenta en el banco.
	 * @param residue El parametro initialResidue es de tipo String y representa el saldo de la cuenta de banco.
	 * @param dateCreation El parametro dateCreation2 representa un dato de tipo Calendar para guardar la fecha actual.
	 * @param minResidue El parametro minResidue es un dato de tipo entero que representa la cantidad minima que puede tener una cuenta de banco de tipo DepositAccount
	 */
	
	public DepositAccount(String number, double residue, Calendar dateCreation,int minResidue) {
		super(number, residue, dateCreation);
		this.number=number;
		this.residue=residue;
		this.dateCreation=dateCreation;
		this.minResidue=minResidue;
	}
	
	@Override
	public void deposit(double value) {
		// TODO Auto-generated method stub
		this.residue+=value;
	}

	@Override
	public boolean retirement(double value) throws RetirementExceededException {
		// TODO Auto-generated method stub
		if(this.residue-value>=minResidue) {
			this.residue-=value;
			return true;
		}else {
			throw new RetirementExceededException("Ha excedido la cantidad a retirar.Volvera al menu de inicio");
			
		}
	}

	public int getMinResidue() {
		return minResidue;
	}

	public void setMinResidue(int minResidue) {
		this.minResidue = minResidue;
	}
	
	public double calculateInterest() {
		return this.residue*INTEREST_RATE;
	}

	@Override
	public boolean addCheckBook(String numberFrom, String numberTo) {
		return false;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "Cuenta de ahorro \nNumero de cuenta=" + number + ", \nSaldo=" + residue + ", \nFecha de creacion=" + convertCalendarToFormat() +"\nInteres mensual= "+df.format(calculateInterest())+ "\n";
	}
	
	

}
