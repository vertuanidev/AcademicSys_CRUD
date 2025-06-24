<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Disciplina, app.util.DoublyLinkedList" %>
<html>
<head>
  <title>Buscar Disciplina</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3">Selecione a Disciplina</h2>
    <form action="<%= request.getContextPath() %>/disciplina" method="get" class="row g-3 align-items-center">
      <input type="hidden" name="action" value="resultados"/>
      <div class="col-auto">
        <select name="codigo" class="form-select">
          <%
            DoublyLinkedList<Disciplina> list = (DoublyLinkedList<Disciplina>) request.getAttribute("listaDisciplinas");
            for (Disciplina d : list) {
          %>
            <option value="<%=d.getCodigo()%>"><%=d.getCodigo()%> – <%=d.getNome()%></option>
          <%
            }
          %>
        </select>
      </div>
      <div class="col-auto">
        <button type="submit" class="btn btn-primary">Ver Resultados</button>
      </div>
      <div class="col-auto">
        <a href="<%= request.getContextPath() %>/disciplina" class="btn btn-secondary">Cancelar</a>
      </div>
    </form>
  </div>
</body>
</html>
