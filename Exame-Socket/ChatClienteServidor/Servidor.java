package ChatClienteServidor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends JFrame implements ActionListener, Runnable
{
	//Atributos do servidor
	JTextArea areaTexto = null;
	JTextField campoTexto = null;
	JButton botaoOk = null;
	ServerSocket servidor = null;
	ArrayList<ClienteThread> vetorClientes = new ArrayList<ClienteThread>();
	
	//Metodo utilizado para captura de eventos
	public void actionPerformed(ActionEvent evento)
	{
		for (ClienteThread cliente:vetorClientes)
		{
			cliente.enviaMensagem(campoTexto.getText());
		}
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				Socket cliente = servidor.accept();
				ClienteThread clienteThread = new ClienteThread(cliente, this);
				vetorClientes.add(clienteThread);
			}catch(Exception e){}
		}
	}
	
	Servidor()
	{
		//Configuracoes da janela
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Servidor aguardando...");
		setLayout(new BorderLayout());
		
		//Criacao dos objetos visuais
		campoTexto = new JTextField(10);
		botaoOk = new JButton("OK");
		botaoOk.addActionListener(this);
		areaTexto = new JTextArea(10,10);
		JScrollPane borda = new JScrollPane(areaTexto);
		
		//Adiciona os componenentes a janela
		add(borda, BorderLayout.CENTER);
		
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.add(campoTexto);
		painel.add(botaoOk);
		add(painel, BorderLayout.SOUTH);
		
		setVisible(true);
		
		//Cria o objeto servidor com a porta
		//Valida os canais a partir do socket cliente
		try
		{
			servidor = new ServerSocket(3067);
			new Thread(this).start();
		}
		catch(Exception e){}
	}
	
	public static void main(String...args)
	{
		new Servidor();
//		new Cliente();
//		new Cliente();
//		new Cliente();
	}
}
