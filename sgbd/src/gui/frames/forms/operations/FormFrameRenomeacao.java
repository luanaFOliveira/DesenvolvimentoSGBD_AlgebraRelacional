package gui.frames.forms.operations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import entities.Cell;
import entities.OperatorCell;
import entities.Column;
import sgbd.query.Operator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FormFrameRenomeacao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtOldName;
	private JTextField txtNewName;
	private JButton btnPronto;
	private String newName;
	
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
					FormFrameRenomeacao frame = new FormFrameRenomeacao(cell,cells,graph);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public FormFrameRenomeacao(Object cell, List<Cell> cells,mxGraph graph) {
		super("Renomeacao");

		this.setVisible(true);
		
		this.cell = cells.stream().filter(x -> x.getCell().equals(((mxCell)cell))).findFirst().orElse(null);
		parentCell = this.cell.getParents().get(0);
		this.jCell = cell;
		this.graph = graph;
		initializeGUI();

	}

	/**
	 * Create the frame.
	 */
	private void initializeGUI() {
		setBounds(100, 100, 450, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);
		
		txtOldName = new JTextField();
		txtOldName.setColumns(10);
		txtOldName.setText(parentCell.getName());
		
		txtNewName = new JTextField();
		txtNewName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome Tabela");
		
		JLabel lblNewLabel_1 = new JLabel("Novo Nome");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtOldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(btnPronto)))
					.addGap(42))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPronto)
						.addComponent(txtOldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnPronto) {
			newName = txtNewName.getText();
			//parentCell.setName(newName);
	        executeOperation();		

		}
		// TODO Auto-generated method stub
		
	}
	
	public void executeOperation() {
		Operator operator = parentCell.getData();

		operator.open();
		cell.setName(newName);

		List<Column> aux = cell.getColumns();
		for(Column column : aux) {
			String name = column.getName();
			//column.setName();
		}

		 ((OperatorCell)cell).setColumns(List.of(parentCell.getColumns()), operator.getContentInfo().values());
	     ((OperatorCell) cell).setOperator(operator);
        operator.close();

        dispose();
		
	}
}
