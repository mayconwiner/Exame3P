package Estudo01;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {
	private JTextField mensUsuario; // insere a mensagem do usuario
	private JTextArea displayArea; // exibie informa��o para o usuario
	private ObjectOutputStream output; // Gera o fluxo de saida para o cliente
	private ObjectInputStream input; // Gera fluxo de entrada a partir do //
										// cliente
	// socket de servidor ServerSocket
	private ServerSocket server; // conex�o com o servidor
	private Socket connection; // conex�o com o cliente
	private int counter = 1; // contador de conex�es
	// configura a GUI

	public Server() {
		super("Servidor do Maycon Douglas");
		mensUsuario = new JTextField(); // cria mensagem do usuario
		mensUsuario.setEditable(false);
		mensUsuario.addActionListener(new ActionListener() {
			// envia mensagem ao Cliente
			public void actionPerformed(ActionEvent e) {

				sendData(e.getActionCommand());
				mensUsuario.setText("");
			}// fim do metodo actionPerformed
		}// Fim da classe interna an�nima
		);// fim da chamada para addActionListener

		add(mensUsuario, BorderLayout.NORTH);
		displayArea = new JTextArea();
		setSize(300, 150);// tamanho da janela
		setVisible(true); // mostra a janela

	}

	public void runServer() {
		try // configura o servidor para receber conex�es ; processa as conex�es
		{
			server = new ServerSocket(12345, 100);// cria ServerSocket
			while (true) {
				try {
					waitForConnection(); // espera uma conex�o
					getStreams(); // obtem fluxos de entrada e saida
					processConnection(); // processa a conex�o
				} catch (EOFException eofException) {
					displayMessage("\n terminated conecction");
				} // fim do catch
				finally {
					closeConnection(); // fecha a conex�o
				}
			}

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// expera que a conexao chegue e ent�o exibe informa��es sobre a conex�o
	private void waitForConnection() throws IOException {
		displayMessage("Aguardando para Conex�o ");
		connection = server.accept(); // permite que o servidor aceite a conex�o
		displayMessage("Conex�o : " + counter + "Recebendo DE : " + connection.getInetAddress().getHostName());
	}// fim do metodo waitForConnection
		// Obtem fluxos para envair e receber dados

	private void getStreams() throws IOException {// configura o fluxo de saida // de objetos
	output = new ObjectOutputStream(connection.getOutputStream());
	output.flush();//esvazia buffer de saida para enviar as informa��es de cabe�alho
	//configura o fluxo de entrada para objetos 
	input = new ObjectInputStream(connection.getInputStream());
	displayMessage ("\n Got I/O streams \n");
	}

	// processa a conex�o com o cliente
	private void processConnection() throws IOException
	{
	String mensagem = "Conectado com sucesso !!!";
	sendData(mensagem);
	//ativa mensUsuario de moto que usuario do servidor posso envair mensagens 
	setTextFieldEditable(true);
	do // processa as mensagens enviadas peo cliente 
	{
		try // L� e exibe a mensagem 
		{
			mensagem = (String) input.readObject(); //L� uma novo mensagem 
			displayMessage(" \n " + mensagem);//exibe a mensagem 
		}catch(ClassNotFoundException classNotFoundException)
		{
			displayMessage("\n tipo de objeto n�o encontrado");
		}
	}while (!mensagem.equals("CLIENTE >>> TERMINATE"));
	}

	public void closeConnection() {
		displayMessage("\n Terminando a conex�o .... \n ");
		setTextFieldEditable(false);
		try {
			output.close();// fecha o fluxo de saida
			input.close();// fecha o fluxo de entrada
			connection.close(); // fecha a conex�o
		} // fim do try
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private void sendData(String mensagem) {
		try // envia o objeto ao cliente
		{
			output.writeObject("SERVER>>> " + mensagem);
			output.flush(); // esvazia a saida para o cliente
			displayMessage("\nSERVER>>> " + mensagem);
		} catch (IOException ioException) {
			displayArea.append("\nError de Escrito do Objeto");
		}
	}

	private void displayMessage(final String mensagemParaDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				displayArea.append(mensagemParaDisplay);// acrecenta a mensagem

			}

		});
	}

	private void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run()// configura a editabilidade do mensusuario
			{
				mensUsuario.setEditable(editable);

			}

		});
	}

}
