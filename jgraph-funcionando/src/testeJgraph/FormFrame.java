package testeJgraph;

import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class FormFrame implements ActionListener{

	private JFrame frame;
	private JTextField txtValor;
	private float Ycoordenate;
	private JComboBox columnsComboBox;
	private List<String> columnsList;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private ItemListener itemListener;
	private Boolean addBool = false;
	private String textResultado;
	private String textTermos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrame window = new FormFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String columns[] = {"nome","id","idade"};
		
		columnsList = new ArrayList<String>();
		
		columnsList.addAll(Arrays.asList(columns));
		
		columnsComboBox = new JComboBox(columnsList.toArray(new String[0]));
		/*
		itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED ) {
                    String item = (String) itemEvent.getItem();
                    System.out.println("Item: " + item + " removed from list");
                    removeItem(item);
                }
            }
        };
        columnsComboBox.addItemListener(itemListener);
        */
		Ycoordenate = columnsComboBox.getAlignmentY();
		
		
		JLabel lblColumns = new JLabel("Columns");
		
		String operacoes[] = {">","<","=","!=","nenhum"};
		
		JComboBox operacaoComboBox = new JComboBox(operacoes);
		
		JLabel lblOperacao = new JLabel("Operacao");
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		
		JLabel lblTermos = new JLabel("Termos");
		
		JTextArea textArea = new JTextArea();
		textArea.setText(textResultado);
		
		JLabel lblResultado = new JLabel("Resultado");
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setText(textTermos);
		
		btnAdd = new JButton("Add");
		
		JButton btnRemove = new JButton("Remover");
		
		JButton btnPronto = new JButton("Pronto");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(columnsComboBox, 0, 144, Short.MAX_VALUE)
											.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
										.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(lblTermos))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(operacaoComboBox, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblOperacao))
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblValor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtValor)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblResultado)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnPronto)
										.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(71, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOperacao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(columnsComboBox, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
						.addComponent(operacaoComboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblResultado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTermos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAdd)
							.addComponent(btnRemove))
						.addComponent(btnPronto))
					.addGap(32))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAdd){
			//columnsComboBox.removeItemAt(columnsComboBox.getSelectedIndex());
			System.out.println("botao add");
			textTermos = "oi";
		}else if(e.getSource() == btnRemove) {
			System.out.println("botao remove");
		}
		
	}
	
	private void removeItem(String item) {
        //this step is required to keep from calling back to the listener with new selection when item is removed
		columnsComboBox.removeItemListener(itemListener);
		columnsList.remove(item);
        columnsComboBox.removeItem(item);
        columnsComboBox.addItemListener(itemListener); //okay now we what to know about changes again
    }
	
}


