package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import entities.Cell;
import entities.OperatorCell;
import gui.frames.forms.FormFrameColumnType;
import gui.frames.forms.FormFramePrimaryKey;

public class ImportGraph {

	private List<Cell> cells;
	private Object newCell;
	
	public ImportGraph() {
	
		JFileChooser fileUpload = new JFileChooser();
		//fileUpload.showOpenDialog(null);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		
		fileUpload.setFileFilter(filter);
		
		int res = fileUpload.showOpenDialog(null);
		
		if(res == JFileChooser.APPROVE_OPTION) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(fileUpload.getSelectedFile().getAbsolutePath()))){
				
				List<String> columnsName = new ArrayList<>();
				List<List<String>> lines = new ArrayList<>();
				
				//columnsName.addAll(Arrays.asList(br.readLine().replace("\"", "").replace(" ", "").split(",")));
				
				String line = br.readLine();
				while(line != null) {
					
					if(line.contains("tabela")) {
						
					}
					
					if(line.contains("Cell")) {
						lines.add(Arrays.asList(line.split(",")));
						line = br.readLine();
						addCells(Arrays.asList(line.split(",")));
					}
					
					lines.add(Arrays.asList(line.split(",")));
					
					line = br.readLine();
				}
				
				
			}catch(IOException e) {
				
				e.printStackTrace();
			
			}
		}
		
		
		
	}
	
	public void addCells(List<String> line) {
		
		//newCell = graph.insertVertex(null, null,line.get(0) , line.get(4), line.get(5), line.get(2), line.get(3), line.get(1));
		
		//cells.add(new OperatorCell(line.get(0), line.get(1), newCell, currentType, line.get(4), line.get(5), line.get(2), line.get(3)));
		
		//graph.insertEdge(line.get(6), null,"", line.get(6), line.get(7));

		
	}
	
	
		
		
}
