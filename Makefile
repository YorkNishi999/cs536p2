###
# This Makefile can be used to make a scanner for the b language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = ./deps:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: b.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) b.jlex.java

b.jlex.java: b.jlex sym.class
	java -cp $(CP) JLex.Main b.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	java -cp $(CP) P2 
	diff validReserved.in validReserved.out
	diff validIntegerLiteral.in validIntegerLiteral.out
	diff validIdent.in validIdent.out
	diff validStringLiteral.in validStringLiteral.out
	diff validSymbol.in validSymbol.out
	diff validComment.in validComment.out
	diff validWhitespace_expect.out validWhitespace.out
	diff invalidIntegerLiteral_expect.out invalidIntegerLiteral.out
	diff invalidStringLiteral_expect.out invalidStringLiteral.out
	diff invalidIdent_expect.out invalidIdent.out
	diff invalidComment_expect.out invalidComment.out
	diff invalidSymbol_expect.out invalidSymbol.out



###
# clean up
###

clean:
	rm -f *~ *.class b.jlex.java

cleantest:
	rm -f allTokens.out
	rm -f validComment.out
	rm -f validIdent.out
	rm -f validIntegerLiteral.out
	rm -f validReserved.out
	rm -f validSymbol.out
	rm -f validWhitespace.out
	rm -f invalidIntegerLiteral.out
	rm -f invalidStringLiteral.out
