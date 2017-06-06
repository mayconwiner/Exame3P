package ChatClienteServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteThread extends Thread
{
	//Atributos da classe
	Socket cliente = null;
	DataInputStream entrada = null;
	DataOutputStream saida = null;
	Servidor copiaServidor = null;
	
	//Construtor da classe
	ClienteThread(Socket cliente, Servidor copia)
	{
		copiaServidor = copia;
		//Valida a ref para o socket
		//Cria os canais de entrada e saida
		this.cliente = cliente;
		try
		{
			this.entrada = new DataInputStream(cliente.getInputStream());
			this.saida = new DataOutputStream(cliente.getOutputStream());
			this.start();
		}
		catch(Exception e){}
	}
	
	//Envia uma mensagem para o cliente conectado
	public void enviaMensagem(String msg)
	{
		try
		{
			saida.writeUTF(msg);
			saida.flush();
		}
		catch(Exception e){}
	}
	
	//Define o comportamento da Thread
	public void run()
	{
		while(true)
		{
			try
			{
				String novaMensagem = entrada.readUTF();
				copiaServidor.areaTexto.append(novaMensagem);
				//String msg = boot.tratamensagem(novaMensagem);
				//saida.write(msg);
				for (ClienteThread cliente:copiaServidor.vetorClientes)
				{
					cliente.enviaMensagem(novaMensagem);
				}
			}
			catch(Exception e){}
		}
	}
}
