package SimpleArr;

import java.util.Arrays;
import java.util.Random;

public class SimpleArray // Atenção Não é seguro para Threads
{
	private final int[] array; // o array de inteiros compartilhados
	private int writeIndex = 0; // o indice do proximo elemento a ser gravado
	public final static Random generator = new Random();

	// constroi um SimpleArray de um dado tamanho
	public SimpleArray(int size) {
		array = new int[size];
	}// fim do construtor

	// adiciona um valor ao array compartilhado
	public synchronized void add(int value) {
		int position = writeIndex; // armazena o indice de gravação

		try { // coloca a Thread para dormir por 0-99 milissegundos
			Thread.sleep(generator.nextInt(500));
		} // fim do try
		catch (InterruptedException e) {
			e.printStackTrace();
		} // fim do catch
			// Coloca valor no elemento correto
		array[position] = value;
		System.out.printf("%s wrote %2d to element %d \n", Thread.currentThread().getName(), value, position);

		++writeIndex;// incrementa indice do elemento a ser gravado depois
		System.out.printf("Next write index: %d \n", writeIndex);
	}// fim do metodo add
		// utilizado para gerar saida do conteudo do array de inteiros
		// compartilhado

	public String toString() {
		return "\n Conteudos do  simplearray: \n" + Arrays.toString(array);
	}
}
