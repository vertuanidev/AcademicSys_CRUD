<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <title>Bem-vindo ao Sistema</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    rel="stylesheet"/>
</head>
<body class="bg-light">
  <div class="container py-5 text-center">
    <!-- Logo UCL -->
    <img
      src="${pageContext.request.contextPath}/images/UCL.png"
      alt="UCL Logo"
      class="img-fluid mb-4"
      style="max-width:200px;"/>

    <h1 class="mb-4">Sistema de Gestão UCL</h1>

    <!-- CRUD -->
    <div class="list-group w-50 mx-auto">
      <a href="${pageContext.request.contextPath}/aluno"
         class="list-group-item">Alunos</a>
      <a href="${pageContext.request.contextPath}/disciplina"
         class="list-group-item">Disciplinas</a>
      <a href="${pageContext.request.contextPath}/curso"
         class="list-group-item">Inscrições</a>
    </div>

    <!-- RESULTADOS INTERATIVOS -->
    <div class="list-group w-50 mx-auto mt-4">
      <a href="${pageContext.request.contextPath}/aluno?action=buscar"
         class="list-group-item list-group-item-action">
        Resultados de um Aluno
      </a>
      <a href="${pageContext.request.contextPath}/disciplina?action=buscar"
         class="list-group-item list-group-item-action">
        Resultados por Disciplina
      </a>
    </div>
  </div>
</body>
</html>
