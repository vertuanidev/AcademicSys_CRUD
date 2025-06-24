package app;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ListaAlunos listaAlu = new ListaAlunos();
        ListaDisciplinas listaDis = new ListaDisciplinas();
        ListaCursos listaCur = new ListaCursos();

        carregarAlunos(listaAlu, "Alunos.txt");
        carregarDisciplinas(listaDis, "Disciplinas.txt");
        carregarCursos(listaCur, "Cursos.txt");

        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Listar Alunos");
            System.out.println("2) Listar Disciplinas");
            System.out.println("3) Listar Inscrições");
            System.out.println("4) Resultados de um Aluno");
            System.out.println("5) Resultados por Disciplina");
            System.out.println("0) Sair e salvar");
            System.out.print("Opção: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    listaAlu.listar();
                    break;
                case 2:
                    listaDis.listar();
                    break;
                case 3:
                    listarInscricoesComNomes(listaAlu, listaDis, listaCur);
                    break;
                case 4:
                    resultadosAluno(listaAlu, listaDis, listaCur, sc);
                    break;
                case 5:
                    resultadosDisciplina(listaAlu, listaDis, listaCur, sc);
                    break;
                case 0:
                    salvarAlunos(listaAlu, "Alunos.txt");
                    salvarDisciplinas(listaDis, "Disciplinas.txt");
                    salvarCursos(listaCur, "Cursos.txt");
                    System.out.println("Dados salvos. Encerrando.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opc != 0);

        sc.close();
    }

    // === Listagem de inscrições com nome de aluno e disciplina ===
    private static void listarInscricoesComNomes(
            ListaAlunos listaAlu,
            ListaDisciplinas listaDis,
            ListaCursos listaCur) {

        System.out.println("\n--- Inscrições Cadastradas ---");
        NoCurso noC = listaCur.cabeca.proximo;
        while (noC != listaCur.cabeca) {
            Curso c = noC.dado;
            Aluno a = buscaAluno(listaAlu, c.getMatricula());
            Disciplina d = buscaDisciplina(listaDis, c.getCodigoDisciplina());

            float media = c.getMedia();
            String status = (d != null && media >= d.getNotaMinima()) 
                                ? "APROVADO" : "REPROVADO";

            System.out.printf("%s → %s : N1=%.1f  N2=%.1f  Média=%.1f → %s%n",
                a  != null ? a.getNome() : ("Aluno " + c.getMatricula()),
                d  != null ? d.getNome() : ("Disc " + c.getCodigoDisciplina()),
                c.getNota1(), c.getNota2(), media, status
            );

            noC = noC.proximo;
        }
    }

    // === Carregar e salvar Alunos ===
    private static void carregarAlunos(ListaAlunos lista, String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] f = linha.split(";");
                int m = Integer.parseInt(f[0]);
                String nome = f[1];
                int idade = Integer.parseInt(f[2]);
                lista.adicionar(new Aluno(m, nome, idade));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler Alunos: " + e.getMessage());
        }
    }

    private static void salvarAlunos(ListaAlunos lista, String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            NoAluno n = lista.cabeca.proximo;
            while (n != lista.cabeca) {
                Aluno a = n.dado;
                bw.write(a.getMatricula() + ";" + a.getNome() + ";" + a.getIdade());
                bw.newLine();
                n = n.proximo;
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar Alunos: " + e.getMessage());
        }
    }

    // === Carregar e salvar Disciplinas ===
    private static void carregarDisciplinas(ListaDisciplinas lista, String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] f = linha.split(";");
                int cod = Integer.parseInt(f[0]);
                String nome = f[1];
                float notaMin = Float.parseFloat(f[2]);
                lista.adicionar(new Disciplina(cod, nome, notaMin));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler Disciplinas: " + e.getMessage());
        }
    }

    private static void salvarDisciplinas(ListaDisciplinas lista, String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            NoDisciplina n = lista.cabeca.proximo;
            while (n != lista.cabeca) {
                Disciplina d = n.dado;
                bw.write(d.getCodigo() + ";" + d.getNome() + ";" + d.getNotaMinima());
                bw.newLine();
                n = n.proximo;
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar Disciplinas: " + e.getMessage());
        }
    }

    // === Carregar e salvar Cursos (inscrições) ===
    private static void carregarCursos(ListaCursos lista, String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] f = linha.split(";");
                int m = Integer.parseInt(f[0]);
                int cod = Integer.parseInt(f[1]);
                float n1 = Float.parseFloat(f[2]);
                float n2 = Float.parseFloat(f[3]);
                lista.adicionar(new Curso(m, cod, n1, n2));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler Cursos: " + e.getMessage());
        }
    }

    private static void salvarCursos(ListaCursos lista, String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            NoCurso n = lista.cabeca.proximo;
            while (n != lista.cabeca) {
                Curso c = n.dado;
                bw.write(c.getMatricula() + ";" + c.getCodigoDisciplina() + ";" + c.getNota1() + ";" + c.getNota2());
                bw.newLine();
                n = n.proximo;
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar Cursos: " + e.getMessage());
        }
    }

    // === Relatórios de Resultado ===
    private static void resultadosAluno(
            ListaAlunos listaAlu,
            ListaDisciplinas listaDis,
            ListaCursos listaCur,
            Scanner sc) {

        System.out.print("Digite matrícula do aluno: ");
        int mat = Integer.parseInt(sc.nextLine());

        Aluno alvo = buscaAluno(listaAlu, mat);
        if (alvo == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("\nResultados de " + alvo.getNome() + ":");
        NoCurso nc = listaCur.cabeca.proximo;
        while (nc != listaCur.cabeca) {
            Curso c = nc.dado;
            if (c.getMatricula() == mat) {
                Disciplina d = buscaDisciplina(listaDis, c.getCodigoDisciplina());
                float media = c.getMedia();
                String status = (d != null && media >= d.getNotaMinima()) ? "APROVADO" : "REPROVADO";
                String nomeDisc = (d != null ? d.getNome() : "Disc " + c.getCodigoDisciplina());
                System.out.printf("  %s: N1=%.1f N2=%.1f Média=%.1f → %s%n",
                        nomeDisc, c.getNota1(), c.getNota2(), media, status);
            }
            nc = nc.proximo;
        }
    }

    private static void resultadosDisciplina(
            ListaAlunos listaAlu,
            ListaDisciplinas listaDis,
            ListaCursos listaCur,
            Scanner sc) {

        System.out.print("Digite código da disciplina: ");
        int cod = Integer.parseInt(sc.nextLine());

        Disciplina alvo = buscaDisciplina(listaDis, cod);
        if (alvo == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.println("\nResultados em " + alvo.getNome() + ":");
        NoCurso nc = listaCur.cabeca.proximo;
        while (nc != listaCur.cabeca) {
            Curso c = nc.dado;
            if (c.getCodigoDisciplina() == cod) {
                Aluno a = buscaAluno(listaAlu, c.getMatricula());
                float media = c.getMedia();
                String status = media >= alvo.getNotaMinima() ? "APROVADO" : "REPROVADO";
                String nomeAlu = (a != null ? a.getNome() : "Aluno " + c.getMatricula());
                System.out.printf("  %s: N1=%.1f N2=%.1f Média=%.1f → %s%n",
                        nomeAlu, c.getNota1(), c.getNota2(), media, status);
            }
            nc = nc.proximo;
        }
    }

    // === Métodos de busca auxiliares ===
    private static Aluno buscaAluno(ListaAlunos lista, int matricula) {
        NoAluno no = lista.cabeca.proximo;
        while (no != lista.cabeca) {
            if (no.dado.getMatricula() == matricula) return no.dado;
            no = no.proximo;
        }
        return null;
    }

   
    private static Disciplina buscaDisciplina(ListaDisciplinas lista, int codigo) {
        NoDisciplina no = lista.cabeca.proximo;
        while (no != lista.cabeca) {
            if (no.dado.getCodigo() == codigo) return no.dado;
            no = no.proximo;
        }
        return null;
    }
}