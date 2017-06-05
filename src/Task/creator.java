package Task;

public class creator {

	public static void main(String[] args) {
		System.out.println("Criando Threads");
		
		//cria cada Thread com um novo runnable selecionado
		Thread thread1 = new Thread(new PrintTask("task1"));
		Thread thread2 = new Thread(new PrintTask("task2"));
		Thread thread3 = new Thread(new PrintTask("task3"));
		Thread thread4 = new Thread(new PrintTask("task4"));
		Thread thread5 = new Thread(new PrintTask("task5"));
		
		
		System.out.println("Thread criada");
		
		
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		System.out.println("Thread startada");
		
	}

}
