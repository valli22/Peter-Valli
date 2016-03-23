/* The following code was generated by JFlex 1.6.1 */

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/UNI/Git hub valli/Peter-Valli/PL/LexicoPracticas.flex</tt>
 */
class PracticaLexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int constLit = 2;
  public static final int comentPar = 4;
  public static final int comentLlav = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\40\0\1\0\3\0\1\5\2\0\1\11\1\13\1\10\1\7\1\56"+
    "\1\53\1\3\1\4\1\0\12\2\1\57\1\54\1\60\1\55\1\61"+
    "\2\0\1\31\1\6\1\42\1\6\1\30\1\6\1\36\1\43\1\33"+
    "\2\1\1\32\1\1\1\34\1\1\1\65\1\1\1\27\1\1\1\35"+
    "\6\1\1\62\1\0\1\63\1\0\1\1\1\0\1\25\1\14\1\37"+
    "\1\21\1\15\1\46\1\16\1\50\1\17\2\1\1\51\1\26\1\20"+
    "\1\24\1\22\1\1\1\23\1\40\1\41\1\45\1\44\1\52\1\1"+
    "\1\47\1\1\1\12\1\0\1\64\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff92\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\2\1\1\2\1\3\1\4\1\1\1\5\1\6"+
    "\1\7\1\10\1\11\22\1\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\1"+
    "\1\25\1\26\1\0\1\27\1\2\1\0\1\30\3\26"+
    "\1\31\2\26\1\32\2\26\1\33\1\34\7\26\1\35"+
    "\7\26\1\36\1\37\1\40\1\41\1\42\1\43\1\0"+
    "\1\26\1\44\1\26\1\45\1\46\2\26\1\47\1\26"+
    "\1\50\7\26\1\51\1\52\2\26\1\43\1\26\1\53"+
    "\4\26\1\54\2\26\1\55\1\56\1\57\3\26\1\60"+
    "\3\26\1\61\1\26\1\62\2\26\1\63\2\26\1\64"+
    "\3\26\1\65\1\26\1\66\4\26\1\67\1\70\1\71";

  private static int [] zzUnpackAction() {
    int [] result = new int[148];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\66\0\154\0\242\0\330\0\u010e\0\u0144\0\u017a"+
    "\0\u01b0\0\u01e6\0\330\0\330\0\330\0\330\0\u021c\0\u0252"+
    "\0\u0288\0\u02be\0\u02f4\0\u032a\0\u0360\0\u0396\0\u03cc\0\u0402"+
    "\0\u0438\0\u046e\0\u04a4\0\u04da\0\u0510\0\u0546\0\u057c\0\u05b2"+
    "\0\u05e8\0\330\0\330\0\330\0\u017a\0\u061e\0\u0654\0\u068a"+
    "\0\330\0\330\0\330\0\u06c0\0\u06f6\0\330\0\u010e\0\u072c"+
    "\0\330\0\u0762\0\u0798\0\330\0\u07ce\0\u0804\0\u083a\0\u010e"+
    "\0\u0870\0\u08a6\0\u010e\0\u08dc\0\u0912\0\u010e\0\u010e\0\u0948"+
    "\0\u097e\0\u09b4\0\u09ea\0\u0a20\0\u0a56\0\u0a8c\0\u010e\0\u0ac2"+
    "\0\u0af8\0\u0b2e\0\u0b64\0\u0b9a\0\u0bd0\0\u0c06\0\330\0\330"+
    "\0\330\0\330\0\330\0\u072c\0\u0c3c\0\u0c72\0\u010e\0\u0ca8"+
    "\0\u010e\0\u010e\0\u0cde\0\u0d14\0\u010e\0\u0d4a\0\u010e\0\u0d80"+
    "\0\u0db6\0\u0dec\0\u0e22\0\u0e58\0\u0e8e\0\u0ec4\0\u010e\0\u010e"+
    "\0\u0efa\0\u0f30\0\u0c3c\0\u0f66\0\u010e\0\u0f9c\0\u0fd2\0\u1008"+
    "\0\u103e\0\u010e\0\u1074\0\u10aa\0\u010e\0\u010e\0\u010e\0\u10e0"+
    "\0\u1116\0\u114c\0\u010e\0\u1182\0\u11b8\0\u11ee\0\u010e\0\u1224"+
    "\0\u010e\0\u125a\0\u1290\0\u010e\0\u12c6\0\u12fc\0\u010e\0\u1332"+
    "\0\u1368\0\u139e\0\u010e\0\u13d4\0\u010e\0\u140a\0\u1440\0\u1476"+
    "\0\u14ac\0\u010e\0\u010e\0\u010e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[148];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\10\1\11\1\12\1\6\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\6\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\3\6\1\33\3\6\1\34\1\6\1\35\1\36\1\6"+
    "\1\37\1\6\1\40\3\6\1\41\1\42\1\43\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\52\1\5\1\6"+
    "\11\53\1\54\54\53\7\5\1\55\5\5\1\0\1\5"+
    "\2\0\2\5\1\0\1\5\1\0\12\5\2\0\4\5"+
    "\1\0\16\5\1\0\64\5\1\56\1\5\67\0\2\57"+
    "\3\0\1\57\5\0\37\57\12\0\1\57\2\0\1\7"+
    "\1\0\1\60\63\0\1\7\67\0\1\61\63\0\1\62"+
    "\1\63\2\0\1\62\21\0\2\62\10\0\1\62\13\0"+
    "\1\63\16\0\1\64\57\0\2\57\3\0\1\57\5\0"+
    "\1\57\1\65\35\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\4\57\1\66\30\57\1\67\1\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\32\57\1\70"+
    "\4\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\10\57\1\71\26\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\3\57\1\72\4\57\1\73\26\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\7\57\1\74"+
    "\27\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\1\57\1\75\35\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\7\57\1\76\22\57\1\77\4\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\4\57\1\100"+
    "\2\57\1\101\27\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\10\57\1\102\26\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\14\57\1\103\22\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\20\57\1\104"+
    "\16\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\10\57\1\105\1\106\25\57\12\0\1\57\1\0\2\57"+
    "\3\0\1\57\5\0\10\57\1\107\22\57\1\110\1\111"+
    "\2\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\27\57\1\112\7\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\11\57\1\113\25\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\10\57\1\114\20\57\1\115"+
    "\5\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\34\57\1\116\2\57\12\0\1\57\55\0\1\117\65\0"+
    "\1\120\3\0\1\121\61\0\1\122\21\0\1\123\64\0"+
    "\1\56\57\0\1\124\65\0\1\62\1\0\1\125\1\0"+
    "\1\62\21\0\2\62\10\0\1\62\25\0\1\62\3\0"+
    "\1\62\21\0\2\62\10\0\1\62\24\0\2\57\3\0"+
    "\1\57\5\0\2\57\1\126\34\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\5\57\1\127\31\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\24\57\1\130"+
    "\12\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\25\57\1\131\11\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\30\57\1\132\6\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\10\57\1\133\26\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\23\57\1\134"+
    "\13\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\5\57\1\135\31\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\7\57\1\136\27\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\5\57\1\137\31\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\15\57\1\140"+
    "\21\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\21\57\1\141\15\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\4\57\1\142\32\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\24\57\1\143\12\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\6\57\1\144"+
    "\30\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\1\57\1\145\35\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\15\57\1\146\21\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\7\57\1\147\27\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\7\57\1\150"+
    "\27\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\4\57\1\151\32\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\3\57\1\152\33\57\12\0\1\57\2\0"+
    "\1\153\3\0\1\153\21\0\2\153\10\0\1\153\24\0"+
    "\2\57\3\0\1\57\5\0\3\57\1\154\33\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\1\57\1\155"+
    "\35\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\2\57\1\156\20\57\1\157\13\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\10\57\1\160\26\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\11\57\1\161"+
    "\25\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\16\57\1\162\20\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\14\57\1\163\22\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\24\57\1\164\12\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\1\57\1\165"+
    "\35\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\1\57\1\166\35\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\4\57\1\167\32\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\13\57\1\170\23\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\23\57\1\171"+
    "\13\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\35\57\1\172\1\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\4\57\1\173\32\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\7\57\1\174\27\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\1\57\1\175"+
    "\35\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\7\57\1\176\27\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\33\57\1\177\3\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\22\57\1\200\14\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\25\57\1\201"+
    "\11\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\15\57\1\202\21\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\25\57\1\203\11\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\1\57\1\204\35\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\11\57\1\205"+
    "\25\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\5\57\1\206\31\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\5\57\1\207\31\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\14\57\1\210\22\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\26\57\1\211"+
    "\10\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\3\57\1\212\33\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\12\57\1\213\24\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\31\57\1\214\5\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\13\57\1\215"+
    "\23\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\21\57\1\216\15\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\10\57\1\217\26\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\7\57\1\220\27\57\12\0"+
    "\1\57\1\0\2\57\3\0\1\57\5\0\14\57\1\221"+
    "\22\57\12\0\1\57\1\0\2\57\3\0\1\57\5\0"+
    "\4\57\1\222\32\57\12\0\1\57\1\0\2\57\3\0"+
    "\1\57\5\0\1\57\1\223\35\57\12\0\1\57\1\0"+
    "\2\57\3\0\1\57\5\0\13\57\1\224\23\57\12\0"+
    "\1\57";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5346];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\11\5\1\4\11\23\1\3\11\4\1\3\11"+
    "\2\1\1\11\1\1\1\0\1\11\1\1\1\0\1\11"+
    "\32\1\5\11\1\1\1\0\77\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[148];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
