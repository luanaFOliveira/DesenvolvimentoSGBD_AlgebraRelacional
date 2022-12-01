package jgraph;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Form implements ActionListener{

	JFrame frame = new JFrame();
	JLabel label = new JLabel("HELLO");
	private JTextField txtSeleo;
	private JButton btnEnviar;
	public static String sent;
	
	public Form(String inf) {
		
		inf = "a";
		label.setBounds(0,0,100,50);
		label.setFont(new Font(null,Font.PLAIN, 25));
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(420,420);
		frame.getContentPane().setLayout(null);
		
		txtSeleo = new JTextField();
		txtSeleo.setBounds(150, 60, 100, 20);
		frame.getContentPane().add(txtSeleo);
		txtSeleo.setColumns(10);
		
		JLabel lblSeleo = new JLabel("Seleção");
		lblSeleo.setBounds(159, 12, 70, 15);
		frame.getContentPane().add(lblSeleo);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(150, 104, 86, 25);
		frame.getContentPane().add(btnEnviar);
		frame.setVisible(true);
	    btnEnviar.addActionListener(this);

	}
	
	public String getPredicate() {
		
		return txtSeleo.getText();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnEnviar){
			
			sent = txtSeleo.getText();
			frame.dispose();
			
		}
		
	}
}
