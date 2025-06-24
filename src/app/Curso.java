package app;

public class Curso {
    private int matricula;        // matrícula do aluno
    private int codigoDisciplina; // código da disciplina
    private float nota1;
    private float nota2;

    public Curso(int matricula, int codigoDisciplina, float nota1, float nota2) {
        this.matricula = matricula;
        this.codigoDisciplina = codigoDisciplina;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public int getMatricula() {
        return matricula;
    }

    public int getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public float getNota1() {
        return nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public float getMedia() {
        return (nota1 + nota2) / 2f;
    }

    @Override
    public String toString() {
        // para exibir no listarInscricoes()
        return matricula + ";" + codigoDisciplina + ";" + nota1 + ";" + nota2;
    }
}
