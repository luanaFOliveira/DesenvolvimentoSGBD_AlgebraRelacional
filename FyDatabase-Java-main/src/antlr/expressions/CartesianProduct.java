package antlr.expressions;

import java.util.List;

public class CartesianProduct extends Expression {
	
	private List<String> relations;
	
	public CartesianProduct(List<String> relations) {
		this.relations = relations;
	}
	
	public List<String> getRelations() {
		return relations;
	}

	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	@Override
	public String toString() {
		return "PRODUTO CARTESIANO, relações: " + relations.toString();
	}
}
