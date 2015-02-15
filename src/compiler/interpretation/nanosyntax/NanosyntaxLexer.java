// Generated from /Users/dbborens/IdeaProjects/protoverse/src/Nanosyntax.g4 by ANTLR 4.5
package compiler.interpretation.nanosyntax;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NanosyntaxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, STRING=17, 
		FLOAT=18, INTEGER=19, WS=20, COMMENT=21, LINE_COMMENT=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "ID", "STRING", "FLOAT", 
		"INTEGER", "INT", "EXP", "WS", "COMMENT", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "'define'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", 
		"'<='", "'>='", "'>'", "'<'", "'->'", "'$'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "ID", "STRING", "FLOAT", "INTEGER", "WS", "COMMENT", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u00b3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\7\21\\\n\21\f\21\16\21_\13\21\3"+
		"\22\3\22\7\22c\n\22\f\22\16\22f\13\22\3\22\3\22\3\23\5\23k\n\23\3\23\3"+
		"\23\3\23\6\23p\n\23\r\23\16\23q\3\23\5\23u\n\23\3\23\5\23x\n\23\3\23\3"+
		"\23\3\23\5\23}\n\23\3\24\5\24\u0080\n\24\3\24\3\24\3\25\3\25\3\25\7\25"+
		"\u0087\n\25\f\25\16\25\u008a\13\25\5\25\u008c\n\25\3\26\3\26\5\26\u0090"+
		"\n\26\3\26\3\26\3\27\6\27\u0095\n\27\r\27\16\27\u0096\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\7\30\u009f\n\30\f\30\16\30\u00a2\13\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\7\31\u00ad\n\31\f\31\16\31\u00b0\13\31\3"+
		"\31\3\31\3\u00a0\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\2+\2-\26/\27\61\30\3\2\13"+
		"\4\2C\\c|\5\2\62;C\\c|\4\2$$^^\3\2\62;\3\2\63;\4\2GGgg\4\2--//\5\2\13"+
		"\f\16\17\"\"\4\2\f\f\17\17\u00be\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t>\3\2"+
		"\2\2\13@\3\2\2\2\rB\3\2\2\2\17D\3\2\2\2\21F\3\2\2\2\23H\3\2\2\2\25J\3"+
		"\2\2\2\27M\3\2\2\2\31P\3\2\2\2\33R\3\2\2\2\35T\3\2\2\2\37W\3\2\2\2!Y\3"+
		"\2\2\2#`\3\2\2\2%|\3\2\2\2\'\177\3\2\2\2)\u008b\3\2\2\2+\u008d\3\2\2\2"+
		"-\u0094\3\2\2\2/\u009a\3\2\2\2\61\u00a8\3\2\2\2\63\64\7=\2\2\64\4\3\2"+
		"\2\2\65\66\7<\2\2\66\6\3\2\2\2\678\7f\2\289\7g\2\29:\7h\2\2:;\7k\2\2;"+
		"<\7p\2\2<=\7g\2\2=\b\3\2\2\2>?\7}\2\2?\n\3\2\2\2@A\7\177\2\2A\f\3\2\2"+
		"\2BC\7-\2\2C\16\3\2\2\2DE\7/\2\2E\20\3\2\2\2FG\7,\2\2G\22\3\2\2\2HI\7"+
		"\61\2\2I\24\3\2\2\2JK\7>\2\2KL\7?\2\2L\26\3\2\2\2MN\7@\2\2NO\7?\2\2O\30"+
		"\3\2\2\2PQ\7@\2\2Q\32\3\2\2\2RS\7>\2\2S\34\3\2\2\2TU\7/\2\2UV\7@\2\2V"+
		"\36\3\2\2\2WX\7&\2\2X \3\2\2\2Y]\t\2\2\2Z\\\t\3\2\2[Z\3\2\2\2\\_\3\2\2"+
		"\2][\3\2\2\2]^\3\2\2\2^\"\3\2\2\2_]\3\2\2\2`d\7$\2\2ac\n\4\2\2ba\3\2\2"+
		"\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7$\2\2h$\3\2\2"+
		"\2ik\7/\2\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\5)\25\2mo\7\60\2\2np\t\5\2"+
		"\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2su\5+\26\2ts\3\2\2"+
		"\2tu\3\2\2\2u}\3\2\2\2vx\7/\2\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\5)\25"+
		"\2z{\5+\26\2{}\3\2\2\2|j\3\2\2\2|w\3\2\2\2}&\3\2\2\2~\u0080\7/\2\2\177"+
		"~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\5)\25\2\u0082"+
		"(\3\2\2\2\u0083\u008c\7\62\2\2\u0084\u0088\t\6\2\2\u0085\u0087\t\5\2\2"+
		"\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u0083\3\2\2\2\u008b"+
		"\u0084\3\2\2\2\u008c*\3\2\2\2\u008d\u008f\t\7\2\2\u008e\u0090\t\b\2\2"+
		"\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092"+
		"\5)\25\2\u0092,\3\2\2\2\u0093\u0095\t\t\2\2\u0094\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2"+
		"\2\2\u0098\u0099\b\27\2\2\u0099.\3\2\2\2\u009a\u009b\7\61\2\2\u009b\u009c"+
		"\7,\2\2\u009c\u00a0\3\2\2\2\u009d\u009f\13\2\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a3\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7,\2\2\u00a4\u00a5\7\61\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\b\30\2\2\u00a7\60\3\2\2\2\u00a8\u00a9\7\61"+
		"\2\2\u00a9\u00aa\7\61\2\2\u00aa\u00ae\3\2\2\2\u00ab\u00ad\n\n\2\2\u00ac"+
		"\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\b\31\2\2\u00b2"+
		"\62\3\2\2\2\21\2]djqtw|\177\u0088\u008b\u008f\u0096\u00a0\u00ae\3\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}