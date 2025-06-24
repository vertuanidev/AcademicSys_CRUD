package app.web;

import app.Aluno;
import app.Curso;
import app.Disciplina;
import app.model.Result;
import app.util.DoublyLinkedList;
import app.util.PersistenceUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        ServletContext ctx = getServletContext();

        DoublyLinkedList<Aluno> alunos =
            (DoublyLinkedList<Aluno>) ctx.getAttribute("alunosList");
        DoublyLinkedList<Curso> cursos =
            (DoublyLinkedList<Curso>) ctx.getAttribute("cursosList");
        DoublyLinkedList<Disciplina> disciplinas =
            (DoublyLinkedList<Disciplina>) ctx.getAttribute("disciplinasList");

        // BUSCAR
        if ("buscar".equals(action)) {
            req.setAttribute("listaAlunos", alunos);
            req.getRequestDispatcher("/jsp/aluno/busca.jsp").forward(req, resp);
            return;
        }
        // NOVO
        if ("novo".equals(action)) {
            req.getRequestDispatcher("/jsp/aluno/form.jsp").forward(req, resp);
            return;
        }
        // EDITAR
        if ("editar".equals(action)) {
            int mat = Integer.parseInt(req.getParameter("matricula"));
            Aluno alvo = null;
            for (Aluno a : alunos) {
                if (a.getMatricula() == mat) { alvo = a; break; }
            }
            req.setAttribute("aluno", alvo);
            req.getRequestDispatcher("/jsp/aluno/form.jsp").forward(req, resp);
            return;
        }
        // DELETAR
        if ("deletar".equals(action)) {
            int mat = Integer.parseInt(req.getParameter("matricula"));
            for (Aluno a : alunos) {
                if (a.getMatricula() == mat) {
                    alunos.remove(a);
                    break;
                }
            }
            PersistenceUtil.saveList(ctx, alunos,
                a -> a.getMatricula()+";"+a.getNome()+";"+a.getIdade(),
                "/resources/Alunos.txt");
            resp.sendRedirect(req.getContextPath() + "/aluno");
            return;
        }
        // RESULTADOS
        if ("resultados".equals(action)) {
            int mat = Integer.parseInt(req.getParameter("matricula"));
            DoublyLinkedList<Result> resultados = new DoublyLinkedList<>();
            for (Curso c : cursos) {
                if (c.getMatricula() == mat) {
                    Disciplina dObj = null;
                    for (Disciplina d : disciplinas) {
                        if (d.getCodigo() == c.getCodigoDisciplina()) {
                            dObj = d; break;
                        }
                    }
                    if (dObj != null) {
                        float media = (c.getNota1()+c.getNota2())/2f;
                        boolean aprovado = media >= dObj.getNotaMinima();
                        resultados.add(new Result(dObj.getNome(), media, aprovado));
                    }
                }
            }
            req.setAttribute("resultados", resultados);
            req.getRequestDispatcher("/jsp/aluno/resultados.jsp").forward(req, resp);
            return;
        }
        // LISTAR
        req.setAttribute("lista", alunos);
        req.getRequestDispatcher("/jsp/aluno/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        int mat = Integer.parseInt(req.getParameter("matricula"));
        String nome = req.getParameter("nome");
        int idade = Integer.parseInt(req.getParameter("idade"));
        ServletContext ctx = getServletContext();

        DoublyLinkedList<Aluno> alunos =
            (DoublyLinkedList<Aluno>) ctx.getAttribute("alunosList");
        if ("editar".equals(action)) {
            for (Aluno a : alunos) {
                if (a.getMatricula() == mat) { alunos.remove(a); break; }
            }
        }
        alunos.add(new Aluno(mat, nome, idade));
        PersistenceUtil.saveList(ctx, alunos,
            a -> a.getMatricula()+";"+a.getNome()+";"+a.getIdade(),
            "/resources/Alunos.txt");
        resp.sendRedirect(req.getContextPath() + "/aluno");
    }
}