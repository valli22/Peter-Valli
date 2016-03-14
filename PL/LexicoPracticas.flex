import java_cup.runtime.*

%%

%unicode
%cup
%class PracticaLexico

identificador = [_a-zA-Z][a-zA-Z0-9_]*
constNumInt = [+-]?[0-9]+
constNumReal = [+-]?[0-9]+"."[0-9]+
constNumIntH = "$"[+-]?[A-F0-9]+
constNumRealH = "$"[+-]?[A-F0-9]+"."[A-F0-9]+
finParentesis = "*"")"

%xstate constLit
%xstate comentPar
%xstate comentLlav

%%


"'" {yybegin(constLit);}
"{" {yybegin(comentLlav);}
"(*" {yybegin(comentPar);}

"REAL" {return new java_cup.runtime.Symbol(sym.real);}
"INTEGER" {return new java_cup.runtime.Symbol(sym.int);}
"CHARACTER" {return new java_cup.runtime.Symbol(sym.char);}
"program" {return new java_cup.runtime.Symbol(sym.program);}
"begin" {return new java_cup.runtime.Symbol(sym.begin);}
"end" {return new java_cup.runtime.Symbol(sym.end);}
"const" {return new java_cup.runtime.Symbol(sym.const);}
"var" {return new java_cup.runtime.Symbol(sym.var);}
"procedure" {return new java_cup.runtime.Symbol(sym.procedure);}
"function" {return new java_cup.runtime.Symbol(sym.function);}
"div" {return new java_cup.runtime.Symbol(sym.div);}
"mod" {return new java_cup.runtime.Symbol(sym.mod);}
"or" {return new java_cup.runtime.Symbol(sym.or);}
"and" {return new java_cup.runtime.Symbol(sym.and);}
"not" {return new java_cup.runtime.Symbol(sym.not);}
"type" {return new java_cup.runtime.Symbol(sym.type);}
"array" {return new java_cup.runtime.Symbol(sym.array);}
"of" {return new java_cup.runtime.Symbol(sym.of);}
"record" {return new java_cup.runtime.Symbol(sym.record);}
"if" {return new java_cup.runtime.Symbol(sym.if);}
"then" {return new java_cup.runtime.Symbol(sym.then);}
"else" {return new java_cup.runtime.Symbol(sym.else);}
"while" {return new java_cup.runtime.Symbol(sym.while);}
"do" {return new java_cup.runtime.Symbol(sym.do);}
"for" {return new java_cup.runtime.Symbol(sym.for);}
"to" {return new java_cup.runtime.Symbol(sym.to);}
"case" {return new java_cup.runtime.Symbol(sym.case);}
"," {return new java_cup.runtime.Symbol(sym.coma);}
";" {return new java_cup.runtime.Symbol(sym.puntocoma);}
"." {return new java_cup.runtime.Symbol(sym.punto);}
".." {return new java_cup.runtime.Symbol(sym.puntopunto);} 
"=" {return new java_cup.runtime.Symbol(sym.igual);}
"+" {return new java_cup.runtime.Symbol(sym.mas);}
":" {return new java_cup.runtime.Symbol(sym.dospuntos);}
":=" {return new java_cup.runtime.Symbol(sym.asignacion);}
"<" {return new java_cup.runtime.Symbol(sym.mayor);}
">" {return new java_cup.runtime.Symbol(sym.menor);}
"<=" {return new java_cup.runtime.Symbol(sym.menorigual);}
">=" {return new java_cup.runtime.Symbol(sym.mayorigual);}
"<>" {return new java_cup.runtime.Symbol(sym.distinto);}
"-" {return new java_cup.runtime.Symbol(sym.menos);}
"*" {return new java_cup.runtime.Symbol(sym.por);}
"(" {return new java_cup.runtime.Symbol(sym.parentesisizq);}
")" {return new java_cup.runtime.Symbol(sym.parentesisder);}
"[" {return new java_cup.runtime.Symbol(sym.corcheteizq);}
"]" {return new java_cup.runtime.Symbol(sym.corcheteder);}

{identificador} {return new java_cup.runtime.Symbol(sym.id);}
{constNumInt} {return new java_cup.runtime.Symbol(sym.constnumint);}
{constNumReal} {return new java_cup.runtime.Symbol(sym.constnumreal);}
{constNumIntH} {return new java_cup.runtime.Symbol(sym.constnuminth);}
{constNumRealH} {return new java_cup.runtime.Symbol(sym.constnumrealh);}

<constLit>{
		"''" {System.out.print("'");}
		"'" {yybegin(YYINITIAL); return new java_cup.runtime.Symbol(sym.constlit);}
		[^'] {}
		
	  }

<comentLlav>{
		[^}] {System.out.print(yytext());}
		"}" {yybegin(YYINITIAL);}
	    }

<comentPar>{
		{finParentesis} {yybegin(YYINITIAL);}
		[^finParentesis] {System.out.print(yytext());}		
	   }