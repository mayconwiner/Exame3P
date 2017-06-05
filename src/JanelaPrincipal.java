import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class Monitor extends Thread {

	Monitor() {
		start();
	}

	public void run() {
		while (true) {
			if (JanelaPrincipal.temperatura > 450) {
				JOptionPane.showMessageDialog(null, "Alerta" + " : " + JanelaPrincipal.temperatura + " De Temperatura");
				System.out.println("Alerta Disparado");
			}
		}
	}
}

class Reator implements Runnable {
	private Random sorteia;

	public void run() {
		sorteia = new Random();
		while (true) {
			JanelaPrincipal.temperatura = 50 + sorteia.nextInt(450);
			JanelaPrincipal.vrRotulo.setText(JanelaPrincipal.temperatura + "");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}

public class JanelaPrincipal extends JFrame {
	static JLabel vrRotulo = null;
	static int temperatura = 0;
	JButton botao = null;

	JanelaPrincipal() {
		setSize(800, 600);
		setTitle("Monitoramento da uzina TermoNuclear de fukoshima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		vrRotulo = new JLabel("Aguardando..........");
		vrRotulo.setFont(new Font("Arial", Font.BOLD, 20));
		vrRotulo.setForeground(Color.red);

		botao = new JButton("OK");
		add(botao);
		add(vrRotulo);
		setVisible(true);

		Monitor monitor = new Monitor();

		Thread reator = new Thread(new Reator());
		reator.start();

	}

}
