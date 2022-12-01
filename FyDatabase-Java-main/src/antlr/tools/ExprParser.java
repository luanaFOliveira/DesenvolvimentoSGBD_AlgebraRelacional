// Generated from Expr.g4 by ANTLR 4.7.2

	package antlr.tools;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, SELECT=6, PROJECT=7, NATURAL=8, 
		CARTESIAN=9, ATRIBUTE=10, PREDICATE=11, RELATION=12, WS=13;
	public static final int
		RULE_prog = 0, RULE_expression = 1, RULE_select = 2, RULE_project = 3, 
		RULE_natural = 4, RULE_cartesian = 5, RULE_relation = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "expression", "select", "project", "natural", "cartesian", "relation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "SELECT", "PROJECT", "NATURAL", "CARTESIAN", 
			"ATRIBUTE", "PREDICATE", "RELATION", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	 
		public ProgContext() { }
		public void copyFrom(ProgContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgramContext extends ProgContext {
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ProgramContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			_localctx = new ProgramContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(15); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(14);
				expression();
				}
				}
				setState(17); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SELECT) | (1L << PROJECT) | (1L << NATURAL) | (1L << CARTESIAN))) != 0) );
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public ProjectContext project() {
			return getRuleContext(ProjectContext.class,0);
		}
		public NaturalContext natural() {
			return getRuleContext(NaturalContext.class,0);
		}
		public CartesianContext cartesian() {
			return getRuleContext(CartesianContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				select();
				}
				break;
			case PROJECT:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				project();
				}
				break;
			case NATURAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(23);
				natural();
				}
				break;
			case CARTESIAN:
				enterOuterAlt(_localctx, 4);
				{
				setState(24);
				cartesian();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectContext extends ParserRuleContext {
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
	 
		public SelectContext() { }
		public void copyFrom(SelectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectionContext extends SelectContext {
		public TerminalNode SELECT() { return getToken(ExprParser.SELECT, 0); }
		public TerminalNode PREDICATE() { return getToken(ExprParser.PREDICATE, 0); }
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public SelectionContext(SelectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitSelection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitSelection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select);
		try {
			_localctx = new SelectionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(SELECT);
			setState(28);
			match(T__0);
			setState(29);
			match(PREDICATE);
			setState(30);
			match(T__1);
			setState(31);
			match(T__2);
			setState(32);
			relation();
			setState(33);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProjectContext extends ParserRuleContext {
		public ProjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_project; }
	 
		public ProjectContext() { }
		public void copyFrom(ProjectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProjectionContext extends ProjectContext {
		public TerminalNode PROJECT() { return getToken(ExprParser.PROJECT, 0); }
		public List<TerminalNode> ATRIBUTE() { return getTokens(ExprParser.ATRIBUTE); }
		public TerminalNode ATRIBUTE(int i) {
			return getToken(ExprParser.ATRIBUTE, i);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public ProjectionContext(ProjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterProjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitProjection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitProjection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectContext project() throws RecognitionException {
		ProjectContext _localctx = new ProjectContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_project);
		int _la;
		try {
			_localctx = new ProjectionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(PROJECT);
			setState(36);
			match(T__0);
			setState(37);
			match(ATRIBUTE);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(38);
				match(T__4);
				setState(39);
				match(ATRIBUTE);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			match(T__1);
			setState(46);
			match(T__2);
			setState(47);
			relation();
			setState(48);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaturalContext extends ParserRuleContext {
		public NaturalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_natural; }
	 
		public NaturalContext() { }
		public void copyFrom(NaturalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NaturalJoinContext extends NaturalContext {
		public TerminalNode NATURAL() { return getToken(ExprParser.NATURAL, 0); }
		public List<RelationContext> relation() {
			return getRuleContexts(RelationContext.class);
		}
		public RelationContext relation(int i) {
			return getRuleContext(RelationContext.class,i);
		}
		public NaturalJoinContext(NaturalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterNaturalJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitNaturalJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitNaturalJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NaturalContext natural() throws RecognitionException {
		NaturalContext _localctx = new NaturalContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_natural);
		try {
			_localctx = new NaturalJoinContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(NATURAL);
			setState(51);
			match(T__2);
			setState(52);
			relation();
			setState(53);
			match(T__4);
			setState(54);
			relation();
			setState(55);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CartesianContext extends ParserRuleContext {
		public CartesianContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cartesian; }
	 
		public CartesianContext() { }
		public void copyFrom(CartesianContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CartesianProductContext extends CartesianContext {
		public TerminalNode CARTESIAN() { return getToken(ExprParser.CARTESIAN, 0); }
		public List<RelationContext> relation() {
			return getRuleContexts(RelationContext.class);
		}
		public RelationContext relation(int i) {
			return getRuleContext(RelationContext.class,i);
		}
		public CartesianProductContext(CartesianContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCartesianProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCartesianProduct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCartesianProduct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CartesianContext cartesian() throws RecognitionException {
		CartesianContext _localctx = new CartesianContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cartesian);
		try {
			_localctx = new CartesianProductContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(CARTESIAN);
			setState(58);
			match(T__2);
			setState(59);
			relation();
			setState(60);
			match(T__4);
			setState(61);
			relation();
			setState(62);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationContext extends ParserRuleContext {
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
	 
		public RelationContext() { }
		public void copyFrom(RelationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SimpleContext extends RelationContext {
		public TerminalNode RELATION() { return getToken(ExprParser.RELATION, 0); }
		public SimpleContext(RelationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedContext extends RelationContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NestedContext(RelationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterNested(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitNested(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitNested(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_relation);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RELATION:
				_localctx = new SimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(RELATION);
				}
				break;
			case SELECT:
			case PROJECT:
			case NATURAL:
			case CARTESIAN:
				_localctx = new NestedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17G\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\6\2\22\n\2\r\2\16\2\23"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\5\3\34\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\7\5+\n\5\f\5\16\5.\13\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\5\bE\n\b\3\b"+
		"\2\2\t\2\4\6\b\n\f\16\2\2\2E\2\21\3\2\2\2\4\33\3\2\2\2\6\35\3\2\2\2\b"+
		"%\3\2\2\2\n\64\3\2\2\2\f;\3\2\2\2\16D\3\2\2\2\20\22\5\4\3\2\21\20\3\2"+
		"\2\2\22\23\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\25\3\2\2\2\25\26\7\2"+
		"\2\3\26\3\3\2\2\2\27\34\5\6\4\2\30\34\5\b\5\2\31\34\5\n\6\2\32\34\5\f"+
		"\7\2\33\27\3\2\2\2\33\30\3\2\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34\5\3\2"+
		"\2\2\35\36\7\b\2\2\36\37\7\3\2\2\37 \7\r\2\2 !\7\4\2\2!\"\7\5\2\2\"#\5"+
		"\16\b\2#$\7\6\2\2$\7\3\2\2\2%&\7\t\2\2&\'\7\3\2\2\',\7\f\2\2()\7\7\2\2"+
		")+\7\f\2\2*(\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2"+
		"/\60\7\4\2\2\60\61\7\5\2\2\61\62\5\16\b\2\62\63\7\6\2\2\63\t\3\2\2\2\64"+
		"\65\7\n\2\2\65\66\7\5\2\2\66\67\5\16\b\2\678\7\7\2\289\5\16\b\29:\7\6"+
		"\2\2:\13\3\2\2\2;<\7\13\2\2<=\7\5\2\2=>\5\16\b\2>?\7\7\2\2?@\5\16\b\2"+
		"@A\7\6\2\2A\r\3\2\2\2BE\7\16\2\2CE\5\4\3\2DB\3\2\2\2DC\3\2\2\2E\17\3\2"+
		"\2\2\6\23\33,D";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}