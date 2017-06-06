package Estudo01;

import javax.swing.JFrame;

public class ServerTest {

	public static void main(String[] args) {
		 Server aplicacao = new Server();
		 aplicacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 aplicacao.runServer();

	}

}
