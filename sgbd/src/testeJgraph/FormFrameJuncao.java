package testeJgraph;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgbd.prototype.Column;
import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.query.unaryop.FilterOperator;
import sgbd.query.unaryop.ProjectionOperator;
import sgbd.query.binaryop.NestedLoopJoin;
import sgbd.table.Table;
import sgbd.util.ComparableFilter;



public class FormFrameJuncao extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox colunasComboBox;
	private JComboBox colunasComboBox_1;
	private JButton btnPronto;
	private List<String> columnsList_1;
	private List<String> columnsList_2;
	private Prototype p1;
	private Prototype p2;
	private Table tabela_1; 
	private Table tabela_2; 
	private Operator juncao;
	


	/**
	 * Launch the application.
	 */
	public static void main(Prototype p1,Prototype p2,Table tabela_1,Table tabela_2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFrameJuncao frame = new FormFrameJuncao(p1,p2,tabela_1,tabela_2);
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
	public FormFrameJuncao(Prototype p1,Prototype p2,Table tabela_1,Table tabela_2) {
		this.setVisible(true);
		this.p1 = p1;
		this.p2 = p2;
		this.tabela_1 = tabela_1;
		this.tabela_2 = tabela_2;
		
		setBounds(100, 100, 450, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		columnsList_1 = new ArrayList<String>();
		columnsList_1 = setColumns(p1);
		
		colunasComboBox = new JComboBox(columnsList_1.toArray(new String[0]));
		
		columnsList_2 = new ArrayList<String>();
		columnsList_2 = setColumns(p2);
		
		colunasComboBox_1 = new JComboBox(columnsList_2.toArray(new String[0]));
		
		
		
		JLabel lblNewLabel = new JLabel("Coluna");
		
		JLabel lblNewLabel_1 = new JLabel("Coluna");
		
		JLabel lblNewLabel_2 = new JLabel("=");
		
		JButton btnPronto = new JButton("Pronto");
		btnPronto.addActionListener(this);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(colunasComboBox, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(colunasComboBox_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnPronto))
						.addComponent(lblNewLabel_1))
					.addContainerGap(91, Short.MAX_VALUE))
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
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(colunasComboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPronto))
					.addContainerGap(198, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnPronto) {
			//columnsResult = textArea_1.getText(); 
			
	        showResult(colunasComboBox.getSelectedItem().toString(),colunasComboBox_1.getSelectedItem().toString());
	        
		}
	}
	

	public void showResult(String comp_1,String comp_2) {
		
		System.out.println("----------------------------------------------");
		Operator table_1 = new TableScan(tabela_1, columnsList_1);
		Operator table_2 = new TableScan(tabela_2, columnsList_2);
		
		Operator executor = new NestedLoopJoin(table_1,table_2, (t1, t2) -> {return true;});
		
		this.juncao = executor;

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
	            		str+=row.getValue().getString(data.getKey());
	            		break;
	            	default:
	            		str+=row.getValue().getInt(data.getKey());
	            		break;
	            		
	            	}
	            	str+=" | ";
	            }
	        }
	        System.out.println(str);
			//textArea.setText(str);
	    }
	    //Fecha operador
	    executor.close();
		
	}
	

	public List<String> setColumns(Prototype p) {
		
		ArrayList<Column> columnsArrayList = p.getColumns();
		List<String> columnsListTemp = new ArrayList<String>();
		
		for(int i=0;i<columnsArrayList.size();i++) {
			
			Column newColumn = columnsArrayList.get(i);
			columnsListTemp.add(newColumn.getName());
		}
		return columnsListTemp;
		
	}
	
	public Operator getOperator() {
		return this.juncao;
	}
	
}
