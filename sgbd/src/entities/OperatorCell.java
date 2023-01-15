package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgbd.prototype.ComplexRowData;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.table.Table;

public class OperatorCell extends Cell{

	private Operator operator;
	private String content;
	
	public OperatorCell(String name, String style, Object cell) {
	
		super(name, style, cell);
		this.operator = null;
		
	}
	
	public void setOperator(Operator operator) {
		
		this.operator = operator;
		setContent();
		
	}
	
	private void setContent() {
		
		StringBuilder sb = new StringBuilder();
		Operator aux = this.operator;
		aux.open();
	    while(aux.hasNext()){
	        Tuple t = aux.next();
	        String str = "";
	        for (Map.Entry<String, ComplexRowData> row: t){
	            for(Map.Entry<String,byte[]> data:row.getValue()) {
	            	switch(data.getKey()){
	            		
		            	case "Name":
		            	case "Sex":
		            	case "Team":
		            	case "Position":	
		            		//str+=row.getValue().getString(data.getKey());
			            	sb.append(row.getValue().getString(data.getKey()));
		            		break;
		            	default:
		            		//str+=row.getValue().getInt(data.getKey());
			            	sb.append(row.getValue().getInt(data.getKey()).toString());
		            		break;

	            	}
	            	sb.append(" | ");
	            	//str+=" | ";
	            }
	            sb.append("\n");
	        }
	        System.out.println(str);
			//textArea.setText(str);
	    }
	    
	    this.content = sb.toString();
	    
	}
	
	private String getContent() {

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
	
	@Override
	public String toString() {
		
        return getContent();
		
	}

}
