package SimpleArr;

//Parei na pagina 845

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArrayTeste {

	public static void main(String[] args) {
		// Constroi o objeto compartilhado
		SimpleArray sharedSimpleArray = new SimpleArray(6);

		// cria duas tarefas a serem gravadas no SimpleArray compartilhado
		ArrayWriter writer1 = new ArrayWriter(1, sharedSimpleArray);
		ArrayWriter writer2 = new ArrayWriter(11, sharedSimpleArray);

		// executa as tarefas com um ExecutorService
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(writer1);
		executor.execute(writer2);

		executor.shutdown();

		try {
			// espera 1 minuto por ambos os gravadores terminarem a execução
			boolean taskEnded = executor.awaitTermination(1, TimeUnit.MINUTES);
			if (taskEnded)
				System.out.println(sharedSimpleArray);// imprime o conteudo
			else
				System.out.println("Timed out while waiting for tasks to finish");
		} // fim do try
		catch (InterruptedException e) {
			System.out.println("Interrupted while waitinsg for tasks finish");
		}
	}

}
