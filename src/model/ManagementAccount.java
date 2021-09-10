package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import exceptionsProyect.EmptyFieldsException;
import exceptionsProyect.NullEntry;
import exceptionsProyect.NumberAccountNotFoundException;
import exceptionsProyect.RetirementExceededException;
/**
 * Esta clase define la getion de los diferentes procedimientos que puede ralizar un usuario sobre la entidad bancaria.
 * @author Marcos Esteba Vargas Avella
 */

public class ManagementAccount {
	// atributos de clase
	private Account account;
	private ETypeAccount typeAccount;
	private ArrayList<Account> accounts;
	/**
	 * Constructor de clase que inicializa la Lista de cuentas.
	 * 
	 * 	 */
	public ManagementAccount() {
		accounts= new ArrayList<>();
		typeAccount=null;
	}
	/**
	 * Metodo que retorna un dato tipo double que representa el saldo de la cuenta mas el valor del parametro @param value.
	 * @param number El parametro numero representa el numero con el que se identifica una cuenta en el banco.
	 * @param value El parametro value se un valor de tipo  double que representa un monto a depositar en la cuenta.
	 * @return double que representa el nuevo saldo
	 * @throws NullEntry Excepcion que avisa cuando alguna entrada es de tipo null
	 * @throws EmptyFieldsException Excepcion que es lanzada cuando algun dato de entrada es vacio
	 * @throws NumberAccountNotFoundException Excepcion que es lanzada cuando @param number no existe o no fue inicializado.
	 */
	
	public double deposity(String number,double value) throws NullEntry,EmptyFieldsException,NumberAccountNotFoundException {
		if(number!=null) {
			findAccount(number).deposit(value);
			return findAccount(number).getResidue() ;
		}
		else {
			throw new NullEntry("Ha cancelado un proceso. Sera enviado al menu de inicio");
		}
		
		
	}
	/**
	 * Este metodo permite buscar y realizar una disminucion en el saldo de una cuenta que exista dentro de {@link #account}
	 * @param number El parametro numero representa un String que identifica la cuenta.Su proposito es servir para encontrar la cuenta con esa designacion
	 * @param value El varametro value es un dato de tipo double cuyo proposito es determinar el monto que va a ser retirado de una cuenta que exista dentro de {@link #account}
	 * @return Dato tipo booleando, true si la operacion fue realizada y false si no se puedo realizar la operacion. 
	 * @throws NullEntry Excepcion que avisa cuando alguna entrada es de tipo null
	 * @throws RetirementExceededException Excepcion que es lanzada cuando la cantidad que va a ser retirada de una cuenta excede su limite.
	 * @throws NumberAccountNotFoundException Excepcion que es lanzada cuando @param number no existe o no fue inicializado.
	 */
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
	/**
	 * Metodo que permite encontrar el obejto dentro de un arraylist {@link #accounts}
	 * @param number El parametro number es de tipo String y representa el identificador de la cuenta que se desea buscar.
	 * @return De tipo Objeto Account. Retorna el objeto en caso de exisitir , de lo contrario es null;
	 */
	public Account findAccount(String number) {
		return accounts.stream().filter(acountsAux-> acountsAux.getNumber().equals(number)).findFirst().map(acountsAux->acountsAux).orElse(null);
	}
	/**
	 * El metodo permite encotrar la posicion de un bojeto dentro de un arraylist {@link #accounts}
	 * @param number El parametro number es de tipo String y es el identificador de la cuenta que se esta buscando.
	 * @return entero que representa la posicion en donde se encuentra un objeto, Si no se encuentra retorn -1;
	 */
	
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
	/**
	 * Metodo que permite eliminar un objeto dentro de un arraylist en caso de que exista el objeto con el identificador @param number
	 * @param number El parametro number es dato tipo String que representa el identificador de la cuenta(Objeto Account)
	 * @return Dato tipo boolean, En caso de que exista el objeto se elimina y retorna true, en caso contrario lanza una excepcion.
	 * @throws NumberAccountNotFoundException Excepcion que es lanzada cuando @param number no existe o no fue inicializado.
	 * @throws NullEntry Excepcion que avisa cuando alguna entrada es de tipo null
	 */
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
	/**
	 * Metodo que permite generar un arreglo de tipo String con la informacion suministrada en los parametros.
	 * @param number El parametro number es de tipo String y representa el identificador de el numero de cuenta de la cuenta que va a ser creada. 
	 * @param initialResidue El parametro initialResidue es de tipo String y representa el valor inicial que tendra la cuenta a ser creada.
	 * @param overdraft_minResidue El parametro overdraft_minResidue es de tipo String y puede representar ya sea el valor de sobregiro maximo que puede tener la cuenta corriente
	 	o el valor minimo que puede tener una cuenta de ahorro
	 * @return Arreglo de String con la informacion suministrada en los aprametros.
	 */
	public String[] addInformationAcount(String number,String initialResidue,String overdraft_minResidue) {
		String[] data = {number,initialResidue,overdraft_minResidue};
		return data;
	}
	

	
	/**
	 * Este metodo permite añadir una cuenta de banco a {@link #accounts} por medio de la informacion suministrada desde un objeto tipo ENUM y un arreglo con informacion
	 de la cuenta
	 * @param typeAccount El parametro typeAccount es un dato de tipo ETypeAccount que representa el tipo de cuenta que se va a crear (CURRENT o DEPOSIT)
	 * @param data el parametro data es un arreglo de tipo String donde esta toda la informacion que va a ser agregada a la cuenta.
	 * @return Tipo de dato booleano, true si el objeto fue añadido correctamente, false si no fue añadido.
	 */
	
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
	/**
	 * Metodo que integra {@link #addAccount(ETypeAccount, String[])} y {@link #addInformationAcount(String, String, String)} para ser mas eficiente a la hora de agregar
	 un nuevo objeto e un arraylist {@link #accounts}.
	 * @param typeAccount El parametro typeAccoutn es un dato de tipo String que dice que tipo de cuenta esta siendo creadada(CORRIENTE o AHORRO)
	 * @param number El parametro number es de tipo String y representa el identificador de el numero de cuenta de la cuenta que va a ser creada. 
	 * @param initialResidue El parametro initialResidue es de tipo String y representa el valor inicial que tendra la cuenta a ser creada.
	 * @param overdraft_minResidue El parametro overdraft_minResidue es de tipo String y puede representar ya sea el valor de sobregiro maximo que puede tener la cuenta corriente
	 * @return Tipo de dato booleano, true si el objeto fue añadido correctamente, false si no fue añadido.
	 * @throws NumberFormatException Excepcion que es lanzada cuando los datos initialResidue y overdraft_minResidue no son de tipo double/float/int
	 * @throws NullEntry Excepcion que avisa cuando alguna entrada es de tipo null
	 * @throws EmptyFieldsException Excepcion que es lanzada cuando algun dato de entrada es vacio
	 * @throws NumberAccountNotFoundException Excepcion que es lanzada cuando @param number no existe o no fue inicializado.
	 */
	
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
	/**
	 * 
	 * @param number
	 * @param numberFrom
	 * @param numberTo
	 * @return
	 * @throws EmptyFieldsException
	 * @throws NullEntry
	 * @throws NumberAccountNotFoundException
	 */
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
