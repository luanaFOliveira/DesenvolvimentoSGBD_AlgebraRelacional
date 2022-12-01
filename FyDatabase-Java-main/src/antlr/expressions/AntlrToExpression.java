package antlr.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import antlr.tools.ExprBaseVisitor;
import antlr.tools.ExprParser.CartesianProductContext;
import antlr.tools.ExprParser.NaturalJoinContext;
import antlr.tools.ExprParser.ProjectionContext;
import antlr.tools.ExprParser.SelectionContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression>{

	@Override
	public Expression visitSelection(SelectionContext ctx) {
		
		String relation = ctx.relation().getText();
		String predicate = ctx.PREDICATE().getText();
		return new Select(predicate, relation);
		
	}

	@Override
	public Expression visitProjection(ProjectionContext ctx) {
		
		List<String> atributes = new ArrayList<>();
		
			String relation = ctx.getChild(ctx.getChildCount() - 2).getText();
			
			int first = 0;
			
			int i;
			
			for(i = 1; !ctx.getChild(i).getText().equals("]"); i++) if(ctx.getChild(i).getText().equals("[")) first = i + 1;
			
			String atribute = "";
			
			for(int j = first; j < i; j++) atribute += ctx.getChild(j).getText();
						
			atributes = Arrays.asList(atribute.split(","));
			
			return new Project(atributes, relation);
		
	}

	@Override
	public Expression visitNaturalJoin(NaturalJoinContext ctx) {
		
		String relation1 = ctx.getChild(2).getText();
		String relation2 = ctx.getChild(4).getText();
		
		return new NaturalJoin(relation1, relation2);
	
	}

	@Override
	public Expression visitCartesianProduct(CartesianProductContext ctx) {

		List<String> relations = new ArrayList<>();

		for(int i = 1; i < ctx.getChildCount(); i++) {
		
			String relation = ctx.getChild(i).getText();
			if(relation.charAt(0) != ',' && relation.charAt(0) != '(' && relation.charAt(0) != ')')
			relations.add(relation);
		}
		return new CartesianProduct(relations);
		
	}
	
	
}
