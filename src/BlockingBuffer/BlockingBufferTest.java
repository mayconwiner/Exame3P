package BlockingBuffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingBufferTest {

	public static void main(String[] args) {
		// cria novo pool de threads com duas threads
		ExecutorService aplicacao = Executors.newCachedThreadPool();

		// cria BlockingBuffer para armazenar ints
		Buffer sharedLocation = new BlockingBuffer();

		aplicacao.execute(new Producer(sharedLocation));
		aplicacao.execute(new Consumer(sharedLocation));

	}

}
