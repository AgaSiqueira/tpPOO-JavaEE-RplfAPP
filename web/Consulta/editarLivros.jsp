<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Livro</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    
    <!-- Incluindo Bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
    
    <style>
        body {
            background-color: #87CEEB;
        }
        
        form {
            font-size: 20px;
            margin-top: 20px;
        }
        
        .container {
            border: 1px solid #000;
            border-radius: 10px;
            background-color: #4682B4;
            margin-top: 30px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div id="app" class="container">
        <h1 class="text-center">Editar Livro</h1>
        
        <form @submit="updateLivro" class="row g-3">
            <div class="col-md-6">
                <label for="titulo" class="form-label font-weight-bold">Título:</label>
                <input type="text" v-model="livro.titulo" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="autor" class="form-label font-weight-bold">Autor:</label>
                <input type="text" v-model="livro.autor" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="genero" class="form-label font-weight-bold">Gênero:</label>
                <input type="text" v-model="livro.genero" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="sinopse" class="form-label font-weight-bold">Sinopse:</label>
                <textarea v-model="livro.sinopse" required class="form-control"></textarea>
            </div>
            
            <div class="col-md-6">
                <label for="editora" class="form-label font-weight-bold">Editora:</label>
                <input type="text" v-model="livro.editora" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="isbn" class="form-label font-weight-bold">ISBN:</label>
                <input type="text" v-model="livro.isbn" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="idioma" class="form-label font-weight-bold">Idioma:</label>
                <input type="text" v-model="livro.idioma" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="disponibilidade" class="form-label font-weight-bold">Disponibilidade:</label>
                <input type="number" v-model="livro.disponibilidade" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="quantidade" class="form-label font-weight-bold">Quantidade:</label>
                <input type="number" v-model="livro.quantidade" required class="form-control">
            </div>
            
            <div class="col-md-6">
                <label for="ano" class="form-label font-weight-bold">Ano:</label>
                <input type="number" v-model="livro.ano" required class="form-control">
            </div>
            
            <div class="col-12">
                <button type="submit" class="btn btn-primary">Salvar</button>
            </div>
        </form>
    </div>
           <div class="d-flex justify-content-between mx-5 mb-4">
            <div class="mt-3">
                <a href="../index.jsp" class="btn btn-secondary">Voltar ao Menu</a>
            </div>
            <div class="mt-3">
                <a href="../Consulta/livros.jsp" class="btn btn-success"><strong>Livros cadastrados</strong></a>
            </div>
        </div>
    <script>
        new Vue({
            el: '#app',
            data: {
                livro: {
                    idLivro: <%= request.getParameter("idLivro") %>,
                    titulo: '',
                    autor: '',
                    genero: '',
                    sinopse: '',
                    editora: '',
                    isbn: '',
                    idioma: '',
                    disponibilidade: 0,
                    quantidade: 0,
                    ano: 0
                }
            },
            created() {
                this.fetchLivro();
            },
            methods: {
                fetchLivro() {
                    axios.get('../livro?idLivro=<%= request.getParameter("idLivro") %>')
                        .then(response => {
                            this.livro = response.data.Livros;
                        })
                        .catch(error => {
                            console.error(error);
                        });
                },
                updateLivro(event) {
                    event.preventDefault();
                    axios.put('../livro?idLivro=<%= request.getParameter("idLivro") %>', this.livro)
                        .then(response => {
                            alert('Livro atualizado com sucesso!');
                        })
                        .catch(error => {
                            console.error(error);
                        });
                }
            }
        });
    </script>
</body>
</html>
