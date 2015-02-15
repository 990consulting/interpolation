// Generated from /Users/dbborens/IdeaProjects/protoverse/src/Nanosyntax.g4 by ANTLR 4.5
package compiler.interpretation.nanosyntax;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NanosyntaxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, STRING=17, 
		FLOAT=18, INTEGER=19, WS=20, COMMENT=21, LINE_COMMENT=22;
	public static final int
		RULE_root = 0, RULE_statement = 1, RULE_assignment = 2, RULE_definition = 3, 
		RULE_block = 4, RULE_singleton = 5, RULE_operation = 6, RULE_operand = 7, 
		RULE_operator = 8, RULE_reference = 9, RULE_type = 10, RULE_primitive = 11, 
		RULE_stringPrimitive = 12, RULE_floatPrimitive = 13, RULE_intPrimitive = 14;
	public static final String[] ruleNames = {
		"root", "statement", "assignment", "definition", "block", "singleton", 
		"operation", "operand", "operator", "reference", "type", "primitive", 
		"stringPrimitive", "floatPrimitive", "intPrimitive"
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

	@Override
	public String getGrammarFileName() { return "Nanosyntax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NanosyntaxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << ID))) != 0)) {
				{
				{
				setState(30); 
				statement();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public DefinitionContext definition() {
			return getRuleContext(DefinitionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(42);
			switch (_input.LA(1)) {
			case T__14:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(36); 
				assignment();
				setState(37); 
				match(T__0);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39); 
				definition();
				setState(40); 
				match(T__0);
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

	public static class AssignmentContext extends ParserRuleContext {
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SingletonContext singleton() {
			return getRuleContext(SingletonContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			setState(51);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44); 
				reference();
				setState(45); 
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47); 
				reference();
				setState(48); 
				match(T__1);
				setState(49); 
				singleton(0);
				}
				break;
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

	public static class DefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(NanosyntaxParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SingletonContext singleton() {
			return getRuleContext(SingletonContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_definition);
		try {
			setState(64);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53); 
				match(T__2);
				setState(54); 
				type();
				setState(55); 
				match(ID);
				setState(56); 
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58); 
				match(T__2);
				setState(59); 
				type();
				setState(60); 
				match(ID);
				setState(61); 
				match(T__1);
				setState(62); 
				singleton(0);
				}
				break;
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

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			match(T__3);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << ID))) != 0)) {
				{
				{
				setState(67); 
				statement();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73); 
			match(T__4);
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

	public static class SingletonContext extends ParserRuleContext {
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public PrimitiveContext primitive() {
			return getRuleContext(PrimitiveContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public List<SingletonContext> singleton() {
			return getRuleContexts(SingletonContext.class);
		}
		public SingletonContext singleton(int i) {
			return getRuleContext(SingletonContext.class,i);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public SingletonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleton; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitSingleton(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingletonContext singleton() throws RecognitionException {
		return singleton(0);
	}

	private SingletonContext singleton(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SingletonContext _localctx = new SingletonContext(_ctx, _parentState);
		SingletonContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_singleton, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(76); 
				reference();
				}
				break;
			case 2:
				{
				setState(77); 
				operation();
				}
				break;
			case 3:
				{
				setState(78); 
				primitive();
				}
				break;
			case 4:
				{
				setState(79); 
				assignment();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(88);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SingletonContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_singleton);
					setState(82);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(83); 
					operand();
					setState(84); 
					singleton(4);
					}
					} 
				}
				setState(90);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_operation);
		try {
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91); 
				operand();
				setState(92); 
				operator();
				setState(93); 
				operand();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95); 
				operand();
				setState(96); 
				operator();
				setState(97); 
				operation();
				}
				break;
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

	public static class OperandContext extends ParserRuleContext {
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public PrimitiveContext primitive() {
			return getRuleContext(PrimitiveContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_operand);
		try {
			setState(104);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101); 
				reference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102); 
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(103); 
				primitive();
				}
				break;
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

	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NanosyntaxParser.ID, 0); }
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_reference);
		try {
			setState(117);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); 
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109); 
				match(ID);
				setState(110); 
				match(T__13);
				setState(111); 
				reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112); 
				match(ID);
				setState(113); 
				match(T__14);
				setState(114); 
				reference();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(115); 
				match(T__14);
				setState(116); 
				reference();
				}
				break;
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NanosyntaxParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); 
			match(ID);
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

	public static class PrimitiveContext extends ParserRuleContext {
		public StringPrimitiveContext stringPrimitive() {
			return getRuleContext(StringPrimitiveContext.class,0);
		}
		public FloatPrimitiveContext floatPrimitive() {
			return getRuleContext(FloatPrimitiveContext.class,0);
		}
		public IntPrimitiveContext intPrimitive() {
			return getRuleContext(IntPrimitiveContext.class,0);
		}
		public PrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveContext primitive() throws RecognitionException {
		PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_primitive);
		try {
			setState(124);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(121); 
				stringPrimitive();
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(122); 
				floatPrimitive();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(123); 
				intPrimitive();
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

	public static class StringPrimitiveContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(NanosyntaxParser.STRING, 0); }
		public StringPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringPrimitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitStringPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringPrimitiveContext stringPrimitive() throws RecognitionException {
		StringPrimitiveContext _localctx = new StringPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stringPrimitive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); 
			match(STRING);
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

	public static class FloatPrimitiveContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(NanosyntaxParser.FLOAT, 0); }
		public FloatPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatPrimitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitFloatPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatPrimitiveContext floatPrimitive() throws RecognitionException {
		FloatPrimitiveContext _localctx = new FloatPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_floatPrimitive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); 
			match(FLOAT);
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

	public static class IntPrimitiveContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NanosyntaxParser.INTEGER, 0); }
		public IntPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intPrimitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NanosyntaxVisitor ) return ((NanosyntaxVisitor<? extends T>)visitor).visitIntPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntPrimitiveContext intPrimitive() throws RecognitionException {
		IntPrimitiveContext _localctx = new IntPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_intPrimitive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); 
			match(INTEGER);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5: 
			return singleton_sempred((SingletonContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean singleton_sempred(SingletonContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: 
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\30\u0087\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\7\2\"\n\2\f\2"+
		"\16\2%\13\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3-\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4\66\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5\3\6"+
		"\3\6\7\6G\n\6\f\6\16\6J\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7S\n\7\3\7"+
		"\3\7\3\7\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\5\bf\n\b\3\t\3\t\3\t\5\tk\n\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\5\13x\n\13\3\f\3\f\3\r\3\r\3\r\5\r\177\n\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\20\2\3\f\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2"+
		"\3\3\2\b\17\u0088\2#\3\2\2\2\4,\3\2\2\2\6\65\3\2\2\2\bB\3\2\2\2\nD\3\2"+
		"\2\2\fR\3\2\2\2\16e\3\2\2\2\20j\3\2\2\2\22l\3\2\2\2\24w\3\2\2\2\26y\3"+
		"\2\2\2\30~\3\2\2\2\32\u0080\3\2\2\2\34\u0082\3\2\2\2\36\u0084\3\2\2\2"+
		" \"\5\4\3\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\3\3\2\2\2%#\3\2"+
		"\2\2&\'\5\6\4\2\'(\7\3\2\2(-\3\2\2\2)*\5\b\5\2*+\7\3\2\2+-\3\2\2\2,&\3"+
		"\2\2\2,)\3\2\2\2-\5\3\2\2\2./\5\24\13\2/\60\5\n\6\2\60\66\3\2\2\2\61\62"+
		"\5\24\13\2\62\63\7\4\2\2\63\64\5\f\7\2\64\66\3\2\2\2\65.\3\2\2\2\65\61"+
		"\3\2\2\2\66\7\3\2\2\2\678\7\5\2\289\5\26\f\29:\7\22\2\2:;\5\n\6\2;C\3"+
		"\2\2\2<=\7\5\2\2=>\5\26\f\2>?\7\22\2\2?@\7\4\2\2@A\5\f\7\2AC\3\2\2\2B"+
		"\67\3\2\2\2B<\3\2\2\2C\t\3\2\2\2DH\7\6\2\2EG\5\4\3\2FE\3\2\2\2GJ\3\2\2"+
		"\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7\7\2\2L\13\3\2\2\2MN\b\7"+
		"\1\2NS\5\24\13\2OS\5\16\b\2PS\5\30\r\2QS\5\6\4\2RM\3\2\2\2RO\3\2\2\2R"+
		"P\3\2\2\2RQ\3\2\2\2SZ\3\2\2\2TU\f\5\2\2UV\5\20\t\2VW\5\f\7\6WY\3\2\2\2"+
		"XT\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\r\3\2\2\2\\Z\3\2\2\2]^\5\20"+
		"\t\2^_\5\22\n\2_`\5\20\t\2`f\3\2\2\2ab\5\20\t\2bc\5\22\n\2cd\5\16\b\2"+
		"df\3\2\2\2e]\3\2\2\2ea\3\2\2\2f\17\3\2\2\2gk\5\24\13\2hk\5\6\4\2ik\5\30"+
		"\r\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2k\21\3\2\2\2lm\t\2\2\2m\23\3\2\2\2n"+
		"x\7\22\2\2op\7\22\2\2pq\7\20\2\2qx\5\24\13\2rs\7\22\2\2st\7\21\2\2tx\5"+
		"\24\13\2uv\7\21\2\2vx\5\24\13\2wn\3\2\2\2wo\3\2\2\2wr\3\2\2\2wu\3\2\2"+
		"\2x\25\3\2\2\2yz\7\22\2\2z\27\3\2\2\2{\177\5\32\16\2|\177\5\34\17\2}\177"+
		"\5\36\20\2~{\3\2\2\2~|\3\2\2\2~}\3\2\2\2\177\31\3\2\2\2\u0080\u0081\7"+
		"\23\2\2\u0081\33\3\2\2\2\u0082\u0083\7\24\2\2\u0083\35\3\2\2\2\u0084\u0085"+
		"\7\25\2\2\u0085\37\3\2\2\2\r#,\65BHRZejw~";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}