package app;

// Nó para lista duplamente encadeada de Disciplina
public class NoDisciplina {
    public Disciplina dado;
    public NoDisciplina proximo;
    public NoDisciplina anterior;

    public NoDisciplina(Disciplina dado) {
        this.dado = dado;
    }
}
