package testeJgraph;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sgbd.prototype.Column;
import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.table.Table;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.query.unaryop.*;

import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	private ArrayList<Column> columnsArrayList;
	private Prototype p1;
	private JButton btnPronto;
	private Operator selecao;
	
	/**
	 * Launch the application.
	 */
	public static void main(Prototype pr,Table tabela) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameSelecao frame = new FormFrameSelecao(pr,tabela);
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
	public FormFrameSelecao(Prototype pr,Table tabela) {
		this.tabela = tabela;
		this.p1 = pr;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList = new ArrayList<String>();
		columnsList = setColumns();
		
		
		
		comboBoxColunas = new JComboBox(columnsList.toArray(new String[0]));
		
		String operacoes[] = {">","<","=","!=",""};
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textArea, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
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
										.addComponent(lblNewLabel_2)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3)))
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
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnPronto) {
			//columnsResult = textArea_1.getText(); 
	        Operator selectOp = new TableScan(tabela,columnsList);
	        selecao = new FilterOperator(selectOp,(Tuple t)->{
	            return t.getContent(tabela.toString()).getInt(comboBoxColunas.getSelectedItem().toString()) == 18;
	        });			
	        showResult(selecao);		
		}
		//getInt(comboBoxColunas.getSelectedItem().toString()
		 //comboBoxOp.getSelectedItem().toString() textField.getText()
	}
	
	
	public Operator getOperator() {
		return this.selecao;
	}
	
	public void showResult(Operator op) {
		System.out.println("show Results");
		
		Operator executor = op;	
	    
	    executor.open();
	    while(executor.hasNext()){
	        Tuple t = executor.next();
	        String str = "";
	        for (Map.Entry<String, ComplexRowData> row: t){
	            for(Map.Entry<String,byte[]> data:row.getValue()) {
	                switch(data.getKey()){
	                    case "idade":
	                    case "anoNascimento":
	                    case "id":
	                    case "idCidade":
	                    case "size":
	                        str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getInt(data.getKey());
	                        break;
	                    case "salario":
	                        str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getFloat(data.getKey());
	                        break;
	                    case "name":
	                    case "nome":
	                    default:
	                        str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getString(data.getKey());
	                        break;
	                }
	                str+=" | ";
	            }
	        }
	        System.out.println(str);
			textArea.setText(str);
	    }
	    //Fecha operador
	    executor.close();
		
	}
	
	public ArrayList<Column> getColumnsList(Prototype pr) {
		return pr.getColumns();
	}
	
	public void setAtributos(Prototype pr,Table tabela) {
		this.tabela = tabela;
		this.p1 = pr;
		
	}
	
	public List<String> setColumns() {
		
		columnsArrayList = p1.getColumns();
		
		for(int i=0;i<columnsArrayList.size();i++) {
			
			Column newColumn = columnsArrayList.get(i);
			columnsList.add(newColumn.getName());
		}
		return columnsList;
		//columnsList.addAll(Arrays.asList(columns));
		
	}
}