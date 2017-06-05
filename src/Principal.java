class simulaProcesso extends Thread {
	int numero;

	public void run() {
		while (true) {
			System.out.println(numero);
			numero++;
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}

}

public class Principal {

	public static void main(String[] args) {
		new JanelaPrincipal();
		
//		simulaProcesso s1 =  new simulaProcesso();
//		s1.numero = 20;
//		s1.start();
//		


	}

}
