<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Disciplina, app.util.DoublyLinkedList" %>
<html>
<head>
  <title><%= request.getAttribute("disciplina") == null ? "Nova Disciplina" : "Editar Disciplina" %></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3"><%= request.getAttribute("disciplina") == null ? "Nova Disciplina" : "Editar Disciplina" %></h2>
    <form action="<%= request.getContextPath() %>/disciplina" method="post">
      <input type="hidden" name="action" value="<%= request.getAttribute("disciplina") == null ? "novo" : "editar" %>"/>
      <div class="mb-3">
        <label class="form-label">Código</label>
        <input type="number" name="codigo" class="form-control"
               value="<%= ((Disciplina)request.getAttribute("disciplina")) != null ? ((Disciplina)request.getAttribute("disciplina")).getCodigo() : "" %>"
               <%= request.getAttribute("disciplina") != null ? "readonly" : "" %> required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Nome</label>
        <input type="text" name="nome" class="form-control"
               value="<%= ((Disciplina)request.getAttribute("disciplina")) != null ? ((Disciplina)request.getAttribute("disciplina")).getNome() : "" %>" required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Nota Mínima</label>
        <input type="number" step="0.01" name="notaMinima" class="form-control"
               value="<%= ((Disciplina)request.getAttribute("disciplina")) != null ? ((Disciplina)request.getAttribute("disciplina")).getNotaMinima() : "" %>" required/>
      </div>
      <button type="submit" class="btn btn-primary">Salvar</button>
      <a href="<%= request.getContextPath() %>/disciplina" class="btn btn-secondary">Cancelar</a>
    </form>
  </div>
</body>
</html>
