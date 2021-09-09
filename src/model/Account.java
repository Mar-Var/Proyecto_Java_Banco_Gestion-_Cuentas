package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import model.CurrentAccount.CheckBook;

public abstract class Account implements ActionsAccount {

	protected String number;
	protected double residue;
	protected Calendar dateCreation;

	public Account() {
		
	}
	
	
	public Account(String number, double residue, Calendar dateCreation2) {
		this.number = number;
		this.residue = residue;
		this.dateCreation = dateCreation2;
	}
	
	public abstract boolean addCheckBook(String numberFrom, String numberTo);
	
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
