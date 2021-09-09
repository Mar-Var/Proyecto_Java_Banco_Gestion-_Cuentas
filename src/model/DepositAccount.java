package model;

import java.time.LocalDate;
import java.util.Calendar;

public class DepositAccount extends Account {
	private int minResidue;
	final static float INTEREST_RATE=0.05f;
	
	public DepositAccount() {
		
	}
	
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
	public boolean retirement(double value) {
		// TODO Auto-generated method stub
		if(this.residue-value>=minResidue) {
			this.residue-=value;
			return true;
		}
		return false;
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

}
