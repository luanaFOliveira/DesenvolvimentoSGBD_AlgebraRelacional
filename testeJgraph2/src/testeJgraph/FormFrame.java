package testeJgraph;

import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


import sgbd.prototype.Column;
import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.table.Table;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class FormFrame implements ActionListener{

	private JFrame frame;
	private float Ycoordenate;
	private JComboBox columnsComboBox;
	private List<String> columnsList;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnPronto;
	private ItemListener itemListener;
	private Boolean addBool = false;
	private String textResultado;
	private String textTermos;
	private JTextArea textArea_1;
	private JTextArea textArea;
	
	private String columnsResult;
	private Table tabela; 
	private ArrayList<Column> columnsArrayList;
	private Prototype p1;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrame window = new FormFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println(tabela);

		
		String columns[] = {"nome","id","idade"};
		
		columnsList = new ArrayList<String>();
		
		columnsList = setColumns();
		//columnsArrayList = p1.getColumns();
		//columnsArrayList.to
		//columnsList.addAll(Arrays.asList(columns));
		
		columnsComboBox = new JComboBox(columnsList.toArray(new String[0]));
		/*
		itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED ) {
                    String item = (String) itemEvent.getItem();
                    System.out.println("Item: " + item + " removed from list");
                    removeItem(item);
                }
            }
        };
        columnsComboBox.addItemListener(itemListener);
        */
		Ycoordenate = columnsComboBox.getAlignmentY();
		
		
		JLabel lblColumns = new JLabel("Columns");
		
		String operacoes[] = {">","<","=","!=",""};
		
		JLabel lblTermos = new JLabel("Termos");
		
		textArea = new JTextArea();
		//textArea.setText(textResultado);
		
		JLabel lblResultado = new JLabel("Resultado");
		
		textArea_1 = new JTextArea();
		//textArea_1.setText(textTermos);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);

		
		btnRemove = new JButton("Remover");
		btnRemove.addActionListener(this);

		
		btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPronto)
							.addGap(228))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(columnsComboBox, 0, 144, Short.MAX_VALUE)
									.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTermos, Alignment.LEADING)
								.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblResultado)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(71, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblResultado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(columnsComboBox)
							.addGap(67)
							.addComponent(lblTermos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnRemove)
								.addComponent(btnPronto))
							.addGap(5))
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
					.addGap(242))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAdd){
			//quando tu clica em adicionar, ele adiciona nos termos e dai tira a coluna q foi usada
			if(columnsComboBox.getItemCount() > 0) {
				textTermos = columnsComboBox.getSelectedItem().toString();
				columnsComboBox.removeItemAt(columnsComboBox.getSelectedIndex());
				textArea_1.setText(textTermos);
			}

		}else if(e.getSource() == btnRemove) {
			System.out.println("botao remove");
		}else if(e.getSource() == btnPronto) {
			columnsResult = textArea_1.getText(); 
	        Operator projecao = new ProjectionOperator(tabela,columnsResult);
			showResult(projecao);
		}
		
	}
	
	private void removeItem(String item) {
		columnsComboBox.removeItemListener(itemListener);
		columnsList.remove(item);
        columnsComboBox.removeItem(item);
        columnsComboBox.addItemListener(itemListener); 
    }
	/*
	public void createProjecaoOp() {
        //Operator projecao = new ProjectionOperator(tabela,columnsResult);

	}
*/	
	
	/*
	public void setTable(String tabela) {
		this.tabela = tabela;
	}
	*/
	/*
	public void printResults(String str) {
		textArea.setText(str);
	}
	*/
	
	public void showResult(Operator op) {
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
	        //System.out.println(str);
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


