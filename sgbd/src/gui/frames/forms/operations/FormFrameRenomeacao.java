package gui.frames.forms.operations;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import entities.Cell;
import entities.OperatorCell;
import entities.util.TableFormat;
import sgbd.prototype.Column;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.unaryop.AsOperator;
import sgbd.query.unaryop.FilterColumnsOperator;
import sgbd.table.Table;
import sgbd.util.Conversor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

public class FormFrameRenomeacao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btnPronto;
	private String selectedColumn;
	private List<String> columnsList;

	
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

	public FormFrameRenomeacao(Object cell, List<Cell> cells, mxGraph graph) {
		
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		
		columnsList = parentCell.getColumnsName();
		
		comboBox = new JComboBox(columnsList.toArray(new String[0]));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Coluna");
		
		JLabel lblNewLabel_1 = new JLabel("as");
		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addGap(30)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(btnPronto)))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPronto))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnPronto) {
			selectedColumn = comboBox.getSelectedItem().toString();
			executeOperation(selectedColumn,textField.getText());
		}
	}
	
	public void executeOperation(String column,String formatedString) {
		
		//List<String> aux = parentCell.getColumnsName();
		//aux.removeAll(columnsResult);
		
		Operator operator = parentCell.getData();
		
		operator = new AsOperator(operator,(Conversor) new Conversor() {
          @Override
          public Column metaInfo(Tuple t) {
              return t.getContent(parentCell.getSourceTableName(column)).getMeta(column);
          }

          @Override
          public byte[] process(Tuple t) {
              String formated = t.getContent(parentCell.getSourceTableName(column)).getString(column)+ formatedString;
              return formated.getBytes(StandardCharsets.UTF_8);
          }

      }, formatedString);
		
	    operator.open();
	    
	    ((OperatorCell) cell).setOperator(operator);
		((OperatorCell)cell).setColumns(List.of(parentCell.getColumns()), operator.getContentInfo().values());
		cell.setName("ρ  "+ formatedString);
	    
        operator.close();
		
        dispose();
		
	}
}
