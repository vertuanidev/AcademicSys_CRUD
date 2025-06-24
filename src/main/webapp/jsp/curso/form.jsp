<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Curso, app.util.DoublyLinkedList" %>
<html>
<head>
  <title><%= request.getAttribute("curso") == null ? "Nova Inscrição" : "Editar Inscrição" %></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3"><%= request.getAttribute("curso") == null ? "Nova Inscrição" : "Editar Inscrição" %></h2>
    <form action="<%= request.getContextPath() %>/curso" method="post">
      <input type="hidden" name="action" value="<%= request.getAttribute("curso") == null ? "novo" : "editar" %>"/>
      <div class="mb-3">
        <label class="form-label">Matrícula do Aluno</label>
        <input type="number" name="matricula" class="form-control"
               value="<%= ((Curso)request.getAttribute("curso")) != null ? ((Curso)request.getAttribute("curso")).getMatricula() : "" %>"
               <%= request.getAttribute("curso") != null ? "readonly" : "" %> required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Código da Disciplina</label>
        <input type="number" name="codigoDisciplina" class="form-control"
               value="<%= ((Curso)request.getAttribute("curso")) != null ? ((Curso)request.getAttribute("curso")).getCodigoDisciplina() : "" %>"
               <%= request.getAttribute("curso") != null ? "readonly" : "" %> required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Nota 1</label>
        <input type="number" step="0.01" name="nota1" class="form-control"
               value="<%= ((Curso)request.getAttribute("curso")) != null ? ((Curso)request.getAttribute("curso")).getNota1() : "" %>" required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Nota 2</label>
        <input type="number" step="0.01" name="nota2" class="form-control"
               value="<%= ((Curso)request.getAttribute("curso")) != null ? ((Curso)request.getAttribute("curso")).getNota2() : "" %>" required/>
      </div>
      <button type="submit" class="btn btn-primary">Salvar</button>
      <a href="<%= request.getContextPath() %>/curso" class="btn btn-secondary">Cancelar</a>
    </form>
  </div>
</body>
</html>
