package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import exceptionsProyect.RetirementExceededException;

/**
 * Clase que hereda de {@link Account}
 * @author Marcos Esteban vargas Avella
 *
 */
public class CurrentAccount extends Account {
	private double overDraft;
	private ArrayList<CheckBook> checkBooks;

	/**
	 * Constructor vacio de la clase {@link CurrentAccount}
	 */
	public CurrentAccount() {
		
	}
	/**
	 * Constructor de la clase {@link CurrentAccount}
	 * @param number El parametro number representa el numero con el que se identifica una cuenta en el banco.
	 * @param residue El parametro initialResidue es de tipo String y representa el saldo de la cuenta de banco.
	 * @param dateCreation El parametro dateCreation2 representa un dato de tipo Calendar para guardar la fecha actual.
	 * @param overDraft el parametro {@link Override} es un dato tipo double que representa la cantidad maxima que puede adeudar al banco
	 */
	public CurrentAccount(String number, double residue, Calendar dateCreation, double overDraft) {
		super();
		this.number=number;
		this.residue=residue;
		this.dateCreation=dateCreation;
		this.overDraft=overDraft;
		checkBooks= new ArrayList<>();
	}
	
	/**
	 * 
	 *Clase dependiente de {@link CurrentAccount}
	 *
	 */
	
	class CheckBook {
			
			private String numberFrom;
			private String numberTo;
			/**
			 * Constructor de la clase {@link CheckBook}
			 * @param numberFrom El parametro numberFrom es un dato de tipo String que representa desde que cuenta se realizara una transaccion
			 * @param numberTo El parametro numberFrom es un dato de tipo String que representa hacia que cuenta se realizara una transaccion
			 */
			public CheckBook(String numberFrom, String numberTo) {
				this.numberFrom = numberFrom;
				this.numberTo = numberTo;
			}

			public String getNumberFrom() {
				return numberFrom;
			}

			public void setNumberFrom(String numberFrom) {
				this.numberFrom = numberFrom;
			}

			public String getNumberTo() {
				return numberTo;
			}

			public void setNumberTo(String numberTo) {
				this.numberTo = numberTo;
			}

			@Override
			public String toString() {
				return "Cheques : Desde=" + numberFrom + ", Hacia=" + numberTo + ".\n";
			}
			
			
		
	}
	public  boolean addCheckBook(String numberFrom,String numberTo) {
		checkBooks.add(new CheckBook(numberFrom, numberTo));
		return true;
	}
	
	@Override
	public void deposit(double value) {
		this.residue+=value;
	}


	@Override
	public boolean retirement(double value) throws RetirementExceededException {
		if(this.residue+overDraft-value>=0) {
			this.residue-=value;
			return true;
		}else {
			throw new RetirementExceededException("Ha excedido la cantidad a retirar.Volvera al menu de inicio");
		}
	}
	public double getOverDraft() {
		return overDraft;
	}
	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	public ArrayList<CheckBook> getCheckBooks() {
		return checkBooks;
	}
	public void setCheckBooks(ArrayList<CheckBook> checkBooks) {
		this.checkBooks = checkBooks;
	}
	@Override
	public String toString() {
		return "Cuenta Corriente: \nNo.Cuenta=" + number + ", \nSaldo=" + residue
				+ ", \nFecha creacion=" + convertCalendarToFormat() + "\nCheques=" + checkBooks +"\n";
	}


	
	
	
	


	


}

