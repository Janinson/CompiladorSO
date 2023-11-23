package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String entrada = "si x <= 5 entonces x = ( 10 + 5 ) * 2 sino x = x - 1";
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(entrada);

        System.out.println("Tokens:");
        for (Token token : tokens) {
            System.out.println(token);
        }

        Parser parser = new Parser(tokens);
        NodoAST ast = parser.parse();

        System.out.println("Arbol de Sintaxis Abstracta (AST):");
        System.out.println(ast);
    }
}