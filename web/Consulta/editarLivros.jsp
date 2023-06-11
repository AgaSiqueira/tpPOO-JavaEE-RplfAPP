<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Livro</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <!-- Incluindo Bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">

    <style>
        form {
            font-size: 20px;
        }

    </style>
</head>
<body>
<header class="p-3 bg-dark">
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
                <h3 class=" text-white">Editar Livro</h3>
            </div>

            <div class="mx-5 text-end">
                <a href="/tpPOO-JavaEE-RplfAPP/index.html" class="btn btn-outline-light me-2" role="button" aria-disabled="true">Sair</a>
            </div>
        </div>
    </div>
</header>

<div id="app" class="container">
    <div id="form" class="row justify-content-center mx-5 mt-4 pb-4 pt-4">
        <div class="col-md-6">

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
    </div>
</div>
<br>
<br>
<div class="d-flex justify-content-between mx-5 mb-4">
    <div class="container">
        <a href="../index.jsp" class="btn btn-secondary">Voltar ao Menu</a>
    </div>
    <div class="text-center bg-primary">
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
