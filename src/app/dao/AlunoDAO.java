package app.dao;

import app.Aluno;
import app.util.DoublyLinkedList;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AlunoDAO {

    // Le alunos.txt e retorna a lista dupla
    public DoublyLinkedList<Aluno> buscaTodos(ServletContext ctx) {
        DoublyLinkedList<Aluno> lista = new DoublyLinkedList<>();
        String realPath = ctx.getRealPath("/resources/Alunos.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(realPath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(";");
                int matricula = Integer.parseInt(campos[0]);
                String nome   = campos[1];
                int idade     = Integer.parseInt(campos[2]);
                lista.add(new Aluno(matricula, nome, idade));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
