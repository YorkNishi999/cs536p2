import java.util.*;
import java.io.*;
import java_cup.runtime.*;



/**
 * This program is to be used to test the b scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens).
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        String [] inFiles = 
          {"validReserved.in", "validIntegerLiteral.in", "validIdent.in", "validStringLiteral.in", "validSymbol.in"};
        String [] outFiles = 
          {"validReserved.out", "validIntegerLiteral.out", "validIdent.out", "validStringLiteral.out", "validSymbol.out"};
        for (int i = 0; i < inFiles.length; ++i) {
          testAllTokens(inFiles[i], outFiles[i]);
          CharNum.num = 1;
        }
        // ADD CALLS TO OTHER TEST METHODS HERE
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
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
