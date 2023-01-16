package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgbd.prototype.ComplexRowData;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.table.Table;
import util.TableFormat;

public class OperatorCell extends Cell{

	private Operator operator;
	private List<List<String>> content;
	
	public OperatorCell(String name, String style, Object cell) {
	
		super(name, style, cell);
		this.operator = null;
		
	}
	
	public void setOperator(Operator operator) {
		
		this.operator = operator;
		setContent();
		
	}
	
	private void setContent() {
		
	    this.content = TableFormat.getRows(operator);
	    
	}
	
	public List<List<String>> getContent() {

		return content;
	
	}
	
	public List<String> getColumns(){
		
		Operator aux = operator;
		aux.open();
		List<String> columns = new ArrayList<>();
		Tuple t = aux.next();
		for (Map.Entry<String, ComplexRowData> row: t){
             for(Map.Entry<String,byte[]> data:row.getValue()) {
            	 
            	 columns.add(data.getKey());
            	 
             }
		 }
		 aux.close();
		 return columns;
		
	}
	
	public Table getTable() {
		
		operator.getSources().forEach(x -> System.out.println(x));
		return operator.getSources().get(0);
		
	}
	
	public Operator getData() {
		
		return operator;
		
	}
	
}
