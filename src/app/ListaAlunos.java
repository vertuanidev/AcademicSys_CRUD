package app;

// Lista duplamente encadeada de Aluno para gerenciar objetos Aluno
public class ListaAlunos {
    public NoAluno cabeca; 
    private int tamanho;

    public ListaAlunos() {
        cabeca = new NoAluno(null);
        cabeca.proximo = cabeca;
        cabeca.anterior = cabeca;
        tamanho = 0;
    }

    public void adicionar(Aluno aluno) {
        NoAluno novo = new NoAluno(aluno);
        NoAluno ultimo = cabeca.anterior;
        ultimo.proximo = novo;
        novo.anterior = ultimo;
        novo.proximo = cabeca;
        cabeca.anterior = novo;
        tamanho++;
    }

    public void listar() {
        System.out.println("\n--- Lista de Alunos ---");
        NoAluno atual = cabeca.proximo;
        while (atual != cabeca) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
