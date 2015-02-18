// Generated from /home/dbborens/IdeaProjects/protoverse/src/Nanosyntax.g4 by ANTLR 4.5
package compiler.interpret.nanosyntax;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NanosyntaxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, ID=15, STRING=16, FLOAT=17, 
		INTEGER=18, WS=19, COMMENT=20, LINE_COMMENT=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "ID", "STRING", "FLOAT", "INTEGER", 
		"INT", "EXP", "WS", "COMMENT", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "'define'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", 
		"'<='", "'>='", "'>'", "'<'", "'->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "ID", "STRING", "FLOAT", "INTEGER", "WS", "COMMENT", 
		"LINE_COMMENT"
	};
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
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public NanosyntaxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Nanosyntax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\7\20X\n\20\f\20\16\20[\13\20\3\21\3\21\7\21_\n\21\f"+
		"\21\16\21b\13\21\3\21\3\21\3\22\5\22g\n\22\3\22\3\22\3\22\6\22l\n\22\r"+
		"\22\16\22m\3\22\5\22q\n\22\3\22\5\22t\n\22\3\22\3\22\3\22\5\22y\n\22\3"+
		"\23\5\23|\n\23\3\23\3\23\3\24\3\24\3\24\7\24\u0083\n\24\f\24\16\24\u0086"+
		"\13\24\5\24\u0088\n\24\3\25\3\25\5\25\u008c\n\25\3\25\3\25\3\26\6\26\u0091"+
		"\n\26\r\26\16\26\u0092\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u009b\n\27\f"+
		"\27\16\27\u009e\13\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30"+
		"\u00a9\n\30\f\30\16\30\u00ac\13\30\3\30\3\30\3\u009c\2\31\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\2)\2+\25-\26/\27\3\2\13\4\2C\\c|\5\2\62;C\\c|\4\2$$^^\3\2\62;\3"+
		"\2\63;\4\2GGgg\4\2--//\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u00ba\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\65"+
		"\3\2\2\2\t<\3\2\2\2\13>\3\2\2\2\r@\3\2\2\2\17B\3\2\2\2\21D\3\2\2\2\23"+
		"F\3\2\2\2\25H\3\2\2\2\27K\3\2\2\2\31N\3\2\2\2\33P\3\2\2\2\35R\3\2\2\2"+
		"\37U\3\2\2\2!\\\3\2\2\2#x\3\2\2\2%{\3\2\2\2\'\u0087\3\2\2\2)\u0089\3\2"+
		"\2\2+\u0090\3\2\2\2-\u0096\3\2\2\2/\u00a4\3\2\2\2\61\62\7=\2\2\62\4\3"+
		"\2\2\2\63\64\7<\2\2\64\6\3\2\2\2\65\66\7f\2\2\66\67\7g\2\2\678\7h\2\2"+
		"89\7k\2\29:\7p\2\2:;\7g\2\2;\b\3\2\2\2<=\7}\2\2=\n\3\2\2\2>?\7\177\2\2"+
		"?\f\3\2\2\2@A\7-\2\2A\16\3\2\2\2BC\7/\2\2C\20\3\2\2\2DE\7,\2\2E\22\3\2"+
		"\2\2FG\7\61\2\2G\24\3\2\2\2HI\7>\2\2IJ\7?\2\2J\26\3\2\2\2KL\7@\2\2LM\7"+
		"?\2\2M\30\3\2\2\2NO\7@\2\2O\32\3\2\2\2PQ\7>\2\2Q\34\3\2\2\2RS\7/\2\2S"+
		"T\7@\2\2T\36\3\2\2\2UY\t\2\2\2VX\t\3\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2"+
		"YZ\3\2\2\2Z \3\2\2\2[Y\3\2\2\2\\`\7$\2\2]_\n\4\2\2^]\3\2\2\2_b\3\2\2\2"+
		"`^\3\2\2\2`a\3\2\2\2ac\3\2\2\2b`\3\2\2\2cd\7$\2\2d\"\3\2\2\2eg\7/\2\2"+
		"fe\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\5\'\24\2ik\7\60\2\2jl\t\5\2\2kj\3\2\2"+
		"\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2oq\5)\25\2po\3\2\2\2pq\3\2\2"+
		"\2qy\3\2\2\2rt\7/\2\2sr\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\5\'\24\2vw\5)\25"+
		"\2wy\3\2\2\2xf\3\2\2\2xs\3\2\2\2y$\3\2\2\2z|\7/\2\2{z\3\2\2\2{|\3\2\2"+
		"\2|}\3\2\2\2}~\5\'\24\2~&\3\2\2\2\177\u0088\7\62\2\2\u0080\u0084\t\6\2"+
		"\2\u0081\u0083\t\5\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0087"+
		"\177\3\2\2\2\u0087\u0080\3\2\2\2\u0088(\3\2\2\2\u0089\u008b\t\7\2\2\u008a"+
		"\u008c\t\b\2\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008e\5\'\24\2\u008e*\3\2\2\2\u008f\u0091\t\t\2\2\u0090\u008f"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\b\26\2\2\u0095,\3\2\2\2\u0096\u0097\7\61\2"+
		"\2\u0097\u0098\7,\2\2\u0098\u009c\3\2\2\2\u0099\u009b\13\2\2\2\u009a\u0099"+
		"\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d"+
		"\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\7,\2\2\u00a0\u00a1\7\61"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\27\2\2\u00a3.\3\2\2\2\u00a4\u00a5"+
		"\7\61\2\2\u00a5\u00a6\7\61\2\2\u00a6\u00aa\3\2\2\2\u00a7\u00a9\n\n\2\2"+
		"\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae\b\30\2\2"+
		"\u00ae\60\3\2\2\2\21\2Y`fmpsx{\u0084\u0087\u008b\u0092\u009c\u00aa\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}