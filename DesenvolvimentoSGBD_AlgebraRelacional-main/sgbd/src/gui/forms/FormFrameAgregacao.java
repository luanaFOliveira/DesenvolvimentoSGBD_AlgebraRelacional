package gui.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;

import entities.Cell;
import entities.OperatorCell;
import sgbd.query.Operator;
import sgbd.query.unaryop.GroupOperator;
import sgbd.query.unaryop.MaxOperator;
import sgbd.query.unaryop.MinOperator;
import util.TableFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class FormFrameAgregacao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox comboBoxColunas;
	private JComboBox comboBoxOp;
	private JButton btnPronto;
	private Cell cell;
	private Cell parentCell;
	private List<String> columnsList;

	

	/**
	 * Launch the application.
	 */
	public static void main(Object cell, List<Cell> cells) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameAgregacao frame = new FormFrameAgregacao(cell,cells);
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
	public FormFrameAgregacao(Object cell, List<Cell> cells) {
		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		parentCell = this.cell.getParents().get(0);
		
		initializeGUI();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initializeGUI() {
		
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		
		columnsList = parentCell.getColumnsName();
		
		comboBoxColunas = new JComboBox(columnsList.toArray(new String[0]));
		
		String operacoes[] = {"max","min"};
		
		comboBoxOp = new JComboBox(operacoes);
		
		JLabel lblNewLabel = new JLabel("Colunas");
		
		JLabel lblNewLabel_1 = new JLabel("Operacao");
		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(comboBoxColunas, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBoxOp, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(btnPronto))
						.addComponent(lblNewLabel_1))
					.addGap(36))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxColunas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxOp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPronto))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnPronto) {
			executeOperation(comboBoxColunas.getSelectedItem().toString(),comboBoxOp.getSelectedItem().toString());
			
		}
		
		
	}
	

	public void executeOperation(String column,String op) {
		
		Operator operator = parentCell.getData();
		
		if(op == "max") operator = new GroupOperator(new MaxOperator(parentCell.getData(),parentCell.getSourceTableName(column),column),parentCell.getSourceTableName(column),column);
		else if(op == "min") operator = new GroupOperator(new MinOperator(parentCell.getData(),parentCell.getSourceTableName(column),column),parentCell.getSourceTableName(column),column);
		
	    operator.open();
	    
	    ((OperatorCell) cell).setOperator(operator, TableFormat.getRows(operator));
	    
        operator.close();
        
        dispose();
		
	}
}
