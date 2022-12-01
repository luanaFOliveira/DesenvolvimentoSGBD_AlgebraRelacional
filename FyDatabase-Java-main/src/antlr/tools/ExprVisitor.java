// Generated from Expr.g4 by ANTLR 4.7.2

	package antlr.tools;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ExprParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link ExprParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(ExprParser.SelectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link ExprParser#project}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection(ExprParser.ProjectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NaturalJoin}
	 * labeled alternative in {@link ExprParser#natural}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalJoin(ExprParser.NaturalJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CartesianProduct}
	 * labeled alternative in {@link ExprParser#cartesian}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCartesianProduct(ExprParser.CartesianProductContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple}
	 * labeled alternative in {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple(ExprParser.SimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nested}
	 * labeled alternative in {@link ExprParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNested(ExprParser.NestedContext ctx);
}