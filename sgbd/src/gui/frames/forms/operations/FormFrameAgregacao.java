package gui.frames.forms.operations;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import entities.Cell;
import entities.OperatorCell;
import entities.util.TableFormat;
import sgbd.query.Operator;
import sgbd.query.agregation.AgregationOperation;
import sgbd.query.agregation.AvgAgregation;
import sgbd.query.agregation.MaxAgregation;
import sgbd.query.agregation.MinAgregation;
import sgbd.query.unaryop.FilterColumnsOperator;
import sgbd.query.unaryop.GroupOperator;
import sgbd.table.Table;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class FormFrameAgregacao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnPronto;
	private JComboBox comboBox;
	private JButton btnAdd;
	private JButton btnRemove;
	private JTextArea textArea;
	private JComboBox columnsComboBox;
	private String textOperations;
	private List<String> operationsList;
	private List<String> columnsList;
	private List<String> columns;
	private List<String> columnsListCB;

	
	private List<AgregationOperation> agregationOperations;

	
	private Cell cell;
	private Cell parentCell;
	private Object jCell;
	private mxGraph graph;


	/**
	 * Launch the application.
	 */
	public static void main(Object cell, List<Cell> cells,mxGraph graph) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameAgregacao frame = new FormFrameAgregacao(cell,cells,graph);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public FormFrameAgregacao(Object cell, List<Cell> cells,mxGraph graph) {
		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		parentCell = this.cell.getParents().get(0);
		this.jCell = cell;
		this.graph = graph;
		initializeGUI();

	}
	
	private void initializeGUI() {
		setBounds(100, 100, 450, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		String operacoes[] = {"Agregar","Avg","Min","Max"};		
		
		comboBox = new JComboBox(operacoes);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Coluna");
		
		JLabel lblNewLabel_1 = new JLabel("Operacao");
		
		textArea = new JTextArea();
		
		columnsListCB = new ArrayList<String>();
		
		columnsListCB = parentCell.getColumnsName();
		
		columnsComboBox = new JComboBox(columnsListCB.toArray(new String[0]));
		
		columns = new ArrayList<String>();
		columnsList = new ArrayList<String>();
		operationsList = new ArrayList<String>();
		agregationOperations = new ArrayList<AgregationOperation>();
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(btnAdd)
						.addComponent(btnRemove))
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnPronto)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
						.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(btnAdd)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRemove))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnPronto)
					.addGap(16))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAdd){
			operationsList.add(comboBox.getSelectedItem().toString());
			columnsList.add(columnsComboBox.getSelectedItem().toString());
			textOperations = textArea.getText() + comboBox.getSelectedItem().toString() + " " + "(" +columnsComboBox.getSelectedItem().toString() + ")" + "\n" ;
			textArea.setText(textOperations);
			System.out.println("botao add");


		}else if(e.getSource() == btnRemove) {
			System.out.println("botao remove");
			
		}else if(e.getSource() == btnPronto) {
	        
	       
	        graph.getModel().setValue(jCell,columns.toString()+" G "+ textOperations);
	        executeOperation();		
	        
		}
		
		
	}
	
	public void executeOperation() {
		
		
		for(int i=0;i<operationsList.size();i++) {
			System.out.println("Dentro do loop for");
			
			if(operationsList.get(i).equals("Agregar")) {
				if(columnsList.size()>0) {
					columns.add(columnsList.get(i));
				}
			}else if(operationsList.get(i).equals("Avg")) {
				agregationOperations.add(new AvgAgregation(parentCell.getSourceTableName(columnsList.get(i)),columnsList.get(i)));
				
			}else if(operationsList.get(i).equals("Min")) {
				System.out.println("Dentro do Min");

				agregationOperations.add(new MinAgregation(parentCell.getSourceTableName(columnsList.get(i)),columnsList.get(i)));

			}else if(operationsList.get(i).equals("Max")) {
				agregationOperations.add(new MaxAgregation(parentCell.getSourceTableName(columnsList.get(i)),columnsList.get(i)));

			}
			
		}
		if(columns.size() == 0) {
			columns.add(columnsList.get(0));
		}
		
		Operator operator = parentCell.getData();

		System.out.println("Fora do loop antes do operator");
		System.out.println(agregationOperations);
		System.out.println(columnsList);
		System.out.println(operationsList);
		System.out.println(columns);

		
		//se nao tiver nenhuma coluna no group by  n da, mas acho q eh obrigatorio mesmo

		operator = new GroupOperator(operator,"BIOSTATS","Id",Arrays.asList(
                new AvgAgregation("BIOSTATS","Age"),
                new MaxAgregation("BIOSTATS","Id")
        ));		
		
		System.out.println("Antes do operator open");

	    operator.open();

		
		
		
		
		System.out.println("Fora do loop depois do operator");

	    ((OperatorCell) cell).setOperator(operator, TableFormat.getRows(operator, parentCell.getColumns()));
		((OperatorCell)cell).setColumns();
	    
        operator.close();
		
        dispose();
		
	}
}
