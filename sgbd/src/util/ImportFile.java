package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import entities.TableCell;
import gui.forms.FormFramePrimaryKey;

public class ImportFile {
	
	public static void importCSVFile(TableCell tableCell){
		
		JFileChooser fileUpload = new JFileChooser();
		//fileUpload.showOpenDialog(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		fileUpload.setFileFilter(filter);
		
		int res = fileUpload.showOpenDialog(null);
		
		if(res == JFileChooser.APPROVE_OPTION) {
			
			StringBuilder fileName = new StringBuilder();
			List<String> columnsName = new ArrayList<>();
			List<List<String>> lines = new ArrayList<>();
			
			readFile(fileUpload, fileName, columnsName, lines);
			TableCreator.createTable(tableCell, fileName.toString(), columnsName, lines);
			
		}
		
	}
	
	private static void readFile(JFileChooser fileUpload, StringBuilder fileName, List<String> columnsName, List<List<String>> lines){
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileUpload.getSelectedFile().getAbsolutePath()))){
			
			columnsName.addAll(Arrays.asList(br.readLine().replace("\"", "").replace(" ", "").split(",")));
			fileName.append(fileUpload.getSelectedFile().getName().toUpperCase().substring(0, fileUpload.getSelectedFile().getName().indexOf(".")));
			
			String line = br.readLine();
			while(line != null) {
				
				lines.add(Arrays.asList(line.split(",")));
				
				line = br.readLine();
			}
			
			List<List<String>> aux = new ArrayList<>();
			aux.add(columnsName);
			aux.addAll(lines);
			new FormFramePrimaryKey(aux);
			
		}catch(IOException e) {
			
			e.printStackTrace();
		
		}
		
	}
	
}