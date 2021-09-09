package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class CurrentAccount extends Account {
	private double overDraft;
	private ArrayList<CheckBook> checkBooks;

	
	public CurrentAccount() {
		
	}
	public CurrentAccount(String number, double residue, Calendar dateCreation, double overDraft) {
		super();
		this.number=number;
		this.residue=residue;
		this.dateCreation=dateCreation;
		this.overDraft=overDraft;
		checkBooks= new ArrayList<>();
	}
	
	class CheckBook {
			
			private String numberFrom;
			private String numberTo;
			
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
				return "CheckBook [numberFrom=" + numberFrom + ", numberTo=" + numberTo + "]";
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
	public boolean retirement(double value) {
		if(this.residue+overDraft-value>=0) {
			this.residue-=value;
			return true;
		}
		return false;
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
		return "CurrentAccount [overDraft=" + overDraft + ", checkBooks=" + checkBooks + "]";
	}
	
	
	
	


	


}

