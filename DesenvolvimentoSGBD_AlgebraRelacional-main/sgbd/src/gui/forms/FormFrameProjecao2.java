package gui.forms;

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
import sgbd.query.unaryop.FilterColumnsOperator;
import sgbd.table.Table;
import util.TableFormat;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormFrameProjecao2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox<?> columnsComboBox;
	private JComboBox tablesComboBox;
	private List<String> columnsList;
	private List<String> tablesList;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnPronto;
	private String textTermos;
	private JTextArea textArea;
	
	private List<String> columnsResult;
	private Cell cell;
	private Cell parentCell;
	
	public static void main(Object cell, List<Cell> cells,List<Cell> leafs) {
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

	public FormFrameProjecao2(Object cell, List<Cell> cells,List<Cell> leafs) {
		
		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		parentCell = this.cell.getParents().get(0);
		
		initializeGUI(leafs);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeGUI(List<Cell> leafs) {
		
		setBounds(100, 100, 605, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		tablesList = new ArrayList<String>();
		
		columnsList = parentCell.getColumnsName();
		
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
		
		JLabel lblSelectTables = new JLabel("Selecione a tabela");
		
		for(Cell cell : leafs) {
			tablesList.add(cell.getName());
			System.out.println(cell.getName());
		}
				
		tablesComboBox = new JComboBox(tablesList.toArray(new String[0]));

		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSelectTables)
							.addGap(18)
							.addComponent(tablesComboBox, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemove)
								.addComponent(btnPronto))
							.addGap(75)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTermos))))
					.addGap(85))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectTables)
						.addComponent(tablesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTermos))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(25)
							.addComponent(btnAdd)
							.addGap(16)
							.addComponent(btnRemove)
							.addGap(19)
							.addComponent(btnPronto))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(180, Short.MAX_VALUE))
		);
		contentPane.setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAdd){

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
		
		List<String> aux = parentCell.getColumnsName();
		aux.removeAll(columnsResult);
		
		Operator operator = parentCell.getData();
		
		for(Table table : parentCell.getData().getSources()) {
			
			operator = new FilterColumnsOperator(operator, table.getTableName(), aux);
		
		}
		
	    operator.open();
	    
	    ((OperatorCell) cell).setOperator(operator, TableFormat.getRows(operator));
	    
        operator.close();
		
        dispose();
		
	}
}
