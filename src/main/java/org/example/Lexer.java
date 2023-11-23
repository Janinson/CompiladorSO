package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

    private final Map<String, TipoToken> palabrasClave;

    public Lexer() {
        palabrasClave = new HashMap<>();
        palabrasClave.put("si", TipoToken.SI);
        palabrasClave.put("sino", TipoToken.SINO);
        palabrasClave.put("<=", TipoToken.MENOR_IGUAL);
    }

    public List<Token> tokenize(String entrada) {
        List<Token> tokens = new ArrayList<>();
        String[] palabras = entrada.split("\\s+");
        for (String palabra : palabras) {
            if (palabrasClave.containsKey(palabra)) {
                tokens.add(new Token(palabrasClave.get(palabra), palabra));
            } else if (palabra.matches("[a-zA-Z]+")) {
                tokens.add(new Token(TipoToken.VARIABLE, palabra));
            } else if (palabra.matches("\\d+")) {
                tokens.add(new Token(TipoToken.NUMERO, palabra));
            } else if (palabra.equals("+")) {
                tokens.add(new Token(TipoToken.MAS, palabra));
            } else if (palabra.equals("-")) {
                tokens.add(new Token(TipoToken.MENOS, palabra));
            } else if (palabra.equals("*")) {
                tokens.add(new Token(TipoToken.POR, palabra));
            } else if (palabra.equals("/")) {
                tokens.add(new Token(TipoToken.DIVIDIDO, palabra));
            } else if (palabra.equals("<=")) {
                tokens.add(new Token(TipoToken.MENOR_IGUAL, palabra));
            } else if (palabra.equals("=")) {
                tokens.add(new Token(TipoToken.ASIGNACION, palabra));
            } else {
                for (char c : palabra.toCharArray()) {
                    if (c == '(') {
                        tokens.add(new Token(TipoToken.PARENTESIS_IZQUIERDO, "("));
                    } else if (c == ')') {
                        tokens.add(new Token(TipoToken.PARENTESIS_DERECHO, ")"));
                    } else {
                        tokens.add(new Token(TipoToken.DESCONOCIDO, String.valueOf(c)));
                    }
                }
            }
        }
        return tokens;
    }
}