private String litconst;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  PracticaLexico(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 184) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { 
            }
          case 58: break;
          case 2: 
            { return new java_cup.runtime.Symbol(sym.constnumint,yyline+1,yycolumn+1,yytext());
            }
          case 59: break;
          case 3: 
            { return new java_cup.runtime.Symbol(sym.menos,yyline+1,yycolumn+1,yytext());
            }
          case 60: break;
          case 4: 
            { return new java_cup.runtime.Symbol(sym.punto,yyline+1,yycolumn+1,yytext());
            }
          case 61: break;
          case 5: 
            { return new java_cup.runtime.Symbol(sym.por,yyline+1,yycolumn+1,yytext());
            }
          case 62: break;
          case 6: 
            { return new java_cup.runtime.Symbol(sym.parentesisder,yyline+1,yycolumn+1,yytext());
            }
          case 63: break;
          case 7: 
            { litconst=""; yybegin(constLit);
            }
          case 64: break;
          case 8: 
            { yybegin(comentLlav);
            }
          case 65: break;
          case 9: 
            { return new java_cup.runtime.Symbol(sym.parentesisizq,yyline+1,yycolumn+1,yytext());
            }
          case 66: break;
          case 10: 
            { return new java_cup.runtime.Symbol(sym.coma,yyline+1,yycolumn+1,yytext());
            }
          case 67: break;
          case 11: 
            { return new java_cup.runtime.Symbol(sym.puntocoma,yyline+1,yycolumn+1,yytext());
            }
          case 68: break;
          case 12: 
            { return new java_cup.runtime.Symbol(sym.igual,yyline+1,yycolumn+1,yytext());
            }
          case 69: break;
          case 13: 
            { return new java_cup.runtime.Symbol(sym.mas,yyline+1,yycolumn+1,yytext());
            }
          case 70: break;
          case 14: 
            { return new java_cup.runtime.Symbol(sym.dospuntos,yyline+1,yycolumn+1,yytext());
            }
          case 71: break;
          case 15: 
            { return new java_cup.runtime.Symbol(sym.mayor,yyline+1,yycolumn+1,yytext());
            }
          case 72: break;
          case 16: 
            { return new java_cup.runtime.Symbol(sym.menor,yyline+1,yycolumn+1,yytext());
            }
          case 73: break;
          case 17: 
            { return new java_cup.runtime.Symbol(sym.corcheteizq,yyline+1,yycolumn+1,yytext());
            }
          case 74: break;
          case 18: 
            { return new java_cup.runtime.Symbol(sym.corcheteder,yyline+1,yycolumn+1,yytext());
            }
          case 75: break;
          case 19: 
            { litconst+=yytext();
            }
          case 76: break;
          case 20: 
            { yybegin(YYINITIAL); return new java_cup.runtime.Symbol(sym.constlit,yyline+1,yycolumn+1,litconst);
            }
          case 77: break;
          case 21: 
            { yybegin(YYINITIAL);
            }
          case 78: break;
          case 22: 
            { return new java_cup.runtime.Symbol(sym.id,yyline+1,yycolumn+1,yytext());
            }
          case 79: break;
          case 23: 
            { return new java_cup.runtime.Symbol(sym.puntopunto,yyline+1,yycolumn+1,yytext());
            }
          case 80: break;
          case 24: 
            { yybegin(comentPar);
            }
          case 81: break;
          case 25: 
            { return new java_cup.runtime.Symbol(sym.tif,yyline+1,yycolumn+1,yytext());
            }
          case 82: break;
          case 26: 
            { return new java_cup.runtime.Symbol(sym.tdo,yyline+1,yycolumn+1,yytext());
            }
          case 83: break;
          case 27: 
            { return new java_cup.runtime.Symbol(sym.or,yyline+1,yycolumn+1,yytext());
            }
          case 84: break;
          case 28: 
            { return new java_cup.runtime.Symbol(sym.of,yyline+1,yycolumn+1,yytext());
            }
          case 85: break;
          case 29: 
            { return new java_cup.runtime.Symbol(sym.to,yyline+1,yycolumn+1,yytext());
            }
          case 86: break;
          case 30: 
            { return new java_cup.runtime.Symbol(sym.asignacion,yyline+1,yycolumn+1,yytext());
            }
          case 87: break;
          case 31: 
            { return new java_cup.runtime.Symbol(sym.menorigual,yyline+1,yycolumn+1,yytext());
            }
          case 88: break;
          case 32: 
            { return new java_cup.runtime.Symbol(sym.distinto,yyline+1,yycolumn+1,yytext());
            }
          case 89: break;
          case 33: 
            { return new java_cup.runtime.Symbol(sym.mayorigual,yyline+1,yycolumn+1,yytext());
            }
          case 90: break;
          case 34: 
            { litconst+="'";
            }
          case 91: break;
          case 35: 
            { return new java_cup.runtime.Symbol(sym.constnumreal,yyline+1,yycolumn+1,yytext());
            }
          case 92: break;
          case 36: 
            { return new java_cup.runtime.Symbol(sym.end,yyline+1,yycolumn+1,yytext());
            }
          case 93: break;
          case 37: 
            { return new java_cup.runtime.Symbol(sym.not,yyline+1,yycolumn+1,yytext());
            }
          case 94: break;
          case 38: 
            { return new java_cup.runtime.Symbol(sym.div,yyline+1,yycolumn+1,yytext());
            }
          case 95: break;
          case 39: 
            { return new java_cup.runtime.Symbol(sym.and,yyline+1,yycolumn+1,yytext());
            }
          case 96: break;
          case 40: 
            { return new java_cup.runtime.Symbol(sym.mod,yyline+1,yycolumn+1,yytext());
            }
          case 97: break;
          case 41: 
            { return new java_cup.runtime.Symbol(sym.var,yyline+1,yycolumn+1,yytext());
            }
          case 98: break;
          case 42: 
            { return new java_cup.runtime.Symbol(sym.tfor,yyline+1,yycolumn+1,yytext());
            }
          case 99: break;
          case 43: 
            { return new java_cup.runtime.Symbol(sym.telse,yyline+1,yycolumn+1,yytext());
            }
          case 100: break;
          case 44: 
            { return new java_cup.runtime.Symbol(sym.real,yyline+1,yycolumn+1,yytext());
            }
          case 101: break;
          case 45: 
            { return new java_cup.runtime.Symbol(sym.tcase,yyline+1,yycolumn+1,yytext());
            }
          case 102: break;
          case 46: 
            { return new java_cup.runtime.Symbol(sym.type,yyline+1,yycolumn+1,yytext());
            }
          case 103: break;
          case 47: 
            { return new java_cup.runtime.Symbol(sym.then,yyline+1,yycolumn+1,yytext());
            }
          case 104: break;
          case 48: 
            { return new java_cup.runtime.Symbol(sym.begin,yyline+1,yycolumn+1,yytext());
            }
          case 105: break;
          case 49: 
            { return new java_cup.runtime.Symbol(sym.array,yyline+1,yycolumn+1,yytext());
            }
          case 106: break;
          case 50: 
            { return new java_cup.runtime.Symbol(sym.tconst,yyline+1,yycolumn+1,yytext());
            }
          case 107: break;
          case 51: 
            { return new java_cup.runtime.Symbol(sym.twhile,yyline+1,yycolumn+1,yytext());
            }
          case 108: break;
          case 52: 
            { return new java_cup.runtime.Symbol(sym.record,yyline+1,yycolumn+1,yytext());
            }
          case 109: break;
          case 53: 
            { return new java_cup.runtime.Symbol(sym.program,yyline+1,yycolumn+1,yytext());
            }
          case 110: break;
          case 54: 
            { return new java_cup.runtime.Symbol(sym.tint,yyline+1,yycolumn+1,yytext());
            }
          case 111: break;
          case 55: 
            { return new java_cup.runtime.Symbol(sym.function,yyline+1,yycolumn+1,yytext());
            }
          case 112: break;
          case 56: 
            { return new java_cup.runtime.Symbol(sym.procedure,yyline+1,yycolumn+1,yytext());
            }
          case 113: break;
          case 57: 
            { return new java_cup.runtime.Symbol(sym.tchar,yyline+1,yycolumn+1,yytext());
            }
          case 114: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
