package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ManagementAccount {
	
	private Account account;
	private ETypeAccount typeAccount;
	private ArrayList<Account> accounts;
	
	public ManagementAccount() {
		accounts= new ArrayList<>();
		typeAccount=null;
	}
	
	public double deposity(String number,double value) {
		findAccount(number).deposit(value);
		return findAccount(number).getResidue() ;
		
	}
	public boolean retirement(String number,double value) {
		return findAccount(number).retirement(value);
	}
	public Account findAccount(String number) {
		
		return accounts.stream().filter(acountsAux-> acountsAux.getNumber().equals(number)).findFirst().map(acountsAux->acountsAux).orElse(null);
	}
	
	public int findPosition(String number) {	
		int position=0;
		for (Account account : accounts) {
			if(account.getNumber().equals(number)) {
				return position;
			}
			position++;
			
		}
		return -1 ;
	}
	public boolean deleteAccount(String number) {
		if(findAccount(number)!=null) {
			accounts.remove(findPosition(number));
			return true;
		}
		return false;
	}
	
	public String[] addInformationAcount(String number,String initialResidue,String overdraft_minResidue) {
		String[] data = {number,initialResidue,overdraft_minResidue};
		return data;
	}
	
	public boolean addAccount(String typeAccount,String number,String initialResidue,String overdraft_minResidue) throws Exception {
		String[] data = {number,initialResidue,overdraft_minResidue};
		if(typeAccount=="CORRIENTE") {
			return addAccount(ETypeAccount.CURRENT,data);
		}
		else if(typeAccount=="AHORRO") {
			return addAccount(ETypeAccount.DEPOSIT,data);
		}
		return false;
	}
	
	public boolean addAccount(ETypeAccount typeAccount, String[] data) throws Exception {
		
		try {
		if(findAccount(data[0])==null) {
			 
			 if(typeAccount==ETypeAccount.CURRENT) {
				 accounts.add( new CurrentAccount(data[0], Double.parseDouble(data[1]), Calendar.getInstance(),Double.parseDouble(data[2])));
				 return true;
					 }
			 else if(typeAccount==ETypeAccount.DEPOSIT) {
				 accounts.add(new DepositAccount(data[0], Double.parseDouble(data[1]), Calendar.getInstance(),Integer.parseInt(data[2])));
				 return true;
			 }
			 
					 
		 }
		}catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public boolean addCheckBook(String number ,String numberFrom,String numberTo ) {
		if(findAccount(number)!=null) {
			if(findAccount(number).addCheckBook(numberFrom, numberTo)) {
				return true;
			}
		}
		return false;
	}
	
	
	public double getAverageAccounts() {
		double promedio=0;
		for (Account account : accounts) {
			promedio+=account.getResidue();
		}
		
		return promedio/accounts.size();
	}

}
