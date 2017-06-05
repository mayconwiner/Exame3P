//git remote add origin https://github.com/mayconwiner/Exame3P.git
//git push -u origin master
package SwingWorker;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class CalcularPrimos extends SwingWorker<Integer, Integer> {
	private final Random gerador = new Random();
	private final JTextArea intermediateJTextArea; // exibe os primos
													// localizados;
	private final JButton getPrimoJButton;
	private final JButton cancelarJButton;
	private final JLabel statusJLabel;// exibe o status do calculo
	private final boolean[] primos; // array booleano para localizar primos
	// construtor

	public CalcularPrimos(int max, JTextArea intermediate, JLabel status, JButton getPrimes, JButton cancel) {
		intermediateJTextArea = intermediate;
		statusJLabel = status;
		getPrimoJButton = getPrimes;
		cancelarJButton = cancel;
		primos = new boolean[max];

		// inicializa todos os valores de array primos como verdadeiros
		for (int i = 0; i < max; i++)

			primos[i] = true;

	}

	public Integer doInBackground() throws Exception {
		int count = 0; // o numero de primos localizados
		// iniando no terceiro valor , circula pelo array e coloca falsso como o
		// valor de qualquer numero maior que for um mulpiplo
		for (int i = 2; i < primos.length; i++) {
			if (isCancelled())// se o calculo tiver sido cancelado
				return count;
			else {
				setProgress(100 * (i + 1) / primos.length);
				try {
					Thread.sleep(gerador.nextInt(5));
				} // fim do try
				catch (InterruptedException execao) {
					statusJLabel.setText("Thread Interrompida");
					return count;
				}
				if (primos[i])// i é primo
				{
					publish(i);// disponibiliza para exibição na lista de primos
					++count;

					for (int j = i + i; j < primos.length; j += 1)
						primos[j] = false; // i não é primo
				}
			}
		}
		return count;
	} // fim do método doInbackGround

	// exibe valores publicados na lista de primos
	protected void process(List<Integer> publishedVals) {
		for (int i = 0; i < publishedVals.size(); i++)
			intermediateJTextArea.append(publishedVals.get(i) + "\n");
	}// fim process

	// codigo para executar quando doInBackground se completa
	protected void done() {
		getPrimoJButton.setEnabled(true);// ativa o botao get primos
		cancelarJButton.setEnabled(false); // desativa o botão cancel

		int numPrimos;

		try {
			numPrimos = get(); // recupera o valor de retorno do doInBackground
		} catch (InterruptedException excecao) {
			statusJLabel.setText("Interrupted waiting results");
			return;
		} catch (ExecutionException excecao) {
			statusJLabel.setText("Error perfoming computation");
			return;
		} catch (CancellationException excecao) {
			statusJLabel.setText("Cancelado");
			return;
		}
		statusJLabel.setText("Encontrado " + numPrimos + " Primos ");

	}

}
