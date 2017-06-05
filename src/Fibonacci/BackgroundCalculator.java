package Fibonacci;

import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class BackgroundCalculator extends SwingWorker<Long, Object> {
	private final int n; // numero de fibonacci a calcular
	private final JLabel resultado; // JLabel para exibir o resultado

	// construtor
	public BackgroundCalculator(int numero, JLabel label) {
		n = numero;
		resultado = label;
	}

	// codigo demorado a ser executado em uma thread trabalhadora
	public Long doInBackground() {
		long fib = fibonacci(n);
		return String.valueOf(fib);
	}

	// codigo a executar na thread de despacho de eventos quando doInBackground
	// retorna
	protected void done() {
		try {
			// obtem o resultado de doInBackground e exibe-o
			resultado.setText(get().toString());
		} catch (InterruptedException ex) {
			resultado.setText("Interrompido enquanto aguarda por resultados");
		} catch (ExecutionException ex) {
			resultado.setText("Error encontrado enquando calcula");
		}

	}

	public long fibonacci(long numero) {
		if (numero == 0 || numero == 1)
			return numero;
		else
			return fibonacci(numero = 1) + fibonacci(numero - 2);
	}

}
