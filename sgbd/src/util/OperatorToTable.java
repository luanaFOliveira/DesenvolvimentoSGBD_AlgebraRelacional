package util;

import java.util.List;
import java.util.Map;

import sgbd.prototype.ComplexRowData;
import sgbd.query.Operator;
import sgbd.query.Tuple;

public class OperatorToTable {
	
	public static void converter(Operator aux, List<Integer> indexes) {
		
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
		            		str+=row.getValue().getString(data.getKey());
		            		break;
		            	default:
		            		str+=row.getValue().getInt(data.getKey());
		            		break;

	            	}
	            	str+=" | ";
	            }
	            str+=("\n");
	        }
	        System.out.println(str);
	    }
		
	}
	
}
