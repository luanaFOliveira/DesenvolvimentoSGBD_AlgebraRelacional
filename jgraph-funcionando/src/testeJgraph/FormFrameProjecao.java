package testeJgraph;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import sgbd.prototype.Column;
import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.query.unaryop.ProjectionOperator;
import sgbd.table.SimpleTable;
import sgbd.table.Table;

public class FormFrameProjecao extends JFrame implements ActionListener {

	private JPanel contentPane;

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
	
	private List<String> columnsResult;
	private Table tabela; 
	private ArrayList<Column> columnsArrayList;
	private Prototype p1;
	private Operator projecao;
	
	private ResultFrame resultFrame;

	
	/**
	 * Launch the application.
	 */
	public static void main(Prototype pr, Table tabela) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					FormFrameProjecao window = new FormFrameProjecao(pr,tabela);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormFrameProjecao(Prototype pr, Table tabela) {
		this.tabela =tabela;
		this.p1 = pr;
		initialize();

	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);

		frame.setBounds(100, 100, 500, 301);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		columnsList = new ArrayList<String>();
		
		columnsList = setColumns();
		
		
		columnsComboBox = new JComboBox(columnsList.toArray(new String[0]));
		
		Ycoordenate = columnsComboBox.getAlignmentY();
		
		
		JLabel lblColumns = new JLabel("Columns");
		
		JLabel lblTermos = new JLabel("Termos");
		
		textArea = new JTextArea();
		
		JLabel lblResultado = new JLabel("Resultado");
		
		textArea_1 = new JTextArea();
		
		columnsResult = new ArrayList<String>();
		
		
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
						.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPronto)
						.addComponent(btnRemove))
					.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTermos))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTermos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(columnsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(btnAdd)
							.addGap(17)
							.addComponent(btnRemove)
							.addGap(17)
							.addComponent(btnPronto))
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(532, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAdd){
			//quando tu clica em adicionar, ele adiciona nos termos e dai tira a coluna q foi usada
			if(columnsComboBox.getItemCount() > 0) {
				textTermos = textArea_1.getText() + "\n" +columnsComboBox.getSelectedItem().toString() ;
				columnsComboBox.removeItemAt(columnsComboBox.getSelectedIndex());
				textArea_1.setText(textTermos);
			}

		}else if(e.getSource() == btnRemove) {
			System.out.println("botao remove");
			
		}else if(e.getSource() == btnPronto) {
	        
	        columnsResult = Arrays.asList(textArea_1.getText().split("\n"));
	        showResult(columnsResult);		
	        
		}
	}
	
	
	public Operator getOperator() {
		return this.projecao;
	}
	
	public void showResult(List<String> columnsResult) {
		
        Operator executor = new ProjectionOperator(tabela, columnsResult);
        this.projecao = executor;
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
		            	textArea.append(row.getValue().getString(data.getKey()));
	            		break;
	            	default:
		            	textArea.append(row.getValue().getInt(data.getKey()).toString());
	            		break;
	            		
	            	}
	            	textArea.append(" | ");
	            }
	            textArea.append("\n");

	        }
	        
	    }
        resultFrame = new ResultFrame(textArea);
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
