// Generated from Expr.g4 by ANTLR 4.7.2

	package antlr.tools;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ExprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExprParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExprParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link ExprParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelection(ExprParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link ExprParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelection(ExprParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link ExprParser#project}.
	 * @param ctx the parse tree
	 */
	void enterProjection(ExprParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link ExprParser#project}.
	 * @param ctx the parse tree
	 */
	void exitProjection(ExprParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NaturalJoin}
	 * labeled alternative in {@link ExprParser#natural}.
	 * @param ctx the parse tree
	 */
	void enterNaturalJoin(ExprParser.NaturalJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NaturalJoin}
	 * labeled alternative in {@link ExprParser#natural}.
	 * @param ctx the parse tree
	 */
	void exitNaturalJoin(ExprParser.NaturalJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CartesianProduct}
	 * labeled alternative in {@link ExprParser#cartesian}.
	 * @param ctx the parse tree
	 */
	void enterCartesianProduct(ExprParser.CartesianProductContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CartesianProduct}
	 * labeled alternative in {@link ExprParser#cartesian}.
	 * @param ctx the parse tree
	 */
	void exitCartesianProduct(ExprParser.CartesianProductContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple}
	 * labeled alternative in {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterSimple(ExprParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple}
	 * labeled alternative in {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitSimple(ExprParser.SimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nested}
	 * labeled alternative in {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterNested(ExprParser.NestedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nested}
	 * labeled alternative in {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitNested(ExprParser.NestedContext ctx);
}