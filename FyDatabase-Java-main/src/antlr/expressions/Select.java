package antlr.expressions;

public class Select extends Expression {
	
	private String predicate;
	private String relation;
	
	public Select(String predicate, String relation) {
		this.predicate = predicate;
		this.relation = relation;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	@Override
	public String toString() {
		return "Select;" + predicate + ";" + relation;
	}
}
