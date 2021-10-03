###
# This Makefile can be used to make a scanner for the b language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
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
	diff validComment_expect.in validComment.out
	diff validWhitespace_expect.in validWhitespace.out
	diff invalidIntegerLiteral_expect.in invalidIntegerLiteral.out
	diff invalidStringLiteral_expect.in invalidStringLiteral.out
	diff invalidIdent_expect.in invalidIdent.out
	diff invalidComment_expect.in invalidComment.out
	diff invalidSymbol_expect.in invalidSymbol.out
	diff invalidReserved_expect.in invalidReserved.out


###
# clean up
###

clean:
	rm -f *~ *.class b.jlex.java

cleantest:
	rm -f *.out
