package org.example;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int indiceTokenActual;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.indiceTokenActual = 0;
    }

    public NodoAST parse() {
        return parsePrograma();
    }

    private NodoAST parsePrograma() {
        NodoAST nodoPrograma = new NodoAST(TipoToken.DESCONOCIDO, "programa");
        while (indiceTokenActual < tokens.size()) {
            NodoAST nodoSentencia = parseSentencia();
            if (nodoSentencia != null) {
                nodoPrograma.agregarHijo(nodoSentencia);
            }
        }
        return nodoPrograma;
    }

    private NodoAST parseSentencia() {
        Token tokenActual = tokens.get(indiceTokenActual);
        if (tokenActual.tipo == TipoToken.VARIABLE) {
            NodoAST nodoVariable = new NodoAST(TipoToken.VARIABLE, tokenActual.valor);
            indiceTokenActual++;
            if (indiceTokenActual < tokens.size() && tokens.get(indiceTokenActual).tipo == TipoToken.ASIGNACION) {
                indiceTokenActual++; // Consumir el token '='
                NodoAST nodoAsignacion = new NodoAST(TipoToken.ASIGNACION, "=");
                nodoAsignacion.agregarHijo(nodoVariable);
                NodoAST nodoExpresion = parseExpresion();
                if (nodoExpresion != null) {
                    nodoAsignacion.agregarHijo(nodoExpresion);
                    return nodoAsignacion;
                }
            }
        }
        return null;
    }

    private NodoAST parseExpresion() {
        NodoAST nodoFactor = parseFactor();
        while (indiceTokenActual < tokens.size()
                && (tokens.get(indiceTokenActual).tipo == TipoToken.MAS || tokens.get(indiceTokenActual).tipo == TipoToken.MENOS)) {
            Token operadorToken = tokens.get(indiceTokenActual);
            indiceTokenActual++;
            NodoAST nodoNuevoFactor = parseFactor();
            NodoAST nodoOperacion = new NodoAST(operadorToken.tipo, operadorToken.valor);
            nodoOperacion.agregarHijo(nodoFactor);
            nodoOperacion.agregarHijo(nodoNuevoFactor);
            nodoFactor = nodoOperacion;
        }
        return nodoFactor;
    }

    private NodoAST parseFactor() {
        Token tokenActual = tokens.get(indiceTokenActual);
        if (tokenActual.tipo == TipoToken.NUMERO || tokenActual.tipo == TipoToken.VARIABLE) {
            indiceTokenActual++;
            return new NodoAST(tokenActual.tipo, tokenActual.valor);
        } else if (tokenActual.tipo == TipoToken.MAS || tokenActual.tipo == TipoToken.MENOS) {
            indiceTokenActual++;
            NodoAST nodoFactor = parseFactor();
            NodoAST nodoUnario = new NodoAST(tokenActual.tipo, tokenActual.valor);
            nodoUnario.agregarHijo(nodoFactor);
            return nodoUnario;
        } else if (tokenActual.tipo == TipoToken.PARENTESIS_IZQUIERDO) {
            indiceTokenActual++;
            NodoAST nodoExpresion = parseExpresion();
            if (indiceTokenActual < tokens.size() && tokens.get(indiceTokenActual).tipo == TipoToken.PARENTESIS_DERECHO) {
                indiceTokenActual++;
                return nodoExpresion;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
