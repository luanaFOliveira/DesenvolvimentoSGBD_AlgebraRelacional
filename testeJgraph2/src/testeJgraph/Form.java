package testeJgraph;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Form extends JFrame implements ActionListener{

	JFrame frame = new JFrame();
	JLabel label = new JLabel("HELLO");
	private JTextField txtSeleo;
	private JButton btnEnviar;
	public static String sent;
	private int oldYcoord;
	private JButton btnNewText;
	private List<String> txtContent;

	
	public Form(String inf,String tipo) {
		
		inf = "a";
		label.setBounds(0,0,100,50);
		label.setFont(new Font(null,Font.PLAIN, 25));
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(420,420);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblSeleo = new JLabel("Projecao");
		lblSeleo.setBounds(150, 40, 70, 15);
		frame.getContentPane().add(lblSeleo);
		
		JLabel lblSelect = new JLabel(tipo);
		lblSelect.setBounds(80, 60, 70, 15);
		frame.getContentPane().add(lblSelect);
		
		txtSeleo = new JTextField();
		txtSeleo.setBounds(150, 60, 100, 20);
		frame.getContentPane().add(txtSeleo);
		txtSeleo.setColumns(10);
		
		//txtContent.add(txtSeleo.getText());
		oldYcoord = 60;
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(200, 300, 86, 25);
		frame.getContentPane().add(btnEnviar);
		frame.setVisible(true);
	    btnEnviar.addActionListener(this);
	    
	    btnNewText = new JButton("New field");
	    btnNewText.setBounds(100, 300, 100, 25);
		frame.getContentPane().add(btnNewText);
		frame.setVisible(true);
		btnNewText.addActionListener(this);
		
		String columns[] = {"nome","id","idade"};
		JComboBox comboBox = new JComboBox(columns);
		comboBox.setBounds(100, 200, 100, 34);
		
		JPanel panel = new JPanel();
		panel.add(comboBox);
		
		frame.add(panel);

	}
	
	public void createTextField() {
		
		oldYcoord = oldYcoord + 30;
		
		JLabel newlbl = new JLabel();
		newlbl.setBounds(100, oldYcoord, 70, 15);
		frame.getContentPane().add(newlbl);
		
		/*
		JTextField newtxt = new JTextField();
		newlbl.setBounds(80, oldYcoord, 70, 15);
		newlbl.setBackground(null);
		newlbl.setBorder(null);
		frame.getContentPane().add(newlbl);
		*/
		
		JTextField newtxt = new JTextField();
		newtxt.setBounds(150,oldYcoord , 100, 20);
		frame.getContentPane().add(newtxt);
		newtxt.setColumns(10);
		//txtContent.add(newtxt.getText());

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
		else if (e.getSource() == btnNewText) {
			createTextField();
		}
		
	}
}



/*
class Form extends JFrame implements ActionListener{
	
	static JFrame frame;
	static JComboBox dropDownColumns;
	static JLabel labelProj;
	
	public Form() {
		frame = new JFrame("Projecao");
		//frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(400,300);


		String columns[] = {"nome","id","idade"};
		
		dropDownColumns = new JComboBox(columns);
		
		labelProj = new JLabel("Selecione as colunas");
		
		JPanel panel = new JPanel();
		panel.add(labelProj);
		panel.add(dropDownColumns);
		
		frame.add(panel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
*/