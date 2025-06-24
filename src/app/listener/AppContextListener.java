package app.listener;

import app.Aluno;
import app.Curso;
import app.Disciplina;
import app.util.DoublyLinkedList;
import app.dao.AlunoDAO;
import app.dao.CursoDAO;
import app.dao.DisciplinaDAO;
import app.util.PersistenceUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        // Carrega diretamente a lista duplamente encadeada dos DAOs
        DoublyLinkedList<Aluno> alunos =
            new AlunoDAO().buscaTodos(ctx);
        ctx.setAttribute("alunosList", alunos);

        DoublyLinkedList<Disciplina> disciplinas =
            new DisciplinaDAO().buscaTodos(ctx);
        ctx.setAttribute("disciplinasList", disciplinas);

        DoublyLinkedList<Curso> cursos =
            new CursoDAO().buscaTodos(ctx);
        ctx.setAttribute("cursosList", cursos);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        try {
            // persistencia de Alunos
            PersistenceUtil.saveList(ctx,
                (DoublyLinkedList<Aluno>) ctx.getAttribute("alunosList"),
                a -> a.getMatricula() + ";" + a.getNome() + ";" + a.getIdade(),
                "/resources/Alunos.txt");

            // persistencia de Disciplinas
            PersistenceUtil.saveList(ctx,
                (DoublyLinkedList<Disciplina>) ctx.getAttribute("disciplinasList"),
                d -> d.getCodigo() + ";" + d.getNome() + ";" + d.getNotaMinima(),
                "/resources/Disciplinas.txt");

            // persistencia de Cursos
            PersistenceUtil.saveList(ctx,
                (DoublyLinkedList<Curso>) ctx.getAttribute("cursosList"),
                c -> c.getMatricula() + ";" +
                     c.getCodigoDisciplina() + ";" +
                     c.getNota1() + ";" + c.getNota2(),
                "/resources/Cursos.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
