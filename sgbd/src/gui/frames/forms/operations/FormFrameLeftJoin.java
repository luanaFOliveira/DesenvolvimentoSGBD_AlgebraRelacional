package gui.frames.forms.operations;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import entities.Cell;
import entities.OperatorCell;
import sgbd.query.Operator;
import sgbd.query.binaryop.joins.BlockNestedLoopJoin;
import sgbd.query.binaryop.joins.LeftNestedLoopJoin;

@SuppressWarnings("serial")
public class FormFrameLeftJoin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox<?> colunasComboBox;
	private JComboBox<?> colunasComboBox_1;
	private JButton btnPronto;
	private List<String> columnsList_1;
	private List<String> columnsList_2;
	
	private Cell cell;
	private Cell parentCell1;
	private Cell parentCell2;
	private Object jCell;
	private mxGraph graph;
	
	public static void main(Object cell, List<Cell> cells,mxGraph graph) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameLeftJoin frame = new FormFrameLeftJoin(cell, cells,graph);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormFrameLeftJoin(Object cell, List<Cell> cells, mxGraph graph) {
		super("Left Join");
		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		this.parentCell1 = this.cell.getParents().get(0);
		this.parentCell2 = this.cell.getParents().get(1);
		this.jCell = cell;
		this.graph = graph;
		initializeGUI();
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeGUI() {
		
		setBounds(100, 100, 450, 148);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList_1 = new ArrayList<String>();
		columnsList_1 = parentCell1.getColumnsName();
		columnsList_1.add("natural");
		
		colunasComboBox = new JComboBox(columnsList_1.toArray(new String[0]));
		
		columnsList_2 = new ArrayList<String>();
		columnsList_2 = parentCell2.getColumnsName();
		columnsList_2.add("natural");

		colunasComboBox_1 = new JComboBox(columnsList_2.toArray(new String[0]));
		
		JLabel lblNewLabel = new JLabel(parentCell1.getName());
		
		JLabel lblNewLabel_1 = new JLabel(parentCell2.getName());
		
		JLabel lblNewLabel_2 = new JLabel("=");
		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(colunasComboBox, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addContainerGap(169, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(colunasComboBox_1, 0, 158, Short.MAX_VALUE)
									.addGap(66))))))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(352, Short.MAX_VALUE)
					.addComponent(btnPronto)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(colunasComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(colunasComboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(btnPronto))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnPronto) {
			executeOperation(colunasComboBox.getSelectedItem().toString(),colunasComboBox_1.getSelectedItem().toString());
	        
		}
	}

	public void executeOperation(String item1, String item2) {
		
		Operator table_1 = parentCell1.getData();
		Operator table_2 = parentCell2.getData();
		
		Operator operator = new LeftNestedLoopJoin(table_1,table_2,(t1, t2) -> {
			if(item1.equals("natural") || item2.equals("natural")) return true;
			else return t1.getContent(parentCell1.getSourceTableName(item1)).getInt(item1) == t2.getContent(parentCell2.getSourceTableName(item2)).getInt(item2);
        });
		
		((OperatorCell)cell).setColumns(List.of(parentCell1.getColumns(), parentCell2.getColumns()), operator.getContentInfo().values());
		((OperatorCell) cell).setOperator(operator);
		cell.setName("|X|   " + colunasComboBox.getSelectedItem().toString()+" = "+colunasComboBox_1.getSelectedItem().toString());    
		if(item1.equals("natural") || item2.equals("natural")) {
			graph.getModel().setValue(jCell,parentCell1.getName() + " ⟕ " + parentCell2.getName());
		}
		else graph.getModel().setValue(jCell,"⟕   "+ colunasComboBox.getSelectedItem().toString()+" = "+colunasComboBox_1.getSelectedItem().toString());
	    
	    dispose();
		
	}
	
}
