package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import enums.OperationEnums;
import sgbd.prototype.ComplexRowData;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.table.Table;
import util.OperatorToTable;
import util.TableFormat;

public class OperatorCell extends Cell{

	private Operator operator;
	private List<List<String>> content;
	private OperationEnums type;
	private Table table;
	
	public OperatorCell(String name, String style, Object cell, OperationEnums type) {
	
		super(name, style, cell);
		this.operator = null;
		this.type = type;
		
	}
	
	public void setOperator(Operator operator) {
		
		this.operator = operator;
		this.table = OperatorToTable.convert(this, operator);
		setContent();
		
	}
	
	private void setContent() {
		
	    this.content = TableFormat.getRows(this, operator);
	    
	}
	
	public List<List<String>> getContent() {

		return content;
	
	}
	
	public List<String> getColumns(){
		
		Operator aux = operator;
		aux.open();
		List<String> columns = new ArrayList<>();
		
		if(aux.hasNext()) {
		
			Tuple t = aux.next();
			for (Map.Entry<String, ComplexRowData> row: t){
	             for(Map.Entry<String,byte[]> data:row.getValue()) {
	            	 
	            	 columns.add(data.getKey());
	            	 
	             }
			 }
		
		}
		aux.close();
		return columns;
		
	}
	
	public Table getTable() {
		
		return table;
		
	}
	
	public Operator getData() {
		
		return new TableScan(getTable(), getColumns());
		
	}
	
	public OperationEnums getType() {
		
		return type;
		
	}
	
}
