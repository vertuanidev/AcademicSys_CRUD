package app;

// Lista duplamente encadeada para objetos Curso(inscrições) para ordenar e percorrer as inscrições
public class ListaCursos {
    public NoCurso cabeca;  
    private int tamanho;

    public ListaCursos() {
        cabeca = new NoCurso(null);
        cabeca.proximo = cabeca;
        cabeca.anterior = cabeca;
        tamanho = 0;
    }

    public void adicionar(Curso c) {
        NoCurso novo = new NoCurso(c);
        NoCurso ultimo = cabeca.anterior;
        ultimo.proximo = novo;
        novo.anterior = ultimo;
        novo.proximo = cabeca;
        cabeca.anterior = novo;
        tamanho++;
    }

    public void listar() {
        System.out.println("\n--- Lista de Inscrições (Cursos) ---");
        NoCurso atual = cabeca.proximo;
        while (atual != cabeca) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
