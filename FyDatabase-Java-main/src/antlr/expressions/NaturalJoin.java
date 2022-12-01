package antlr.expressions;

public class NaturalJoin extends Expression {
	
	private String relation1;
	private String relation2;
	
	public NaturalJoin(String relation1, String relation2) {
		this.relation2 = relation2; 
		this.relation1 = relation1;
	}
	
	public String getRelation1() {
		return relation1;
	}

	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}

	public String getRelation2() {
		return relation2;
	}

	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}

	@Override
	public String toString() {
		return "NaturalJoin;" + relation1 + ";" + relation2;
	}
}
