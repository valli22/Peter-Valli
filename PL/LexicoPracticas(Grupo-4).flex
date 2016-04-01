import java_cup.runtime.*;

%%

%{
private String litconst;
%}

%class PracticaLexico
%unicode
%cup
%line
%column

identificador = [_a-zA-Z][a-zA-Z0-9_]*
constNumInt = [+-]?[0-9]+
constNumReal = [+-]?[0-9]+"."[0-9]+
constNumIntH = "$"[+-]?[A-F0-9]+
constNumRealH = "$"[+-]?[A-F0-9]+"."[A-F0-9]+
principioParentesis = "(""*"

%xstate constLit
%xstate comentPar
%xstate comentLlav
%xstate comentParFin

%%

{constNumInt} {return new java_cup.runtime.Symbol(sym.constnumint,yyline+1,yycolumn+1,yytext());}
{constNumIntH} {return new java_cup.runtime.Symbol(sym.constnumint,yyline+1,yycolumn+1,yytext());}
{constNumReal} {return new java_cup.runtime.Symbol(sym.constnumreal,yyline+1,yycolumn+1,yytext());}
{constNumRealH} {return new java_cup.runtime.Symbol(sym.constnumreal,yyline+1,yycolumn+1,yytext());}

"'" {litconst=""; yybegin(constLit);}
"{" {yybegin(comentLlav);}
{principioParentesis} {yybegin(comentPar);}

