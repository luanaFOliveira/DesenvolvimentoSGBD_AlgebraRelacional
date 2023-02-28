package gui.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;

import entities.Cell;
import entities.OperatorCell;
import sgbd.query.Operator;
import sgbd.query.unaryop.FilterColumnsOperator;
import sgbd.query.unaryop.MaxOperator;
import sgbd.table.Table;
import util.TableFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class FormFrameMax extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnPronto;
	private JComboBox comboBox;
	private List<String> columnsList;
	private Cell cell;
	private Cell parentCell;
	
	/**
	 * Launch the application.
	 */
	public static void main(Object cell, List<Cell> cells) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameMax frame = new FormFrameMax(cell,cells);
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
	public FormFrameMax(Object cell, List<Cell> cells) {
		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		parentCell = this.cell.getParents().get(0);
		
		initializeGUI();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initializeGUI() {
		this.setVisible(true);
		setBounds(100, 100, 450, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		
		columnsList = parentCell.getColumnsName();
		
		comboBox = new JComboBox(columnsList.toArray(new String[0]));
		
				
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		
		JLabel lblNewLabel = new JLabel("Colunas");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(btnPronto)))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPronto))
					.addContainerGap(186, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnPronto) {
			executeOperation(comboBox.getSelectedItem().toString());
			
		}
		
		
	}
	

	public void executeOperation(String column) {
		
		System.out.println(parentCell.getData().toString());
		System.out.println(parentCell.getSourceTableName(column));
		System.out.println(column);

		Operator operator = new MaxOperator(parentCell.getData(),parentCell.getSourceTableName(column),column);
	    operator.open();
	    
	    ((OperatorCell) cell).setOperator(operator, TableFormat.getRows(operator));
	    
        operator.close();
        
        dispose();
		
	}
}
