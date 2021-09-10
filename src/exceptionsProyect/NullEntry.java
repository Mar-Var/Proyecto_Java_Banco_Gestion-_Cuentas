package exceptionsProyect;

import javax.swing.JOptionPane;

import model.DepositAccount;

/**
 * Excepcion que extiende de la interfaz Exception para cuando se calcele un cuadro de dialogo de la libreria {@link JOptionPane}
 * @author Marcos Esteban Vargas Avella
 *
 */
public class NullEntry extends Exception {
	public NullEntry(String msg) {
		
		super(msg);
		
	}

}
