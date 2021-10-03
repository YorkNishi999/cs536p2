////////////////////////////////////////////////////////////////////////////////
//
// Title: The test cases for SymTable (P1.java)
// Files: P2.java, b.jlex, Makefile, ErrMsg.java, sym.java
// Test files: validReserved.in, invalidReserved.in, invalidReserved_expect.in,        
//             validIdent.in, invalidIdent.in, invalidIdent_expect.in,
//             validIntegerLiteral.in, invalidIntegerLiteral.in, 
//               invalidIntegerLiteral_expect.in
//             validStringLiteral.in, invalidStringLiteral.in, 
//               invalidStringLiteral_expect.in
//             validSymbol.in, invalidSymbol.in, invalidSymbol_expect.in  
//             validComment.in, validComment_expect.in, 
//               invalidComment.in, invalidComment_expect.in
//             validWhitespace.in, validWhitespace_expect.in
// Folders: deps
// Semester: CS536 Intro to PLs and compilers
// Author: Yohei Nishimura
// Email: ynishimura@wisc.edu
// CS Login: yohei_nishimura111
// Lecturer's Name: Aws Albarghouthi
//
//////////////////////////////////80 letters////////////////////////////////////

/**
 * This class takes a token from the *.in file and creates the *.out file.
 * First, it calls the scanner and stores the token read by the scanner 
 * in my_token of the Symbol class. Then, it checks my_token for a match 
 * with the token in the sym.java file until my_token becomes EOF, 
 * and if it matches, it prints out the necessary information.
 * 
 * @param my_scanner is a instance of Yylex (Scanner)
 * @param my_token is a instance of Symbol 
 * @throws IOException is an error that occurs when the "*.in" files does not exist.
 * For details on each method, please refer to the documentation of each class.
 */


import java.util.*;
import java.io.*;
import java_cup.runtime.*;

public class P2 {
    public static void main(String[] args) throws IOException {
        // exception may be thrown by yylex
        // test all tokens
        String [] inFiles = 
          {
            "validReserved.in", "validIntegerLiteral.in", "validIdent.in", 
            "validStringLiteral.in", "validSymbol.in", "validComment.in", 
            "validWhitespace.in", "invalidIntegerLiteral.in", 
            "invalidStringLiteral.in", "invalidIdent.in","invalidComment.in", 
            "invalidSymbol.in", "invalidReserved.in"
          };
        String [] outFiles = 
          {
            "validReserved.out", "validIntegerLiteral.out", "validIdent.out", 
            "validStringLiteral.out", "validSymbol.out", "validComment.out", 
            "validWhitespace.out","invalidIntegerLiteral.out",
            "invalidStringLiteral.out","invalidIdent.out", "invalidComment.out", 
            "invalidSymbol.out", "invalidReserved.out"
          };
        
        for (int i = 0; i < inFiles.length; ++i) 
        {
          testAllTokens(inFiles[i], outFiles[i]);
          CharNum.num = 1;
        }
    }

    /**
     * testAllTokens
     *
     */
    private static void testAllTokens(String infileName, String outfileName)
                        throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader(infileName);
            outFile = new PrintWriter(new FileWriter(outfileName));
        } catch (FileNotFoundException ex) {
            System.err.println("File " + infileName +" not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println(outfileName + " cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex my_scanner = new Yylex(inFile);
        Symbol my_token = my_scanner.next_token();
        while (my_token.sym != sym.EOF) {
            switch (my_token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
            case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("tru"); 
                break;
            case sym.FALSE:
                  outFile.println("fls"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.RECEIVE:
                outFile.println("receive"); 
                break;
            case sym.PRINT:
                outFile.println("print");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("ret");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)my_token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)my_token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)my_token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
            case sym.ASSIGN:
                outFile.println("=");
                break;
            default:
                outFile.println("UNKNOWN TOKEN");
            } // end switch

            my_token = my_scanner.next_token();
        } // end while
        outFile.close();
    }
}
