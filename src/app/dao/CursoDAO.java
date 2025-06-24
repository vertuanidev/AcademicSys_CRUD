package app.dao;

import app.Curso;
import app.util.DoublyLinkedList;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CursoDAO {

    // Le cursos.txt e retorna a lista duplamente encadeada
    public DoublyLinkedList<Curso> buscaTodos(ServletContext ctx) {
        DoublyLinkedList<Curso> lista = new DoublyLinkedList<>();
        String realPath = ctx.getRealPath("/resources/Cursos.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(realPath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(";");
                int matricula        = Integer.parseInt(campos[0]);
                int codigoDisciplina = Integer.parseInt(campos[1]);
                float nota1          = Float.parseFloat(campos[2]);
                float nota2          = Float.parseFloat(campos[3]);
                lista.add(new Curso(matricula, codigoDisciplina, nota1, nota2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
