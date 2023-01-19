package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entities.Cell;
import sgbd.prototype.ComplexRowData;
import sgbd.query.Operator;
import sgbd.query.Tuple;

public class TableFormat {

	public static List<List<String>> getRows(Cell cell, Operator operator) {
		
		Operator aux = operator;
		aux.open();
		
		List<List<String>> rows = new ArrayList<>();
		List<String> columnsName = new ArrayList<>();
		
		Tuple a = aux.hasNext() ? aux.next() : null;
		
		if(a != null) {
	        for (Map.Entry<String, ComplexRowData> line : a)
	    		for(Map.Entry<String,byte[]> data:line.getValue()) 
	    			columnsName.add(data.getKey());
	    	
	    	rows.add(columnsName);
		}
		
	    while(aux.hasNext()){
	    	
	        Tuple t = a == null ? aux.next() : a;

	        List<String> row = new ArrayList<>();
	        
	        for (Map.Entry<String, ComplexRowData> line : t){
	    		
	            for(Map.Entry<String,byte[]> data:line.getValue()) {
	            	
	            	if(data.getKey().contains("Name") || data.getKey().contains("Sex") || data.getKey().contains("Team") ||
	            			data.getKey().contains("Position") || data.getKey().contains("JobTitle") || data.getKey().contains("Dateofbirth") ||
	            			data.getKey().contains("Phone")){
	            		
			         
	            		row.add(line.getValue().getString(data.getKey()));
	            	
	            	}else {
			        
	            		row.add(line.getValue().getInt(data.getKey()).toString());

	            	}
	        
	            }
	    
	        }

	        rows.add(row);
	        a = null;
	        
	    }
	    
	    aux.close();
	    
	    return rows;
		
	}
	
}
