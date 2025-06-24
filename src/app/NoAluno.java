package app;

// Nó para lista duplamente encadeada de Aluno
public class NoAluno {
    public Aluno dado;
    public NoAluno proximo;
    public NoAluno anterior;

    public NoAluno(Aluno dado) {
        this.dado = dado;
    }
}
