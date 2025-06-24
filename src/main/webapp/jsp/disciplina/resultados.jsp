<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.model.Result, app.util.DoublyLinkedList" %>
<html>
<head>
  <title>Resultados da Disciplina</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3">Resultados por Disciplina</h2>
    <table class="table table-striped">
      <thead class="table-dark">
        <tr><th>Aluno</th><th>Média</th><th>Situação</th></tr>
      </thead>
      <tbody>
      <%
        DoublyLinkedList<Result> res = (DoublyLinkedList<Result>) request.getAttribute("resultados");
        for (Result r : res) {
      %>
        <tr>
          <td><%= r.getName() %></td>
          <td><%= String.format("%.2f", r.getMedia()) %></td>
          <td><%= r.isAprovado() ? "Aprovado" : "Reprovado" %></td>
        </tr>
      <%
        }
      %>
      </tbody>
    </table>
    <a href="<%= request.getContextPath() %>/disciplina" class="btn btn-secondary">Voltar</a>
  </div>
</body>
</html>
