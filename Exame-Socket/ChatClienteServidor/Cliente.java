package ChatClienteServidor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente extends JFrame implements ActionListener, Runnable
{
	//Atributos do servidor
	JTextArea areaTexto = null;
	JTextField campoTexto = null;
	JButton botaoOk = null;
	Socket cliente = null;
	DataInputStream canalEntrada = null;
	DataOutputStream canalSaida = null;
	
	//Metodo utilizado para captura de eventos
	public void actionPerformed(ActionEvent evento)
	{
		try
		{
			canalSaida.writeUTF(campoTexto.getText());
		}
		catch(Exception e){}
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				String msg = canalEntrada.readUTF();
				areaTexto.append(msg + "\n");
			}catch(Exception e){}
		}
	}
	
	Cliente()
	{
		//Configuracoes da janela
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("cliente");
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
		try{
		cliente = new Socket("localhost", 3067);
		canalEntrada = new DataInputStream(cliente.getInputStream());
		canalSaida = new DataOutputStream(cliente.getOutputStream());
		new Thread(this).start();
		}
		catch(Exception e){}
	}
	
	public static void main(String...args)
	{
		new Cliente();
	}
}
