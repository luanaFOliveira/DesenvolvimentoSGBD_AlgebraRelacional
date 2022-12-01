package antlr.expressions;

import java.util.List;

public class Project extends Expression {
	
	List<String> atributes;
	private String relation;
	
	public Project(List<String> atributes, String relation) {
		this.atributes = atributes;
		this.relation = relation;
	}

	public List<String> getAtributes() {
		return atributes;
	}

	public void setAtributes(List<String> atributes) {
		this.atributes = atributes;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	@Override
	public String toString() {
		return "Project;" + atributes.toString() + ";" + relation;
	}
}
