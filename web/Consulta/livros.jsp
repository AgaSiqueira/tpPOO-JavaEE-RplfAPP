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
        body {
            background-color: #87CEEB;
        }
        
        .container {
            margin-top: 50px;
        }
        
        .livro-card {
            border: 1px solid #000;
            border-radius: 10px;
            background-color: #4682B4;
            color: #fff;
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
    <div id="app" class="container mt-1">
        <h1 class="text-center">Livros Cadastrados</h1>
        
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3">
            <div class="col mb-4" v-for="livro in livros" :key="livro.idLivro">
                <div class="livro-card">
                    <h3>Id: {{ livro.idLivro }}</h3>
                    <div>
                        <strong>Título:</strong> {{ livro.titulo }}
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
                            <button @click="editLivro(livro.idLivro)" class="btn btn-primary">Editar Livro</button>
                        </div>
                        <div class="text-center mt-3">
                            <button @click="deleteList(livro.idLivro)" class="btn btn-danger">Deletar Livro</button>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
    
    <div class="d-flex justify-content-between mx-5 mt-1 mb-3">
        <div class="container mt-1">
            <a href="../index.jsp" class="btn btn-secondary">Voltar ao Menu</a>
        </div>
        <div class="text-center" >
            <a href="../Cadastro/cadastroLivro.jsp" class="btn btn-success"><strong>Cadastro de Livros</strong></a>
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
