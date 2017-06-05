//git remote add origin https://github.com/mayconwiner/Exame3P.git
//git push -u origin master
//Criando um Buffer sincronizado com um ArrayBlockingQueue
package BlockingBuffer;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {
	private final ArrayBlockingQueue<Integer> buffer; //

	public BlockingBuffer() {
		buffer = new ArrayBlockingQueue<Integer>(1);
	}

	public void set(int valor) throws InterruptedException {
		buffer.put(valor);
		System.out.printf("%s %d \t %s %d \n", "Produtos inseridos", valor, "buffer : ", buffer.size());
	}

	public int get() throws InterruptedException {
		int leValor = buffer.take(); // remove o valor do buffer
		System.out.printf("%s %2d \t %s %d \n", "Consumidor comprou", leValor, "Buffer ocupado : ", buffer.size());
		return leValor;
	}

}
