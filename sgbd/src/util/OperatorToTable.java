package util;

import java.util.ArrayList;
import java.util.List;

import entities.OperatorCell;
import sgbd.prototype.RowData;
import sgbd.query.Operator;
import sgbd.table.Table;

public class OperatorToTable {
	
	public static Table convert(OperatorCell cell, Operator operator) {
		
		List<List<String>> data = TableFormat.getRows(cell, operator);
		
		List<String> columnsName = new ArrayList<>();
		
		String tableName = operator.getSources().get(0).getTableName();
		
		String lastChar = Character.toString(tableName.charAt(tableName.length() - 1));
		if(FindType.isInt(lastChar)) {
			
			tableName.substring(0, tableName.length() - 2);
			tableName += Integer.parseInt(lastChar) + 1;
			
		}else {
			
			tableName += 0;
			
		}
		
		if(!data.isEmpty()) { 
			
			columnsName = data.get(0);
			data.remove(0);
			
		}
		
		List<RowData> rows = new ArrayList<>();
		
		for(List<String> i : data) {
			
			RowData rowData = new RowData();
			
			int k = 0;
			for(String j : i) {
				
				if(FindType.isInt(j)) {
					
					rowData.setInt(columnsName.get(k), Integer.parseInt(j));
					
				}else if(FindType.isFloat(j)) {
					
					rowData.setFloat(columnsName.get(k), Float.parseFloat(j));
					
				}else {
					
					rowData.setString(columnsName.get(k), j);
					
				}
				
				k++;
				
			}
			
			rows.add(rowData);
			
		}
	        
		ImportCSVFile.createTable(tableName, columnsName, rows);	
		
		return ImportCSVFile.getTable();
		
	}
	
}
