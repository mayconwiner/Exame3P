package Task;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TaskExecutor {

	public static void main(String[] args) {
		PrintTask task1 = new PrintTask("task1");
		PrintTask task2 = new PrintTask("task2");
		PrintTask task3 = new PrintTask("task3");

		System.out.println("Estartando executor");

		// cria executorservice para gerenciar threads
		ExecutorService threadExecutor = Executors.newCachedThreadPool();

		// inicia threads e coloca no estado executavel
		threadExecutor.execute(task1);
		threadExecutor.execute(task2);
		threadExecutor.execute(task3);

		threadExecutor.shutdown();
	}

}