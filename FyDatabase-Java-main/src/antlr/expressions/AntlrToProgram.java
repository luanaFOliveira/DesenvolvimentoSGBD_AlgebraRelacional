package antlr.expressions;

import antlr.tools.ExprBaseVisitor;
import antlr.tools.ExprParser.ProgramContext;

public class AntlrToProgram extends ExprBaseVisitor<Program>{

	@Override
	public Program visitProgram(ProgramContext ctx) {

		Program prog = new Program();
		
		AntlrToExpression exprVisitor = new AntlrToExpression();
		for(int i = 0; i < ctx.getChildCount(); i++){
			
			if(i == ctx.getChildCount() - 1) {
				
			}else {
				prog.addExpression(exprVisitor.visit(ctx.getChild(i)));
			}
			
		}
		return prog;
	}
}
