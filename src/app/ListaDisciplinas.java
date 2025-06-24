package app;

// Lista duplamente encadeada para Disciplina, armazena todas as disciplinas do sistema
public class ListaDisciplinas {
    public NoDisciplina cabeca; 
    private int tamanho;

    public ListaDisciplinas() {
        cabeca = new NoDisciplina(null);
        cabeca.proximo = cabeca;
        cabeca.anterior = cabeca;
        tamanho = 0;
    }

    public void adicionar(Disciplina d) {
        NoDisciplina novo = new NoDisciplina(d);
        NoDisciplina ultimo = cabeca.anterior;
        ultimo.proximo = novo;
        novo.anterior = ultimo;
        novo.proximo = cabeca;
        cabeca.anterior = novo;
        tamanho++;
    }

    public void listar() {
        System.out.println("\n--- Lista de Disciplinas ---");
        NoDisciplina atual = cabeca.proximo;
        while (atual != cabeca) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
