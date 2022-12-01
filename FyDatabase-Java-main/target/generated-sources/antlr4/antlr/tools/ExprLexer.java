// Generated from Expr.g4 by ANTLR 4.4

	package antlr.tools;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, SELECT=6, PROJECT=7, NATURAL=8, 
		CARTESIAN=9, ATRIBUTE=10, PREDICATE=11, RELATION=12, WS=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "SELECT", "PROJECT", "NATURAL", 
		"CARTESIAN", "ATRIBUTE", "PREDICATE", "RELATION", "WS", "A", "B", "C", 
		"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", 
		"R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\u00db\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\7\13\u008c\n\13\f\13\16\13\u008f\13\13\3\13\3\13\3\f\3\f\7\f"+
		"\u0095\n\f\f\f\16\f\u0098\13\f\3\r\3\r\7\r\u009c\n\r\f\r\16\r\u009f\13"+
		"\r\3\16\6\16\u00a2\n\16\r\16\16\16\u00a3\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3"+
		"\u008d\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\2\37\2!\2#\2%\2\'\2)\2+\2-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2?\2"+
		"A\2C\2E\2G\2I\2K\2M\2O\2\3\2!\3\2aa\4\2C\\c|\6\2\62;@@C\\c|\6\2\62;C\\"+
		"aac|\4\2\13\f\17\17\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4"+
		"\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQq"+
		"q\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2"+
		"ZZzz\4\2[[{{\4\2\\\\||\u00c4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3Q\3\2\2\2\5S\3\2"+
		"\2\2\7U\3\2\2\2\tW\3\2\2\2\13Y\3\2\2\2\r[\3\2\2\2\17b\3\2\2\2\21j\3\2"+
		"\2\2\23w\3\2\2\2\25\u0089\3\2\2\2\27\u0092\3\2\2\2\31\u0099\3\2\2\2\33"+
		"\u00a1\3\2\2\2\35\u00a7\3\2\2\2\37\u00a9\3\2\2\2!\u00ab\3\2\2\2#\u00ad"+
		"\3\2\2\2%\u00af\3\2\2\2\'\u00b1\3\2\2\2)\u00b3\3\2\2\2+\u00b5\3\2\2\2"+
		"-\u00b7\3\2\2\2/\u00b9\3\2\2\2\61\u00bb\3\2\2\2\63\u00bd\3\2\2\2\65\u00bf"+
		"\3\2\2\2\67\u00c1\3\2\2\29\u00c3\3\2\2\2;\u00c5\3\2\2\2=\u00c7\3\2\2\2"+
		"?\u00c9\3\2\2\2A\u00cb\3\2\2\2C\u00cd\3\2\2\2E\u00cf\3\2\2\2G\u00d1\3"+
		"\2\2\2I\u00d3\3\2\2\2K\u00d5\3\2\2\2M\u00d7\3\2\2\2O\u00d9\3\2\2\2QR\7"+
		"*\2\2R\4\3\2\2\2ST\7+\2\2T\6\3\2\2\2UV\7]\2\2V\b\3\2\2\2WX\7.\2\2X\n\3"+
		"\2\2\2YZ\7_\2\2Z\f\3\2\2\2[\\\5A!\2\\]\5%\23\2]^\5\63\32\2^_\5%\23\2_"+
		"`\5!\21\2`a\5C\"\2a\16\3\2\2\2bc\5;\36\2cd\5? \2de\59\35\2ef\5/\30\2f"+
		"g\5%\23\2gh\5!\21\2hi\5C\"\2i\20\3\2\2\2jk\5\67\34\2kl\5\35\17\2lm\5C"+
		"\"\2mn\5E#\2no\5? \2op\5\35\17\2pq\5\63\32\2qr\t\2\2\2rs\5/\30\2st\59"+
		"\35\2tu\5-\27\2uv\5\67\34\2v\22\3\2\2\2wx\5!\21\2xy\5\35\17\2yz\5? \2"+
		"z{\5C\"\2{|\5%\23\2|}\5A!\2}~\5-\27\2~\177\5\35\17\2\177\u0080\5\67\34"+
		"\2\u0080\u0081\t\2\2\2\u0081\u0082\5;\36\2\u0082\u0083\5? \2\u0083\u0084"+
		"\59\35\2\u0084\u0085\5#\22\2\u0085\u0086\5E#\2\u0086\u0087\5!\21\2\u0087"+
		"\u0088\5C\"\2\u0088\24\3\2\2\2\u0089\u008d\7)\2\2\u008a\u008c\13\2\2\2"+
		"\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008e\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7)\2\2\u0091"+
		"\26\3\2\2\2\u0092\u0096\t\3\2\2\u0093\u0095\t\4\2\2\u0094\u0093\3\2\2"+
		"\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\30"+
		"\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009d\t\3\2\2\u009a\u009c\t\5\2\2\u009b"+
		"\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\32\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a2\t\6\2\2\u00a1\u00a0"+
		"\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\b\16\2\2\u00a6\34\3\2\2\2\u00a7\u00a8\t\7\2"+
		"\2\u00a8\36\3\2\2\2\u00a9\u00aa\t\b\2\2\u00aa \3\2\2\2\u00ab\u00ac\t\t"+
		"\2\2\u00ac\"\3\2\2\2\u00ad\u00ae\t\n\2\2\u00ae$\3\2\2\2\u00af\u00b0\t"+
		"\13\2\2\u00b0&\3\2\2\2\u00b1\u00b2\t\f\2\2\u00b2(\3\2\2\2\u00b3\u00b4"+
		"\t\r\2\2\u00b4*\3\2\2\2\u00b5\u00b6\t\16\2\2\u00b6,\3\2\2\2\u00b7\u00b8"+
		"\t\17\2\2\u00b8.\3\2\2\2\u00b9\u00ba\t\20\2\2\u00ba\60\3\2\2\2\u00bb\u00bc"+
		"\t\21\2\2\u00bc\62\3\2\2\2\u00bd\u00be\t\22\2\2\u00be\64\3\2\2\2\u00bf"+
		"\u00c0\t\23\2\2\u00c0\66\3\2\2\2\u00c1\u00c2\t\24\2\2\u00c28\3\2\2\2\u00c3"+
		"\u00c4\t\25\2\2\u00c4:\3\2\2\2\u00c5\u00c6\t\26\2\2\u00c6<\3\2\2\2\u00c7"+
		"\u00c8\t\27\2\2\u00c8>\3\2\2\2\u00c9\u00ca\t\30\2\2\u00ca@\3\2\2\2\u00cb"+
		"\u00cc\t\31\2\2\u00ccB\3\2\2\2\u00cd\u00ce\t\32\2\2\u00ceD\3\2\2\2\u00cf"+
		"\u00d0\t\33\2\2\u00d0F\3\2\2\2\u00d1\u00d2\t\34\2\2\u00d2H\3\2\2\2\u00d3"+
		"\u00d4\t\35\2\2\u00d4J\3\2\2\2\u00d5\u00d6\t\36\2\2\u00d6L\3\2\2\2\u00d7"+
		"\u00d8\t\37\2\2\u00d8N\3\2\2\2\u00d9\u00da\t \2\2\u00daP\3\2\2\2\b\2\u008d"+
		"\u0094\u0096\u009d\u00a3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}