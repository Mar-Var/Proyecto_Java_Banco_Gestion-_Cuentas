package model;

import exceptionsProyect.RetirementExceededException;

public interface ActionsAccount {
	public void deposit(double value);
	public boolean retirement(double value)throws RetirementExceededException;

}
