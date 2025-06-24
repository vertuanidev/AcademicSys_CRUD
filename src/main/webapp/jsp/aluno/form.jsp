<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Aluno, app.util.DoublyLinkedList" %>
<html>
<head>
  <title><%= request.getAttribute("aluno") == null ? "Novo Aluno" : "Editar Aluno" %></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3"><%= request.getAttribute("aluno") == null ? "Novo Aluno" : "Editar Aluno" %></h2>
    <form action="<%= request.getContextPath() %>/aluno" method="post">
      <input type="hidden" name="action" value="<%= request.getAttribute("aluno") == null ? "novo" : "editar" %>"/>
      <div class="mb-3">
        <label class="form-label">Matrícula</label>
        <input type="number" name="matricula" class="form-control"
               value="<%= ((Aluno)request.getAttribute("aluno")) != null ? ((Aluno)request.getAttribute("aluno")).getMatricula() : "" %>"
               <%= request.getAttribute("aluno") != null ? "readonly" : "" %> required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Nome</label>
        <input type="text" name="nome" class="form-control"
               value="<%= ((Aluno)request.getAttribute("aluno")) != null ? ((Aluno)request.getAttribute("aluno")).getNome() : "" %>" required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Idade</label>
        <input type="number" name="idade" class="form-control"
               value="<%= ((Aluno)request.getAttribute("aluno")) != null ? ((Aluno)request.getAttribute("aluno")).getIdade() : "" %>" required/>
      </div>
      <button type="submit" class="btn btn-primary">Salvar</button>
      <a href="<%= request.getContextPath() %>/aluno" class="btn btn-secondary">Cancelar</a>
    </form>
  </div>
</body>
</html>
