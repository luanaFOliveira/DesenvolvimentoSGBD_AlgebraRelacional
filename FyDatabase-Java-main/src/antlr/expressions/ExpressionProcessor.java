package antlr.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {

	List<Expression> list;
	public Map<String, Integer> values;
	
	public ExpressionProcessor(List<Expression> list) {
		this.list = list;
		values = new HashMap<>();
	}
	
	public List<String> getEvaluationResults(){
		List<String> evaluations = new ArrayList<>();
		
		for(Expression e : list) {
			
			String result = getEvalResult(e);
			evaluations.add(result);
			
		}
		
		return evaluations;
		
	}
	
	private String getEvalResult(Expression e) {
		String result = "";
	
		if(e instanceof Select) {
		
			Select select = (Select) e;
			
			result = select.toString();
		
		}else if(e instanceof Project) {
			
			Project project = (Project) e;
			result = project.toString();
			
		}else if(e instanceof NaturalJoin) {
			
			NaturalJoin join = (NaturalJoin) e;
			result = join.toString();
			
		}else if(e instanceof CartesianProduct){
			
			CartesianProduct cartesian = (CartesianProduct) e;
			result = cartesian.toString();
			
		}else {
			result = "Não existe";
		}
			
		return result;
		
	}
	
}
