package BlockingBuffer;

import java.util.Random;

public class Consumer implements Runnable {
	private final static Random gerador = new Random();
	private final Buffer sharedLocation; // referencia a objeto compartilhado

	// construtor
	public Consumer(Buffer shared) {
		sharedLocation = shared;

	}

	// Lê o valor de sharedLocation 10 vezes e soma os valores
	public void run() {
		int soma = 0;

		for (int contador = 1; contador <= 10; contador++) {
			try {
				Thread.sleep(gerador.nextInt(3000));
				soma += sharedLocation.get();
				System.out.printf("\t\t\t%2d\n", soma);
			} catch (InterruptedException excecao) {
				excecao.printStackTrace();
			}
		}
		System.out.printf("\n%s %d\n%s\n", "Consumer read valures ", soma, "Terminating consumer");
	}

}
