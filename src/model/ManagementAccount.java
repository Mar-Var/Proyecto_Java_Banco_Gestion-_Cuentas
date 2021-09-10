package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import exceptionsProyect.EmptyFieldsException;
import exceptionsProyect.NullEntry;
import exceptionsProyect.NumberAccountNotFoundException;
import exceptionsProyect.RetirementExceededException;

public class ManagementAccount {
	
	private Account account;
	private ETypeAccount typeAccount;
	private ArrayList<Account> accounts;
	
	public ManagementAccount() {
		accounts= new ArrayList<>();
		typeAccount=null;
	}
	
	public double deposity(String number,double value) throws NullEntry,EmptyFieldsException,NumberAccountNotFoundException {
		if(number!=null) {
			findAccount(number).deposit(value);
			return findAccount(number).getResidue() ;
		}
		else {
			throw new NullEntry("Ha cancelado un proceso. Sera enviado al menu de inicio");
		}
		
		
	}
	public boolean retirement(String number,double value) throws NullEntry, RetirementExceededException,NumberAccountNotFoundException {
		if (number==null) {
			throw new NullEntry("Ha cancelado un proceso. Volvera al menu de inicio");
			
		}else {
			if(findAccount(number)==null) {
				throw new NumberAccountNotFoundException("La cuenta de la cual desea retirar no existe.\nVolvera al menu de inicio");
				
			}else {
				
				if(findAccount(number).retirement(value)) {
					return true;
				}
				else {
					throw new RetirementExceededException("Ha excedido el monto a retirar.\n Volvera al menu de inicio");
				}
				
			}

		}

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
	public boolean deleteAccount(String number) throws NumberAccountNotFoundException,NullEntry {
		if(number!=null) {
			if(findAccount(number)!=null) {
				accounts.remove(findPosition(number));
				return true;
			}else {
				throw new NumberAccountNotFoundException("El numero que busca no se encuentra registrado");
			}
		}else {
			throw new NullEntry("Ha cancelado la operacion.Volvera al menu de inicio");
			
		}
		

	}
	
	public String[] addInformationAcount(String number,String initialResidue,String overdraft_minResidue) {
		String[] data = {number,initialResidue,overdraft_minResidue};
		return data;
	}
	
	public boolean addAccount(String typeAccount,String number,String initialResidue,String overdraft_minResidue) throws NullEntry,EmptyFieldsException, NumberFormatException, NumberAccountNotFoundException {
		
		if(typeAccount!=null||number!=null||initialResidue!=null||overdraft_minResidue!=null) {
			if(typeAccount!=null||number!=null||initialResidue!=null||overdraft_minResidue!=null) {
				String[] data = {number,initialResidue,overdraft_minResidue};
				
				if(typeAccount=="CORRIENTE") {
					return addAccount(ETypeAccount.CURRENT,data);
				}
				else if(typeAccount=="AHORRO") {
					return addAccount(ETypeAccount.DEPOSIT,data);
				}else {
					throw new EmptyFieldsException("Campos vacios. Sera enviado al menu de inicio");
				}
			}
			
		}else {
			throw new NullEntry("Valores nulos encontrados. Sera enviado al menu de inicio");
		}
		
		
		return false;
	}
	
	public boolean addAccount(ETypeAccount typeAccount, String[] data) {
		
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

		return false;
	}
	
	public boolean addCheckBook(String number ,String numberFrom,String numberTo ) throws EmptyFieldsException,NullEntry,NumberAccountNotFoundException {
		
		if(number!=null&&numberFrom!=null&&numberTo!=null) {
			if(number!=""||numberFrom!=""||numberTo!="") {
				
				if(findAccount(number)!=null&&findAccount(numberFrom)!=null&&findAccount(numberTo)!=null) {
					if(findAccount(number).addCheckBook(numberFrom, numberTo)) {
						return true;
					}
				}else {
					throw new NumberAccountNotFoundException("Una cuenta de las anteriores cuentas no se encuentra dentro de nuestros registros.\nVolvera al menu.");
				}
				
			}else {
				throw new EmptyFieldsException("Ha dejado algun campo vacio.\nVolvera al menu de inicio");
			}
			

		}else {
			throw new NullEntry("Ha cancelado una operacion.\nVolvera al menu de inicio");
		}

		return false;
	}
	
	public String viewReport(String number) throws NumberAccountNotFoundException,NullEntry{
		if(number!=null) {
			if(findAccount(number)!=null) {
				return findAccount(number).toString();
			}else {
				throw new NumberAccountNotFoundException("La cuenta no fue encontrada.\n Volvera al menu de inicio");
				
			}
		}else {
			throw new NullEntry("ha cancelado un proceso.\nVolera al menu de inicio");
		}
	}
	
	
	public double getAverageAccounts() {
		double promedio=0;
		for (Account account : accounts) {
			promedio+=account.getResidue();
		}
		
		return promedio/accounts.size();
	}

}
