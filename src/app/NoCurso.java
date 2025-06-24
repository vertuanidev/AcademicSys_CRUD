package app;

// Nó para lista duplamente encadeada de Curso (inscrições)
public class NoCurso {
    public Curso dado;
    public NoCurso proximo;
    public NoCurso anterior;

    public NoCurso(Curso dado) {
        this.dado = dado;
    }
}
