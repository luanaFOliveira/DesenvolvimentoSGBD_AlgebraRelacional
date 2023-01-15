package testeJgraph;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;

import entities.Cell;
import entities.OperatorCell;
import sgbd.query.Operator;
import sgbd.query.unaryop.ProjectionOperator;

@SuppressWarnings("serial")
public class FormFrameProjecao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox<?> columnsComboBox;
	private List<String> columnsList;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnPronto;
	private String textTermos;
	private JTextArea textArea;
	
	private List<String> columnsResult;
	private Cell cell;
	private Cell parentCell;
	
	public static void main(Object cell, List<Cell> cells) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					FormFrameProjecao frame = new FormFrameProjecao(cell, cells);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormFrameProjecao(Object cell, List<Cell> cells) {
		
		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		parentCell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell).getParent())).findFirst().orElse(null);
		
		initializeGUI();

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeGUI() {
		
		setBounds(100, 100, 500, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		
		columnsList = parentCell.getColumns();
		
		columnsComboBox = new JComboBox(columnsList.toArray(new String[0]));
		
		JLabel lblColumns = new JLabel("Columns");
		
		JLabel lblTermos = new JLabel("Termos");
		
		textArea = new JTextArea();
		
		columnsResult = new ArrayList<String>();
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);

		
		btnRemove = new JButton("Remover");
		btnRemove.addActionListener(this);

		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPronto)
						.addComponent(btnRemove))
					.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTermos))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTermos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(btnAdd)
							.addGap(17)
							.addComponent(btnRemove)
							.addGap(17)
							.addComponent(btnPronto))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(532, Short.MAX_VALUE))
		);
		contentPane.setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAdd){
			//quando tu clica em adicionar, ele adiciona nos termos e dai tira a coluna q foi usada
			if(columnsComboBox.getItemCount() > 0) {
				textTermos = textArea.getText() + "\n" +columnsComboBox.getSelectedItem().toString() ;
				columnsComboBox.removeItemAt(columnsComboBox.getSelectedIndex());
				textArea.setText(textTermos);
			}

		}else if(e.getSource() == btnRemove) {
			System.out.println("botao remove");
			
		}else if(e.getSource() == btnPronto) {
	        
	        columnsResult = Arrays.asList(textArea.getText().split("\n"));
	        executeOperation(columnsResult);		
	        
		}
	}
	
	public void executeOperation(List<String> columnsResult) {
		
        Operator operator = new ProjectionOperator(parentCell.getTable(), columnsResult);

	    operator.open();
	    
	    ((OperatorCell) cell).setOperator(operator);
	    
        operator.close();
		
        dispose();
		
	}
	
}
