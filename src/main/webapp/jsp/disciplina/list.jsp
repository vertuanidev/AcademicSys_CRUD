<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Disciplina, app.util.DoublyLinkedList" %>
<html>
<head>
  <title>Disciplinas Cadastradas</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3">Disciplinas Cadastradas</h2>
    <a href="<%=request.getContextPath()%>/disciplina?action=novo"
       class="btn btn-primary mb-3">Nova Disciplina</a>
    <table class="table table-striped">
      <thead class="table-dark">
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Nota Mínima</th>
          <th>Ações</th>
          <th>Resultados</th>
        </tr>
      </thead>
      <tbody>
      <%
        DoublyLinkedList<Disciplina> lista =
          (DoublyLinkedList<Disciplina>) request.getAttribute("lista");
        for (Disciplina d : lista) {
      %>
        <tr>
          <td><%= d.getCodigo()     %></td>
          <td><%= d.getNome()       %></td>
          <td><%= d.getNotaMinima() %></td>
          <td>
            <a href="<%=request.getContextPath()%>/disciplina?action=editar&codigo=<%=d.getCodigo()%>"
               class="btn btn-sm btn-primary">Editar</a>
            <a href="<%=request.getContextPath()%>/disciplina?action=deletar&codigo=<%=d.getCodigo()%>"
               class="btn btn-sm btn-danger">Excluir</a>
          </td>
          <td>
            <a href="<%=request.getContextPath()%>/disciplina?action=resultados&codigo=<%=d.getCodigo()%>"
               class="btn btn-sm btn-info">Ver</a>
          </td>
        </tr>
      <%
        }
      %>
      </tbody>
    </table>
    <a href="<%=request.getContextPath()%>/" class="btn btn-secondary">Voltar ao Início</a>
  </div>
</body>
</html>
