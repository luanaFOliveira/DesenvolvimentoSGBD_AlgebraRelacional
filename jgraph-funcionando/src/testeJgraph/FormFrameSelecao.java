package testeJgraph;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import entities.Cell;
import sgbd.prototype.Column;
import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.query.unaryop.FilterOperator;
import sgbd.table.Table;


public class FormFrameSelecao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBoxColunas;
	private JComboBox comboBoxOp;
	private JTextArea textArea;
	
	private List<String> columnsList;
	private String textTermos;
	
	private List<String> columnsResult;
	private Table tabela; 
	private Prototype p1;
	private JButton btnPronto;
	private Operator selecao;
	private Cell cell;
	private Operator operator;
	
	private ResultFrame resultFrame;
	
	/**
	 * Launch the application.
	 */
	public static void main(Cell cell, Prototype p1, Table tabela, Operator operator) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameSelecao frame = new FormFrameSelecao(cell, p1, tabela, operator);
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
	public FormFrameSelecao(Cell cell, Prototype p1, Table tabela, Operator operator) {
		
		this.setVisible(true);
		
		this.operator = operator;
		this.tabela = tabela;
		this.p1 = p1;
		this.cell = cell;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		columnsList = setColumns();
		
		comboBoxColunas = new JComboBox(columnsList.toArray(new String[0]));
		
		String operacoes[] = {">","<","=","!=",">=", "<="};
		
		comboBoxOp = new JComboBox(operacoes);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Colunas");
		
		JLabel lblNewLabel_1 = new JLabel("Operacao");
		
		JLabel lblNewLabel_2 = new JLabel("Valor");
		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		columnsResult = new ArrayList<String>();
		
		textArea = new JTextArea();
		
		JLabel lblNewLabel_3 = new JLabel("Resultado");


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxColunas, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxOp, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPronto))
						.addComponent(lblNewLabel_2))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxColunas, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(comboBoxOp, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(btnPronto))
					.addGap(261))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnPronto) {
			//columnsResult = textArea_1.getText(); 
			
			System.out.println(operator + " R");
	        showResult(comboBoxColunas.getSelectedItem().toString(), comboBoxOp.getSelectedItem().toString(), textField.getText());
	        
		}
		//getInt(comboBoxColunas.getSelectedItem().toString()
		 //comboBoxOp.getSelectedItem().toString() textField.getText()
	}
	
	
	
	
	public void showResult(String columnName, String op, String value) {
		
		System.out.println("----------------------------------------------");
		
		Operator table = operator == null ? new TableScan(tabela, columnsList) : operator;
		
		Operator executor = new FilterOperator(table,(Tuple t)->{

			if(op.equals(">")) return t.getContent(tabela.getTableName()).getInt(columnName) > Integer.parseInt(value);
			if(op.equals(">=")) return t.getContent(tabela.getTableName()).getInt(columnName) >= Integer.parseInt(value);
			if(op.equals("<")) return t.getContent(tabela.getTableName()).getInt(columnName) < Integer.parseInt(value);
			if(op.equals("<=")) return t.getContent(tabela.getTableName()).getInt(columnName) <= Integer.parseInt(value);
			if(op.equals("=")) return t.getContent(tabela.getTableName()).getInt(columnName) == Integer.parseInt(value);
			return t.getContent(tabela.getTableName()).getInt(columnName) != Integer.parseInt(value);
			
		});
		this.selecao = executor;

	    executor.open();
	    	    
	    while(executor.hasNext()){
	        Tuple t = executor.next();
	        String str = "";
	        for (Map.Entry<String, ComplexRowData> row: t){
	            for(Map.Entry<String,byte[]> data:row.getValue()) {
	            	switch(data.getKey()){
	            		
	            	case "Name":
	            	case "Sex":
	            	case "Team":
	            	case "Position":	
	            		//str+=row.getValue().getString(data.getKey());
		            	textArea.append(row.getValue().getString(data.getKey()));
	            		break;
	            	default:
	            		//str+=row.getValue().getInt(data.getKey());
		            	textArea.append(row.getValue().getInt(data.getKey()).toString());
	            		break;
	            		
	            	}
	            	textArea.append(" | ");
	            	//str+=" | ";
	            }
	            textArea.append("\n");
	        }
	        System.out.println(str);
			//textArea.setText(str);
	    }
	    
	    cell.setOperator(executor);
	    
        resultFrame = new ResultFrame(textArea);
	    //Fecha operador
	    executor.close();
		
	}
	
	public Operator getOperator() {
		return this.selecao;
	}
	
	//acho q esses dois nao precisam
	
	public ArrayList<Column> getColumnsList(Prototype pr) {
		return pr.getColumns();
	}
	
	public void setAtributos(Prototype pr,Table tabela) {
		this.tabela = tabela;
		this.p1 = pr;
		
	}
	
	public List<String> setColumns() {
		
		ArrayList<Column> columnsArrayList = p1.getColumns();
		
		for(int i=0;i<columnsArrayList.size();i++) {
			
			Column newColumn = columnsArrayList.get(i);
			columnsList.add(newColumn.getName());
		}
		return columnsList;
		//columnsList.addAll(Arrays.asList(columns));
		
	}
}
