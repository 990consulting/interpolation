// Generated from /Users/dbborens/IdeaProjects/protoverse/src/Nanosyntax.g4 by ANTLR 4.5
package compiler.interpretation.nanosyntax;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NanosyntaxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NanosyntaxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(@NotNull NanosyntaxParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull NanosyntaxParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull NanosyntaxParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(@NotNull NanosyntaxParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull NanosyntaxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#singleton}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleton(@NotNull NanosyntaxParser.SingletonContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(@NotNull NanosyntaxParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#operand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand(@NotNull NanosyntaxParser.OperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(@NotNull NanosyntaxParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference(@NotNull NanosyntaxParser.ReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull NanosyntaxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#primitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive(@NotNull NanosyntaxParser.PrimitiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#stringPrimitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringPrimitive(@NotNull NanosyntaxParser.StringPrimitiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#floatPrimitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatPrimitive(@NotNull NanosyntaxParser.FloatPrimitiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanosyntaxParser#intPrimitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntPrimitive(@NotNull NanosyntaxParser.IntPrimitiveContext ctx);
}