package app.dao;

import app.Disciplina;
import app.util.DoublyLinkedList;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisciplinaDAO {

    // Le disciplinas.txt e retorna lista duplamente encadeada
    public DoublyLinkedList<Disciplina> buscaTodos(ServletContext ctx) {
        DoublyLinkedList<Disciplina> lista = new DoublyLinkedList<>();
        String realPath = ctx.getRealPath("/resources/Disciplinas.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(realPath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] campos = linha.split(";");
                int codigo    = Integer.parseInt(campos[0]);
                String nome   = campos[1];
                float notaMin = Float.parseFloat(campos[2]);
                lista.add(new Disciplina(codigo, nome, notaMin));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
