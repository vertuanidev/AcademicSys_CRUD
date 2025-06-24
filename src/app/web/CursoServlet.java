package app.web;

import app.Curso;
import app.util.DoublyLinkedList;
import app.util.PersistenceUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/curso")
public class CursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        ServletContext ctx = getServletContext();

        @SuppressWarnings("unchecked")
        DoublyLinkedList<Curso> lista =
            (DoublyLinkedList<Curso>) ctx.getAttribute("cursosList");

        // 1) NOVO
        if ("novo".equals(action)) {
            req.getRequestDispatcher("/jsp/curso/form.jsp")
               .forward(req, resp);
            return;
        }

        // 2) EDITAR
        if ("editar".equals(action)) {
            int mat = Integer.parseInt(req.getParameter("matricula"));
            int cod = Integer.parseInt(req.getParameter("codigoDisciplina"));
            Curso alvo = null;
            for (Curso c : lista) {
                if (c.getMatricula() == mat && c.getCodigoDisciplina() == cod) {
                    alvo = c;
                    break;
                }
            }
            req.setAttribute("curso", alvo);
            req.getRequestDispatcher("/jsp/curso/form.jsp")
               .forward(req, resp);
            return;
        }

        // 3) DELETAR
        if ("deletar".equals(action)) {
            int mat = Integer.parseInt(req.getParameter("matricula"));
            int cod = Integer.parseInt(req.getParameter("codigoDisciplina"));
            for (Curso c : lista) {
                if (c.getMatricula() == mat && c.getCodigoDisciplina() == cod) {
                    lista.remove(c);
                    break;
                }
            }
            // persiste no arquivo imediatamente
            PersistenceUtil.saveList(ctx, lista,
                c -> c.getMatricula() + ";" +
                     c.getCodigoDisciplina() + ";" +
                     c.getNota1() + ";" +
                     c.getNota2(),
                "/resources/Cursos.txt");
            resp.sendRedirect(req.getContextPath() + "/curso");
            return;
        }

        // 4) LISTAR (padrão)
        req.setAttribute("lista", lista);
        req.getRequestDispatcher("/jsp/curso/list.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        int mat = Integer.parseInt(req.getParameter("matricula"));
        int cod = Integer.parseInt(req.getParameter("codigoDisciplina"));
        float n1 = Float.parseFloat(req.getParameter("nota1"));
        float n2 = Float.parseFloat(req.getParameter("nota2"));

        ServletContext ctx = getServletContext();

        @SuppressWarnings("unchecked")
        DoublyLinkedList<Curso> lista =
            (DoublyLinkedList<Curso>) ctx.getAttribute("cursosList");

        // se for edição, remove o antigo
        if ("editar".equals(action)) {
            for (Curso c : lista) {
                if (c.getMatricula() == mat && c.getCodigoDisciplina() == cod) {
                    lista.remove(c);
                    break;
                }
            }
        }

        // adiciona novo ou editado
        lista.add(new Curso(mat, cod, n1, n2));

        // persiste no arquivo imediatamente
        PersistenceUtil.saveList(ctx, lista,
            c -> c.getMatricula() + ";" +
                 c.getCodigoDisciplina() + ";" +
                 c.getNota1() + ";" +
                 c.getNota2(),
            "/resources/Cursos.txt");

        resp.sendRedirect(req.getContextPath() + "/curso");
    }
}
