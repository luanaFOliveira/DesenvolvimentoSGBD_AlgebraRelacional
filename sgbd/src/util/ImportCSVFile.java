package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sgbd.prototype.Column;
import sgbd.prototype.Prototype;
import sgbd.prototype.RowData;
import sgbd.table.SimpleTable;
import sgbd.table.Table;

public class ImportCSVFile {
	
	private static Prototype currentPrototype;
	private static Table currentTable;
	private static String currentFileName;
	
	public static void importCSVFile() {
		
		JFileChooser fileUpload = new JFileChooser();
		//fileUpload.showOpenDialog(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		fileUpload.setFileFilter(filter);
		
		int res = fileUpload.showOpenDialog(null);
		
		if(res == JFileChooser.APPROVE_OPTION) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(fileUpload.getSelectedFile().getAbsolutePath()))){
				
				String columnsName[] = br.readLine().replace("\"", "").replace(" ", "").split(",");
				currentFileName = fileUpload.getSelectedFile().getName().toUpperCase().substring(0, fileUpload.getSelectedFile().getName().indexOf("."));
				
				List<RowData> rows = new ArrayList<>();
				
				String line = br.readLine();
				while(line != null) {
					
					String columns[] = line.split(",");
					
					RowData data = new RowData();
					
					int j = 0;
					
					for(String i : columns) {
						
						if(FindType.isInt(i)) {
							
							data.setInt(columnsName[j], Integer.parseInt(i));
							
						}else if(FindType.isFloat(i)) {
							
							data.setFloat(columnsName[j], Float.parseFloat(i));
							
						}else {
							
							data.setString(columnsName[j], i);
							
						}
						
						j++;
						
					}
					
					rows.add(data);
					
					line = br.readLine();
				}
				
				List<String> columnsNameList1 = Arrays.asList(columnsName);
				List<String> columnsNameList2 = new ArrayList<>(columnsNameList1);
				
				System.out.println(rows.toString());
				
				createTable(currentFileName, columnsNameList2, rows);
				
			}catch(IOException e) {
				
				e.printStackTrace();
			
			}
			
		}
		
	}

	public static void createTable(String name, List<String> columnsName, List<RowData> rows) {
		currentPrototype = new Prototype();
		
		currentPrototype.addColumn(columnsName.get(0), 4, Column.PRIMARY_KEY);
		columnsName.remove(0);
		
		columnsName.forEach(x -> {currentPrototype.addColumn(x, 100, Column.NONE);});
		
	    currentTable = SimpleTable.openTable(name, currentPrototype);
	    currentTable.open();
	    rows.stream().forEach(x -> {currentTable.insert(x);});
	    
	    currentTable.open();
	    
	}
	
	public static Table getTable() {
		
		return currentTable;
		
	}
	
	public static Prototype getPrototype() {
		
		return currentPrototype;
		
	}
	
	public static String getFileName() {
		
		return currentFileName;
		
	}
	
}
