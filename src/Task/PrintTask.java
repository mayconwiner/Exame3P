package Task;

import java.util.Random;

public class PrintTask implements Runnable {
	private final int sleepTime;// tempo de adormecimento aleatorio para a
								// thread
	private final String taskName; // nome da tarefa
	private final static Random generator = new Random();

	public PrintTask(String nome) {
		taskName = nome;
		sleepTime = generator.nextInt(500);
	}

	@Override
	public void run() {
		System.out.printf("%s going to sleep for %d milliseconds. \n", taskName, sleepTime);
		System.out.printf("%s done sleeping \n", taskName);
		while(true){
			System.out.printf("%s going to sleep for %d milliseconds. \n", taskName, sleepTime);
			try{
				Thread.sleep(1000);
			}catch(Exception e )
			{
				
			}
		}
	}

}
