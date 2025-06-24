<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="app.Aluno, app.util.DoublyLinkedList" %>
<html>
<head>
  <title>Buscar Aluno</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-4">
    <h2 class="mb-3">Selecione o Aluno</h2>
    <form action="<%= request.getContextPath() %>/aluno" method="get" class="row g-3 align-items-center">
      <input type="hidden" name="action" value="resultados"/>
      <div class="col-auto">
        <select name="matricula" class="form-select">
          <%
            DoublyLinkedList<Aluno> alunos = (DoublyLinkedList<Aluno>) request.getAttribute("listaAlunos");
            for (Aluno a : alunos) {
          %>
            <option value="<%=a.getMatricula()%>">
              <%=a.getMatricula()%> – <%=a.getNome()%>
            </option>
          <%
            }
          %>
        </select>
      </div>
      <div class="col-auto">
        <button type="submit" class="btn btn-primary">Ver Resultados</button>
      </div>
      <div class="col-auto">
        <a href="<%= request.getContextPath() %>/aluno" class="btn btn-secondary">Cancelar</a>
      </div>
    </form>
  </div>
</body>
</html>
