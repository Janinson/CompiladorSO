package org.example;

import java.util.ArrayList;
import java.util.List;

public class NodoAST {

    TipoToken tipo;
    String valor;
    List<NodoAST> hijos;

    public NodoAST(TipoToken tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(NodoAST hijo) {
        hijos.add(hijo);
    }

    @Override
    public String toString() {
        return imprimirArbol(0);
    }

    private String imprimirArbol(int nivel) {
        StringBuilder resultado = new StringBuilder("  ".repeat(nivel) + "(" + tipo + ": " + valor);
        for (NodoAST hijo : hijos) {
            resultado.append("\n").append(hijo.imprimirArbol(nivel + 1));
        }
        resultado.append(")");
        return resultado.toString();
    }

}
