Fabio Coelho Uechi Martins

RA: 52.124.011-9

Disciplina: CCM320 – Desenvolvimento de Software Orientado a Objetos

# Introdução

O projeto se trata de uma aplicação em Java com comunicação com banco de dados PostgreSQL, o Spotifei. Inspirado na plataforma de streaming de músicas Spotify, o Spotifei simula algumas de suas funções, podendo criar uma conta, pesquisar músicas e criar e editar playlists, além de um histórico.

# Implementações

1. Cadastro e login de usuário.
   
   Com comunicação com o banco de dados, para cadastro e verificação.

3. Pesquisa de músicas, por nome, autor e gênero.

   Exibe tabela com resultados, com base no termo pesquisado no banco de dados.

4. Possibilidade de curtir ou descurtir músicas.

   Também atrelado ao usuário no banco.

5. Visualizar no histórico músicas curtidas e descurtidas.

   Lista de músicas curtidas ou descurtidas pelo usuário salvas no banco de dados.

6. Criar, apagar e renomear playlists.

   Criação e edição de playlists salvas no banco de dados atrelado à usuário.

7. Adicionar e remover músicas de playlists.

    Adiciona-se pela tela de pesquisa e remove-se pela tela de edição de playlists. Também atrelado ao usuário logado e salvo no banco de dados.

# Estrutura

Implementação Java, com interface gráfica Java Swing pelo IDE do NetBeans, utilizando `JFrame`, `JPanel`, `JTable`, `JButton`, `JTextField`, `JTabbedPane`, entre outros.

Banco de dados PostgreSQL gerenciado pelo pgAdmin 4.

Projeto organizado pelo padrão MVC:

### Model

Classes dos dados da aplicação: `Usuario.java`, `Musica.java`, `Playlist.java`.

### View

Classes das interfaces do projeto como: `LoginFrame.java`, `CadastroFrame.java`, `MenuFrame.java`, entre outros.

### Controller

Classes de comunicação entre View e Model: `ControllerLogin.java`, `ControllerPesquisa.java`.

### DAO

Classes de comunicação com o banco de dados: `UsuarioDAO.java`, `MusicaDAO.java`, `PlaylistDAO.java`.

# Como Executar o Código

## 1. Configuração do Banco de Dados

PostgreSQL, instalado e rodando.

Criar banco de dados com as tabelas:

### 1. `usuarios`

* `id_usuario` (SERIAL, INTEGER, PK)
* `nome_usuario` (VARCHAR(255), NOT NULL)
* `login_usuario` (VARCHAR(100), NOT NULL, UNIQUE)
* `senha_usuario` (VARCHAR(255), NOT NULL)

### 2. `musicas`

* `id_musica` (SERIAL, INTEGER, PK)
* `nome_musica` (VARCHAR(255), NOT NULL)
* `autor_musica` (VARCHAR(255))
* `genero_musica` (VARCHAR(100))

### 3. `curtidas` (ON DELETE CASCADE)

* `id_usuario` (INTEGER, NOT NULL, FK)
* `id_musica` (INTEGER, NOT NULL, FK)
* `data_curtida` (TIMESTAMP WITHOUT TIME ZONE, DEFAULT CURRENT\_TIMESTAMP)
* Chave primária composta por `id_musica` e `id_usuario`

### 4. `descurtidas` (ON DELETE CASCADE)

* `id_usuario` (INTEGER, NOT NULL, FK)
* `id_musica` (INTEGER, NOT NULL, FK)
* `data_descurtida` (TIMESTAMP WITHOUT TIME ZONE, DEFAULT CURRENT\_TIMESTAMP)
* Chave primária composta por `id_musica` e `id_usuario`

### 5. `playlists` (ON DELETE CASCADE)

* `id_playlist` (SERIAL, INTEGER, PK)
* `nome_playlist` (VARCHAR(255), NOT NULL)
* `id_usuario` (INTEGER, NOT NULL, FK)

### 6. `playlist_musicas` (ON DELETE CASCADE)

* `id_playlist` (INTEGER, NOT NULL, FK)
* `id_musica` (INTEGER, NOT NULL, FK)
* Chave primária composta por `id_playlist` e `id_musica`

### 7. `historico_buscas` (ON DELETE CASCADE)

* `id_historico_busca` (SERIAL, INTEGER, PK)
* `id_usuario` (INTEGER, NOT NULL, FK)
* `termo_buscado` (TEXT, NOT NULL)
* `data_busca` (TIMESTAMP WITHOUT TIME ZONE, DEFAULT CURRENT\_TIMESTAMP)

Após a criação das tabelas:

* Popular a tabela `musicas` com alguns exemplos.
* Verificar conexão com o banco de dados no arquivo `DAO/Conexao.java`.

## 2. Compilação e execução

a. Abrir o projeto na IDE NetBeans.

b. Compilar o projeto.

c. Executar a classe que contém `Projeto_spotifei.java`.

# Conclusão

O projeto Spotifei da disciplina de Desenvolvimento de Software Orientado a Objetos foi um projeto bastante desafiador, fazendo com que fosse preciso revisitar diversos conceitos explorados durante o semestre de CCM320. Além de relembrar conceitos vistos nos semestres anteriores, principalmente na construção e organização das tabelas do banco de dados e de SQL. Também permitiu exercitar a solução de problemas e eventuais novos aprendizados na programação e implementações de interfaces em Java e Java Swing.
