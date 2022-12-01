grammar Expr;

@header{
	package antlr.tools;
}

prog: expression + EOF # Program
	;
	
expression: select
	| project 
	| natural
	| cartesian
	;
	
select: SELECT '[' PREDICATE ']' '(' relation ')' # Selection;	

project: PROJECT '[' ATRIBUTE (',' ATRIBUTE)* ']' '(' relation ')' # Projection;	
	
natural: NATURAL '(' relation ',' relation ')' # NaturalJoin;
 
cartesian: CARTESIAN '(' relation ',' relation ')' # CartesianProduct;

relation: RELATION #simple
		  | expression #nested;
		
SELECT: S E L E C T;		
PROJECT: P R O J E C T;		
NATURAL: N A T U R A L [_] J O I N;
CARTESIAN: C A R T E S I A N [_] P R O D U C T;		

ATRIBUTE: '\''.*?'\'';		
PREDICATE: [a-zA-Z] ([a-z] | [A-Z] | [0-9] | [>])*;
RELATION: [a-zA-Z] [a-zA-Z0-9_]*;
WS: [\t\r\n]+ -> skip;		
		
fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');	
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');			