"begin" {return new java_cup.runtime.Symbol(sym.begin,yyline+1,yycolumn+1,yytext());}
"end" {return new java_cup.runtime.Symbol(sym.end,yyline+1,yycolumn+1,yytext());}
"program" {return new java_cup.runtime.Symbol(sym.program,yyline+1,yycolumn+1,yytext());}
"REAL" {return new java_cup.runtime.Symbol(sym.real,yyline+1,yycolumn+1,yytext());}
"INTEGER" {return new java_cup.runtime.Symbol(sym.tint,yyline+1,yycolumn+1,yytext());}
"const" {return new java_cup.runtime.Symbol(sym.tconst,yyline+1,yycolumn+1,yytext());}
"CHARACTER" {return new java_cup.runtime.Symbol(sym.tchar,yyline+1,yycolumn+1,yytext());}
"var" {return new java_cup.runtime.Symbol(sym.var,yyline+1,yycolumn+1,yytext());}
"procedure" {return new java_cup.runtime.Symbol(sym.procedure,yyline+1,yycolumn+1,yytext());}
"function" {return new java_cup.runtime.Symbol(sym.function,yyline+1,yycolumn+1,yytext());}
"div" {return new java_cup.runtime.Symbol(sym.div,yyline+1,yycolumn+1,yytext());}
"mod" {return new java_cup.runtime.Symbol(sym.mod,yyline+1,yycolumn+1,yytext());}
"or" {return new java_cup.runtime.Symbol(sym.or,yyline+1,yycolumn+1,yytext());}
"and" {return new java_cup.runtime.Symbol(sym.and,yyline+1,yycolumn+1,yytext());}
"not" {return new java_cup.runtime.Symbol(sym.not,yyline+1,yycolumn+1,yytext());}
"type" {return new java_cup.runtime.Symbol(sym.type,yyline+1,yycolumn+1,yytext());}
"array" {return new java_cup.runtime.Symbol(sym.array,yyline+1,yycolumn+1,yytext());}
"of" {return new java_cup.runtime.Symbol(sym.of,yyline+1,yycolumn+1,yytext());}
"record" {return new java_cup.runtime.Symbol(sym.record,yyline+1,yycolumn+1,yytext());}
"if" {return new java_cup.runtime.Symbol(sym.tif,yyline+1,yycolumn+1,yytext());}
"then" {return new java_cup.runtime.Symbol(sym.then,yyline+1,yycolumn+1,yytext());}
"else" {return new java_cup.runtime.Symbol(sym.telse,yyline+1,yycolumn+1,yytext());}
"while" {return new java_cup.runtime.Symbol(sym.twhile,yyline+1,yycolumn+1,yytext());}
"do" {return new java_cup.runtime.Symbol(sym.tdo,yyline+1,yycolumn+1,yytext());}
"for" {return new java_cup.runtime.Symbol(sym.tfor,yyline+1,yycolumn+1,yytext());}
"to" {return new java_cup.runtime.Symbol(sym.to,yyline+1,yycolumn+1,yytext());}
"case" {return new java_cup.runtime.Symbol(sym.tcase,yyline+1,yycolumn+1,yytext());}
"," {return new java_cup.runtime.Symbol(sym.coma,yyline+1,yycolumn+1,yytext());}
";" {return new java_cup.runtime.Symbol(sym.puntocoma,yyline+1,yycolumn+1,yytext());}
"." {return new java_cup.runtime.Symbol(sym.punto,yyline+1,yycolumn+1,yytext());}
".." {return new java_cup.runtime.Symbol(sym.puntopunto,yyline+1,yycolumn+1,yytext());} 
"=" {return new java_cup.runtime.Symbol(sym.igual,yyline+1,yycolumn+1,yytext());}
"+" {return new java_cup.runtime.Symbol(sym.mas,yyline+1,yycolumn+1,yytext());}
":" {return new java_cup.runtime.Symbol(sym.dospuntos,yyline+1,yycolumn+1,yytext());}
":=" {return new java_cup.runtime.Symbol(sym.asignacion,yyline+1,yycolumn+1,yytext());}
"<" {return new java_cup.runtime.Symbol(sym.mayor,yyline+1,yycolumn+1,yytext());}
">" {return new java_cup.runtime.Symbol(sym.menor,yyline+1,yycolumn+1,yytext());}
"<=" {return new java_cup.runtime.Symbol(sym.menorigual,yyline+1,yycolumn+1,yytext());}
">=" {return new java_cup.runtime.Symbol(sym.mayorigual,yyline+1,yycolumn+1,yytext());}
"<>" {return new java_cup.runtime.Symbol(sym.distinto,yyline+1,yycolumn+1,yytext());}
"-" {return new java_cup.runtime.Symbol(sym.menos,yyline+1,yycolumn+1,yytext());}
"*" {return new java_cup.runtime.Symbol(sym.por,yyline+1,yycolumn+1,yytext());}
"(" {return new java_cup.runtime.Symbol(sym.parentesisizq,yyline+1,yycolumn+1,yytext());}
")" {return new java_cup.runtime.Symbol(sym.parentesisder,yyline+1,yycolumn+1,yytext());}
"[" {return new java_cup.runtime.Symbol(sym.corcheteizq,yyline+1,yycolumn+1,yytext());}
"]" {return new java_cup.runtime.Symbol(sym.corcheteder,yyline+1,yycolumn+1,yytext());}
(" "|\n|\t|\r)+	{}
{identificador} {return new java_cup.runtime.Symbol(sym.id,yyline+1,yycolumn+1,yytext());}
. {System.out.println("Lexical Error: \"" + yytext()+ "\" line: "+(yyline+1)+" column: "+(yycolumn+1)+", do not belong to the alphabet");}


<constLit>{
		"''" {litconst+="'";}
		"'" {yybegin(YYINITIAL); return new java_cup.runtime.Symbol(sym.constlit,yyline+1,yycolumn+1,litconst);}
		[^'] {litconst+=yytext();}
		
	  }

<comentLlav>{
		[^}] {}
		"}" {yybegin(YYINITIAL);}
	    }

<comentPar>{
		"*" {yybegin(comentParFin);}
		[^*] {}		
	   }

<comentParFin>{
		")" {yybegin(YYINITIAL);}
		[^)] {yybegin(comentPar);}		
	   }