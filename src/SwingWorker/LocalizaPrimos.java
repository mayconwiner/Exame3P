//git remote add origin https://github.com/mayconwiner/Exame3P.git
//git push -u origin master
package SwingWorker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class LocalizaPrimos extends JFrame {
	private final JTextField highstPrimeJTextField = new JTextField();
	private final JButton getPrimesJbutton = new JButton("Obter Primos");
	private final JTextArea displayPrimesJtextArea = new JTextArea();
	private final JButton cancelJButton = new JButton("Cancelar");
	private final JProgressBar progressJProgressBar = new JProgressBar();
	private final JLabel statusJlabel = new JLabel();
	private CalcularPrimos calculator;

	// construtor
	public LocalizaPrimos() {
		super("Procurando Primos com SwingWorker");

		// inicializa o painel para obter um numero do usuario
		JPanel northJPanel = new JPanel();
		northJPanel.add(new JLabel("Procura primos : "));
		highstPrimeJTextField.setColumns(5);
		northJPanel.add(highstPrimeJTextField);
		getPrimesJbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				progressJProgressBar.setValue(0);// redefine JProgressBar
				displayPrimesJtextArea.setText("");// limpa JTextArea
				statusJlabel.setText("");// limpa JLabel

				int number; // pesquisa primos para cima ate esse valor

				try {
					// obtem entrada de usuario
					number = Integer.parseInt(highstPrimeJTextField.getText());
				} catch (NumberFormatException ex) {
					statusJlabel.setText("Entre um inteiro");
					return;
				}
				// constroi um novo objeto LocalizaPrimos
				calculator = new LocalizaPrimos(number, displayPrimesJtextArea, statusJlabel, getPrimesJbutton,
						cancelJButton);
				// ouve alterações de propriedade na barra de progresso
				calculator.addPropertyChangeListener(new PropertyChangeListener() {

					public void propertyChange(PropertyChangeEvent e) {
						// se a propriedade alterada for progress,
						// atualiza a barra de progresso
						if (e.getPropertyName().equals("progress")) {
							int newValue = (Integer) e.getNewValue();
							progressJProgressBar.setValue(newValue);
						}
					}
				}
				);
				getPrimesJbutton.setEnabled(false);
				cancelJButton.setEnabled(true);
				
				calculator.execute(); //executa o objeto LocalizaPrimos
				
			}
		}
		);
		northJPanel.add(getPrimesJbutton);
		//adiciona um JList rolavel para exibir os resultados do calculo 
		displayPrimesJtextArea.setEnabled(false);
		add(new JScrollPane(displayPrimesJtextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		

	}

	public static void main(String[] args) {

	}

}
