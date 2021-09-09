package vista;

import javax.swing.JOptionPane;

import model.ManagementAccount;

public class Run {

	public static void main(String[] args) throws Exception {
		ManagementAccount ma= new ManagementAccount();
		
		Object menuObject[] ={"Crear cuenta","Borra cuenta",
				"Depositar dinero","Retirar dinero","Añadir cheque","Ver reportes","Salir"};

		boolean remain=true;
		String numberAccount;
		
		while (remain) {
			
			String menuString= (String)JOptionPane.showInputDialog(null,"Bienvenido al menú de nuestro sistema bancario\n"
					+ "Ejija una opcion: ","Banco programación II",1,null,menuObject,null);
			 switch (menuString) {
			case "Crear cuenta":
				String menuObjectTypeAccount[]= {"CORRIENTE","AHORRO"};
				String menuTypeAccount=(String)JOptionPane.showInputDialog(null,"Seleccione el tipo de cuenta que desea crear\n","Tipo de cuenta",1,null,
						menuObjectTypeAccount,null);
				String accountNumber=(String)JOptionPane.showInputDialog(null,"Ingrese porfavor un numero de cuenta");
				String accountInitialResidue=(String)JOptionPane.showInputDialog(null,"Ingrese la cantidad con la que va a iniciar su cuenta");
				String overdraft_minResidue=(menuTypeAccount.equals("CORRIENTE"))?JOptionPane.showInputDialog(null,"Ingrese el sobregiro permitido a la cuenta"):
					JOptionPane.showInputDialog(null,"Ingrese el sobregiro permitido a la cuenta");
				if(ma.addAccount(menuTypeAccount, accountNumber, accountInitialResidue, overdraft_minResidue)) {
					JOptionPane.showMessageDialog(null, "El registro se ha realizado satisfactoriamente");
				}else {
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
				double valueDesposite=Double.parseDouble(JOptionPane.showInputDialog(null,"Inglese el el valor que desea depositar"));
				ma.deposity(numberAccount, valueDesposite);// Agregar cierta cosa.
				
				break;
			case "Retirar dinero":
				numberAccount = (String)JOptionPane.showInputDialog(null,"Inglese el numero de la cuenta de la que desea retirar el dinero");
				double valueRetire=Double.parseDouble(JOptionPane.showInputDialog(null,"Inglese elvalor que desea retirar"));
				if (ma.retirement(numberAccount, valueRetire)) {
					JOptionPane.showMessageDialog(null, "El retiro de su cuenta se ha realizado satisfactoriamente");
				}else {
					JOptionPane.showMessageDialog(null, "El retiro de su cuenta no se ha realizado. Verifique el numero de su cuenta\n"
							+ "O la cantidad que desea retirar");
				}
				
				break;
			case "Añadir cheque":
				
				
				break;
			case "Ver reportes":
				numberAccount = (String)JOptionPane.showInputDialog(null,"Inglese el numero de la cuenta de la que deseaver su informacion");
				JOptionPane.showMessageDialog(null, ma.getAverageAccounts());;
				
				break;
			case "Salir":
				
				break;

			default:
				JOptionPane.showMessageDialog(null,"La opcion elejida no es encontrada");
				break;
			}
		}

	}

}
