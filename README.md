# Task Management Application

## 1. Planejamento do Projeto

### Funcionalidades:

- **Usuários**: Cadastro, login e gerenciamento de perfis.
- **Tarefas**: CRUD (Criar, Ler, Atualizar, Excluir) de tarefas.
- **Categorias**: Organização de tarefas em categorias.
- **Listas**: Criação e gerenciamento de listas de tarefas.
- **Filtros e Pesquisas**: Filtragem e pesquisa de tarefas por data, prioridade, categoria, etc.
- **Notificações**: Lembretes de tarefas e notificações push (opcional).

## 2. Backend (Quarkus)

### Endpoints:

#### Usuários:

- `POST /users`: Cadastro de usuário.
- `POST /login`: Autenticação de usuário.
- `GET /users/{id}`: Obter informações do usuário.
- `PUT /users/{id}`: Atualizar informações do usuário.
- `DELETE /users/{id}`: Excluir usuário.

#### Tarefas:

- `GET /tasks`: Listar todas as tarefas.
- `POST /tasks`: Criar uma nova tarefa.
- `GET /tasks/{id}`: Obter detalhes de uma tarefa.
- `PUT /tasks/{id}`: Atualizar uma tarefa.
- `DELETE /tasks/{id}`: Excluir uma tarefa.

#### Categorias:

- `GET /categories`: Listar todas as categorias.
- `POST /categories`: Criar uma nova categoria.
- `GET /categories/{id}`: Obter detalhes de uma categoria.
- `PUT /categories/{id}`: Atualizar uma categoria.
- `DELETE /categories/{id}`: Excluir uma categoria.
