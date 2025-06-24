<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Curso, app.util.DoublyLinkedList" %>
<html>
<head>
  <title>Inscrições Cadastradas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3">Inscrições Cadastradas</h2>
    <a href="<%= request.getContextPath() %>/curso?action=novo" class="btn btn-primary mb-3">Nova Inscrição</a>
    <table class="table table-striped">
      <thead class="table-dark">
        <tr>
          <th>Aluno</th><th>Disciplina</th><th>Nota 1</th><th>Nota 2</th><th>Ações</th>
        </tr>
      </thead>
      <tbody>
      <%
        DoublyLinkedList<Curso> lista = (DoublyLinkedList<Curso>) request.getAttribute("lista");
        for (Curso c : lista) {
      %>
        <tr>
          <td><%= c.getMatricula() %></td>
          <td><%= c.getCodigoDisciplina() %></td>
          <td><%= c.getNota1() %></td>
          <td><%= c.getNota2() %></td>
          <td>
            <a href="<%=request.getContextPath()%>/curso?action=editar&matricula=<%=c.getMatricula()%>&codigoDisciplina=<%=c.getCodigoDisciplina()%>"
               class="btn btn-sm btn-primary">Editar</a>
            <a href="<%=request.getContextPath()%>/curso?action=deletar&matricula=<%=c.getMatricula()%>&codigoDisciplina=<%=c.getCodigoDisciplina()%>"
               class="btn btn-sm btn-danger">Excluir</a>
          </td>
        </tr>
      <%
        }
      %>
      </tbody>
    </table>
    <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Voltar ao Início</a>
  </div>
</body>
</html>
