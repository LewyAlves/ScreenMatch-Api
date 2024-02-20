package br.com.alura.screenMatch.model;

public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama");

    private String categoriaOMDB;

    Categoria(String categoriaOMDB){
        this.categoriaOMDB = categoriaOMDB;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOMDB.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
