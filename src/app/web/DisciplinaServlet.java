package app.web;

import app.Disciplina;
import app.Curso;
import app.Aluno;
import app.model.Result;
import app.util.DoublyLinkedList;
import app.util.PersistenceUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/disciplina")
public class DisciplinaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        ServletContext ctx = getServletContext();

        @SuppressWarnings("unchecked")
        DoublyLinkedList<Disciplina> disciplinas =
            (DoublyLinkedList<Disciplina>) ctx.getAttribute("disciplinasList");
        @SuppressWarnings("unchecked")
        DoublyLinkedList<Curso> cursos =
            (DoublyLinkedList<Curso>) ctx.getAttribute("cursosList");
        @SuppressWarnings("unchecked")
        DoublyLinkedList<Aluno> alunos =
            (DoublyLinkedList<Aluno>) ctx.getAttribute("alunosList");

        // 1) BUSCAR (RESULTADOS POR DISCIPLINA)
        if ("buscar".equals(action)) {
            req.setAttribute("listaDisciplinas", disciplinas);
            req.getRequestDispatcher("/jsp/disciplina/busca.jsp")
               .forward(req, resp);
            return;
        }

        // 2) NOVO
        if ("novo".equals(action)) {
            req.getRequestDispatcher("/jsp/disciplina/form.jsp")
               .forward(req, resp);
            return;
        }

        // 3) EDITAR
        if ("editar".equals(action)) {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            Disciplina alvo = null;
            for (Disciplina d : disciplinas) {
                if (d.getCodigo() == codigo) {
                    alvo = d;
                    break;
                }
            }
            req.setAttribute("disciplina", alvo);
            req.getRequestDispatcher("/jsp/disciplina/form.jsp")
               .forward(req, resp);
            return;
        }

        // 4) DELETAR
        if ("deletar".equals(action)) {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            for (Disciplina d : disciplinas) {
                if (d.getCodigo() == codigo) {
                    disciplinas.remove(d);
                    break;
                }
            }
            PersistenceUtil.saveList(ctx, disciplinas,
                d -> d.getCodigo() + ";" + d.getNome() + ";" + d.getNotaMinima(),
                "/resources/Disciplinas.txt");
            resp.sendRedirect(req.getContextPath() + "/disciplina");
            return;
        }

        // 5) RESULTADOS
        if ("resultados".equals(action)) {
            int codigo = Integer.parseInt(req.getParameter("codigo"));

            // encontra disciplina
            Disciplina disc = null;
            for (Disciplina d : disciplinas) {
                if (d.getCodigo() == codigo) {
                    disc = d;
                    break;
                }
            }
            if (disc == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Disciplina não encontrada");
                return;
            }
            float notaMin = disc.getNotaMinima();

            // monta resultados em DoublyLinkedList
            DoublyLinkedList<Result> resultados = new DoublyLinkedList<>();
            for (Curso c : cursos) {
                if (c.getCodigoDisciplina() == codigo) {
                    for (Aluno a : alunos) {
                        if (a.getMatricula() == c.getMatricula()) {
                            float media = (c.getNota1() + c.getNota2()) / 2f;
                            boolean aprovado = media >= notaMin;
                            resultados.add(new Result(a.getNome(), media, aprovado));
                            break;
                        }
                    }
                }
            }

            req.setAttribute("resultados", resultados);
            req.getRequestDispatcher("/jsp/disciplina/resultados.jsp")
               .forward(req, resp);
            return;
        }

        // 6) LISTAR (default)
        req.setAttribute("lista", disciplinas);
        req.getRequestDispatcher("/jsp/disciplina/list.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        int codigo    = Integer.parseInt(req.getParameter("codigo"));
        String nome   = req.getParameter("nome");
        float notaMin = Float.parseFloat(req.getParameter("notaMinima"));

        ServletContext ctx = getServletContext();

        @SuppressWarnings("unchecked")
        DoublyLinkedList<Disciplina> disciplinas =
            (DoublyLinkedList<Disciplina>) ctx.getAttribute("disciplinasList");

        if ("editar".equals(action)) {
            for (Disciplina d : disciplinas) {
                if (d.getCodigo() == codigo) {
                    disciplinas.remove(d);
                    break;
                }
            }
        }
        disciplinas.add(new Disciplina(codigo, nome, notaMin));

        PersistenceUtil.saveList(ctx, disciplinas,
            d -> d.getCodigo() + ";" + d.getNome() + ";" + d.getNotaMinima(),
            "/resources/Disciplinas.txt");

        resp.sendRedirect(req.getContextPath() + "/disciplina");
    }
}
