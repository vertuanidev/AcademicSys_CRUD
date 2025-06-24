# 🎓 AcademicSys

Sistema acadêmico web desenvolvido em Java utilizando Servlets e JSPs, com deploy no Apache Tomcat. Permite o gerenciamento de **alunos**, **cursos** e **disciplinas**, com persistência em arquivos `.txt` e estrutura de dados personalizada (lista duplamente encadeada).

---

## ⚙️ Tecnologias Utilizadas

- Java 8+
- Servlets API
- JSP
- Apache Tomcat 9+
- Eclipse IDE
- Estruturas de dados (listas encadeadas)
- Armazenamento em arquivos `.txt`

---

## 🚀 Funcionalidades

- Cadastro de alunos, cursos e disciplinas
- Listagem e busca por nome
- Persistência local (arquivos `.txt`)
- Associação entre entidades
- Estrutura modular (DAO, modelo, servlet, utilitário)
- Interface web com JSPs

---

## 🗂️ Estrutura do Projeto

```
src/
├── app/
│   ├── dao/            # Classes DAO (AlunoDAO, CursoDAO, DisciplinaDAO)
│   ├── listener/       # AppContextListener para carga inicial
│   ├── model/          # Classe Result (resposta das operações)
│   ├── util/           # Lista encadeada e persistência de arquivos
│   ├── web/            # Servlets (AlunoServlet, CursoServlet, etc.)
│   └── Entidades: Aluno, Curso, Disciplina e listas relacionadas

webapp/
├── index.jsp           # Tela inicial
├── images/             # Logo e imagens da aplicação
├── jsp/                # Telas JSP divididas por entidade (aluno, curso, disciplina)
├── resources/          # Arquivos de dados persistentes (.txt)
└── WEB-INF/
    └── web.xml         # Configuração do servlet container
```

---

## 🧪 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/DEVictin/AcademicSys_CRUD.git
   ```

2. Importe no Eclipse:
   - `File > Import > Existing Projects into Workspace`

3. Configure o Apache Tomcat no Eclipse.

4. Rode o servidor com o projeto `curso_java`.

5. Acesse no navegador:
   ```
   http://localhost:8080/curso_java
   ```

---

## 📎 Observações

- O projeto **não utiliza banco de dados**, mas sim arquivos `.txt` para simular persistência.
- As listas de alunos, cursos e disciplinas são armazenadas usando uma **lista duplamente encadeada personalizada**.
- O `AppContextListener` carrega os dados na inicialização da aplicação.

---
## 👨🏼‍🎓 Projeto Acadêmico (UCL - Universidade Centro Leste)

- Projeto acadêmico desenvolvido na máteria de Algoritmos e Estruturas de Dados II

---
## 👤 Autor

**DEVictin**  
[GitHub - @DEVictin](https://github.com/DEVictin)
