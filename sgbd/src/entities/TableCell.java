package entities;

import java.util.List;
import java.util.Map;

import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.table.Table;
import util.Columns;

public class TableCell extends Cell{

	private Table table;
	private Prototype prototype;
	
	public TableCell(String name, String style, Object cell, Table table, Prototype prototype){
		
		super(name, style, cell);
		this.table = table;
		this.prototype = prototype;
		
	}

	public Table getTable() {
		return table;
	}

	public Prototype getPrototype() {
		return prototype;
	}
	
	public List<String> getColumns(){
		
		return Columns.getColumns(prototype.getColumns());
		
	}
	
	public Operator getData() {
		
		return new TableScan(table, getColumns());
	
	}
	
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		Operator aux = new TableScan(table, getColumns());
		aux.open();
	    while(aux.hasNext()){
	        Tuple t = aux.next();
	        for (Map.Entry<String, ComplexRowData> row: t){
	            for(Map.Entry<String,byte[]> data:row.getValue()) {
	            	switch(data.getKey()){
	            		
		            	case "Name":
		            	case "Sex":
		            	case "Team":
		            	case "Position":	

		            		sb.append(row.getValue().getString(data.getKey()));
		            		break;
		            	
		            	default:

		            		sb.append(row.getValue().getInt(data.getKey()).toString());
		            		break;

	            	}
	            	sb.append(" | ");

	            }
	            sb.append("\n");
	        }

	    }
		
		return sb.toString();
	
	}
	
}
