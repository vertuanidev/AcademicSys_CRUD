package app;

public class Disciplina {
    private int codigo;
    private String nome;
    private float notaMinima;

    public Disciplina(int codigo, String nome, float notaMinima) {
        this.codigo = codigo;
        this.nome = nome;
        this.notaMinima = notaMinima;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public float getNotaMinima() {
        return notaMinima;
    }

    @Override
    public String toString() {
        return codigo + " – " + nome + " (mín. " + notaMinima + ")";
    }
}
