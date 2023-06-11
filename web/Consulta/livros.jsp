<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Livros</title>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <!-- Incluindo Bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
    
    <style>
    
        
        .livro-card {
            border-bottom: 1px solid #000;
            border-right: 1px solid #000;
            color: #000;
            padding: 10px;
            margin-bottom: 20px;
        }
        
        .livro-card h3 {
            margin-bottom: 10px;
        }
        
        .livro-card button {
            margin-top: 10px;
        }
        
        .livro-card a {
            color: #fff;
            text-decoration: none;
            margin-top: 10px;
            display: block;
        }
    </style>
</head>
<body>
    <header class="p-3 mb-4 bg-dark">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/tpPOO-JavaEE-RplfAPP/alugadores.html" class="nav-link px-2 text-white">Alugadores</a></li>
                    <li><a href="/tpPOO-JavaEE-RplfAPP/Consulta/livros.jsp" class="nav-link px-2 text-secondary">Livros</a></li>
                    <li><a href="/tpPOO-JavaEE-RplfAPP/Consulta/reservas.jsp" class="nav-link px-2 text-white">Reservas</a></li>
                </ul>

                <div class="mx-5 text-center">
                    <h3 class=" text-white">Livros cadastrados</h3>
                </div>

                <div class="mx-5 text-end">
                    <a href="/tpPOO-JavaEE-RplfAPP/index.html" class="btn btn-outline-light me-2" role="button" aria-disabled="true">Sair</a>
                </div>
            </div>
        </div>
    </header>
    <div id="app" class="container mt-1">
        
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3">
            <div class="col mb-4" v-for="livro in livros" :key="livro.idLivro">
                <div class="livro-card">
                    <h3>Título: {{ livro.titulo }}</h3>
                    <div>
                        <strong>Id:</strong> {{ livro.idLivro }}
                    </div>
                    <div>
                        <strong>Autor:</strong> {{ livro.autor }}
                    </div>
                    <div>
                        <strong>Gênero:</strong> {{ livro.genero }}
                    </div>
                    <div>
                        <strong>Sinopse:</strong> {{ livro.sinopse }}
                    </div>
                    <div>
                        <strong>Editora:</strong> {{ livro.editora }}
                    </div>
                    <div>
                        <strong>ISBN:</strong> {{ livro.isbn }}
                    </div>
                    <div>
                        <strong>Idioma:</strong> {{ livro.idioma }}
                    </div>
                    <div>
                        <strong>Disponibilidade:</strong> {{ livro.disponibilidade }}
                    </div>
                    <div>
                        <strong>Quantidade:</strong> {{ livro.quantidade }}
                    </div>
                    <div>
                        <strong>Ano:</strong> {{ livro.ano }}
                    </div>
                    
                   <div class="d-flex justify-content-between">
                        <div class="text-center mt-3">
                            <button @click="editLivro(livro.idLivro)" class="btn btn-primary">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                            </button>
                        </div>
                        <div class="text-center mt-3">
                            <button @click="deleteList(livro.idLivro)" class="btn btn-danger">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                            </svg>
                            </button>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
    
    <div class="d-flex justify-content-between mx-5 mt-1 mb-3">
        <div class="container mt-1">
            <a href="../alugadores.html" class="btn btn-secondary">Voltar ao Menu</a>
        </div>
        <div class="text-center" >
            <a href="../Cadastro/cadastroLivro.jsp" class="btn btn-success"><strong>Cadastrar Livro</strong></a>
        </div>
    </div>
    
    <script>
        new Vue({
            el: '#app',
            data: {
                livros: []
            },
            mounted() {
                this.loadLivros();
            },
            methods: {
                loadLivros() {
                    // Carregar os dados do servidor usando uma requisição GET
                    axios.get('../livro')
                        .then(response => {
                            // Atualizar a lista de livros com os dados recebidos do servidor
                            this.livros = response.data.Livros;
                        })
                        .catch(error => {
                            console.error(error);
                        });
                },
                deleteList(idLivro) {
                    // Deletar um livro usando uma requisição DELETE com o parâmetro "livro"
                    axios.delete('../livro', { params: { idLivro } })
                        .then(response => {
                            // Atualizar a lista de livros após a exclusão
                            this.livros = response.data.Livros;
                        })
                        .catch(error => {
                            console.error(error);
                        });
                },
                editLivro(idLivro) {
        window.location.href = "../Consulta/editarLivros.jsp?idLivro=" + idLivro;
    },
            }
        });
    </script>
</body>
</html>
