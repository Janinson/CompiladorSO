package org.example;

enum TipoToken {
    SI, SINO, ASIGNACION, VARIABLE, NUMERO, MAS, MENOS, POR, DIVIDIDO, MENOR_IGUAL, PARENTESIS_IZQUIERDO, PARENTESIS_DERECHO, DESCONOCIDO
}

class Token {
    TipoToken tipo;
    String valor;

    public Token(TipoToken tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "[" + tipo + ": " + valor + "]";
    }
}
