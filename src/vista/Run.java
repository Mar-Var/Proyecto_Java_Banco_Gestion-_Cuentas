package vista;

import javax.swing.JOptionPane;

import exceptionsProyect.EmptyFieldsException;
import exceptionsProyect.NullEntry;
import model.ManagementAccount;

/**
 * La clase Run permite ejecutar el programa.
 * @author Marcos Esteban Vargas Avella
 *
 */

public class Run {

	public static void main(String[] args) throws Exception {
		ManagementAccount ma= new ManagementAccount();
		
		Object menuObject[] ={"Crear cuenta","Borra cuenta",
				"Depositar dinero","Retirar dinero","Añadir cheque","Ver reportes","Salir"};

		boolean remain=true;
		String numberAccount,menuString = null;
		
		while (remain) {
			menuString= (String)JOptionPane.showInputDialog(null,"Bienvenido al menú de nuestro sistema bancario\n"
					+ "Ejija una opcion: ","Banco programación II",1,null,menuObject,null);
			try {
				if(menuString!=null) {
					switch (menuString) {
					case "Crear cuenta":
						String menuObjectTypeAccount[]= {"CORRIENTE","AHORRO"};
						
						String menuTypeAccount=(String)JOptionPane.showInputDialog(null,"Seleccione el tipo de cuenta que desea crear\n","Tipo de cuenta",1,null,
							   menuObjectTypeAccount,null);

						String accountNumber=(String)JOptionPane.showInputDialog(null,"Ingrese porfavor un numero de cuenta");
						
						String accountInitialResidue=(String)JOptionPane.showInputDialog(null,"Ingrese la cantidad con la que va a iniciar su cuenta");
						String overdraft_minResidue=null;
						try {
							if(menuTypeAccount==null) {
								throw new EmptyFieldsException("Tipo de cuenta invalido");
							}else {
								overdraft_minResidue=(menuTypeAccount.equals("CORRIENTE"))?JOptionPane.showInputDialog(null,"Ingrese el sobregiro permitido a la cuenta"):
									JOptionPane.showInputDialog(null,"Ingrese el residuo minimo que debe tener la cuenta");
							}	
						} catch (Exception e) {
							
					//		JOptionPane.showMessageDialog(null, e.getMessage());
						}
						
						try {
							
							if(ma.addAccount(menuTypeAccount, accountNumber, accountInitialResidue, overdraft_minResidue)) {
								JOptionPane.showMessageDialog(null, "El registro se ha realizado satisfactoriamente");
							}						
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							JOptionPane.showMessageDialog(null, "El registro no se ha realizado satisfactoriamente.\nAsegurese de haber ingresado correctamente los datos"
									+ "o puede que la cuenta ya este registrada");
						}

						

						break;
					case "Borra cuenta":
						numberAccount = (String)JOptionPane.showInputDialog(null,"Inglese el numero de la cuenta que desea eliminar");
						
						if(ma.deleteAccount(numberAccount)) {
							JOptionPane.showMessageDialog(null, "El borrado de su cuenta se ha realizado satisfactoriamente");
						}else {
							JOptionPane.showMessageDialog(null, "Hubo un error durante el borrado de la cuenta.\n"
									+ "Pues que la cuenta no exista");
						}
									
						break;
					case "Depositar dinero":
						numberAccount = (String)JOptionPane.showInputDialog(null,"Inglese el numero de la cuenta que a la que desea depositar");
						double valueDesposite=Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el el valor que desea depositar"));
						try {
							JOptionPane.showMessageDialog(null, "El deposito su cuenta se ha realizado satisfactoriamente\n"
									+ "Nuevo Saldo: "+ma.deposity(numberAccount, valueDesposite));
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						
						break;
					case "Retirar dinero":
						
						numberAccount = (String)JOptionPane.showInputDialog(null,"Inglese el numero de la cuenta de la que desea retirar el dinero");
						double valueRetire=Double.parseDouble(JOptionPane.showInputDialog(null,"Inglese elvalor que desea retirar"));
						
						try {
							ma.retirement(numberAccount, valueRetire);
							JOptionPane.showMessageDialog(null, "El retiro de su cuenta se ha realizado satisfactoriamente.\n"
									+ "Nuevo saldo: "+ma.findAccount(numberAccount).getResidue());
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}

						
						break;
					case "Añadir cheque":
						numberAccount = (String)JOptionPane.showInputDialog(null,"Ingrese el numero de la cuenta desde donde va a realizar el proceso.");
						String numberFrom = (String)JOptionPane.showInputDialog(null,"Ingrese el numero de su cuenta.");
						String numberTo = (String)JOptionPane.showInputDialog(null,"Ingrese el numero de cuenta del destinatario");
						try {					
							ma.addCheckBook(numberAccount, numberFrom, numberTo);
							JOptionPane.showMessageDialog(null, "Tramite realizado con exito!\n");
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}

						
						
						break;
					case "Ver reportes":
						numberAccount = (String)JOptionPane.showInputDialog(null,"Inglese el numero de la cuenta de la que deseaver su informacion");
						try {
							JOptionPane.showMessageDialog(null, ma.viewReport(numberAccount));;
							
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					
						
						break;
					case "Salir":
						remain=false;
						break;

					default:
						JOptionPane.showMessageDialog(null,"La opcion elejida no es encontrada");
						break;
					}

				}else {
					throw new NullEntry("Ha cancelado la operacion. Sera sacado del programa");
				}
				
			}catch (NullEntry e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage());
				break;
			}
			


		}

	}

}
