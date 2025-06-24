<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Aluno, app.util.DoublyLinkedList" %>
<html>
<head>
  <title>Alunos Cadastrados</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3">Alunos Cadastrados</h2>
    <a href="<%=request.getContextPath()%>/aluno?action=novo"
       class="btn btn-primary mb-3">Novo Aluno</a>
    <table class="table table-striped">
      <thead class="table-dark">
        <tr>
          <th>Matrícula</th>
          <th>Nome</th>
          <th>Idade</th>
          <th>Ações</th>
          <th>Resultados</th>
        </tr>
      </thead>
      <tbody>
      <%
        DoublyLinkedList<Aluno> lista =
          (DoublyLinkedList<Aluno>) request.getAttribute("lista");
        for (Aluno a : lista) {
      %>
        <tr>
          <td><%= a.getMatricula() %></td>
          <td><%= a.getNome()      %></td>
          <td><%= a.getIdade()     %></td>
          <td>
            <a href="<%=request.getContextPath()%>/aluno?action=editar&matricula=<%=a.getMatricula()%>"
               class="btn btn-sm btn-primary">Editar</a>
            <a href="<%=request.getContextPath()%>/aluno?action=deletar&matricula=<%=a.getMatricula()%>"
               class="btn btn-sm btn-danger">Excluir</a>
          </td>
          <td>
            <a href="<%=request.getContextPath()%>/aluno?action=resultados&matricula=<%=a.getMatricula()%>"
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
