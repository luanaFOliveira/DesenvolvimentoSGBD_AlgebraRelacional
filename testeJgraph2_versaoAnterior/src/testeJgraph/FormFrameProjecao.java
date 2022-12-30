package testeJgraph;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class FormFrameProjecao extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JFrame frame;
	private float Ycoordenate;
	private JComboBox columnsComboBox;
	private List<String> columnsList;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnPronto;
	private ItemListener itemListener;
	private Boolean addBool = false;
	private String textResultado;
	private String textTermos;
	private JTextArea textArea_1;
	private JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameProjecao window = new FormFrameProjecao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormFrameProjecao() {
		initialize();

	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//System.out.println(tabela);

		
		String columns[] = {"nome","id","idade"};
		
		columnsList = new ArrayList<String>();
		
		//columnsList = setColumns();
		//columnsArrayList = p1.getColumns();
		//columnsArrayList.to
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
		
		String operacoes[] = {">","<","=","!=",""};
		
		JLabel lblTermos = new JLabel("Termos");
		
		textArea = new JTextArea();
		//textArea.setText(textResultado);
		
		JLabel lblResultado = new JLabel("Resultado");
		
		textArea_1 = new JTextArea();
		//textArea_1.setText(textTermos);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);

		
		btnRemove = new JButton("Remover");
		btnRemove.addActionListener(this);

		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

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
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPronto)
							.addGap(228))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(columnsComboBox, 0, 144, Short.MAX_VALUE)
									.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTermos, Alignment.LEADING)
								.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblResultado)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(71, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblResultado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(columnsComboBox)
							.addGap(67)
							.addComponent(lblTermos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnRemove)
								.addComponent(btnPronto))
							.addGap(5))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
					.addGap(242))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAdd){
			//quando tu clica em adicionar, ele adiciona nos termos e dai tira a coluna q foi usada
			if(columnsComboBox.getItemCount() > 0) {
				textTermos = columnsComboBox.getSelectedItem().toString();
				columnsComboBox.removeItemAt(columnsComboBox.getSelectedIndex());
				textArea_1.setText(textTermos);
			}

		}else if(e.getSource() == btnRemove) {
			System.out.println("botao remove");
		}else if(e.getSource() == btnPronto) {
			System.out.println("botao pronto");
		}
	}
}
