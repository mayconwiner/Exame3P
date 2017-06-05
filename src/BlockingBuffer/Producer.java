package BlockingBuffer;

import java.util.Random;

public class Producer implements Runnable {
	private final static Random Gerador = new Random();
	private final Buffer sharedLocation; // referencia a objeto compartilhado

	// consntrutor
	public Producer(Buffer shared) {
		sharedLocation = shared;
	}

	// armazena os valores de 1 a 10 em sharedLocation
	public void run() {
		int soma = 0;
		for (int count = 1; count <= 10; count++) {
			try // tempo de 0 a 3 segundos , então coloca o valor em Buffer
			{
				Thread.sleep(Gerador.nextInt(3000));// tempo aleatorio
				sharedLocation.set(count); // configura o valor no buffer
				soma += count;// incrementa soma de valores
				System.out.printf("\t%2d\n", soma);
			} catch (InterruptedException execao) {
				execao.printStackTrace();
			}
		}
		System.out.println("Producer \n Terminating Producer");
	}
}
