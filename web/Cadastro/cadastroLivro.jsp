    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Formulário de Livro</title>
        <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

        <!-- Incluindo Bootstrap -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">

        <style>
            body{
                background-color: #87CEEB;
            }

            form{
                font-size: 20px;
            }

            #form{
                border: 1px solid #000;
                border-radius: 10px;
                background-color: #4682B4;
            }
        </style>
    </head>
    <body>
        <div id="app" class="container" >
            <h1 class="text-center">Cadastro de Livro</h1>
            <div id="form" class="row justify-content-center mx-5 mt-4 pb-4 pt-4">
                <div class="col-md-6">


                    <form @submit="submitForm">

                        <div class="mb-3">
                            <label for="titulo" class="form-label font-weight-bold">Título:</label>
                            <input type="text" id="titulo" v-model="livro.titulo" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="autor" class="form-label font-weight-bold">Autor:</label>
                            <input type="text" id="autor" v-model="livro.autor" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="genero" class="form-label font-weight-bold">Gênero:</label>
                            <input type="text" id="genero" v-model="livro.genero" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="sinopse" class="form-label font-weight-bold">Sinopse:</label>
                            <textarea id="sinopse" v-model="livro.sinopse" required class="form-control"></textarea>
                        </div>

                        <div class="mb-3">
                            <label for="editora" class="form-label font-weight-bold">Editora:</label>
                            <input type="text" id="editora" v-model="livro.editora" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="isbn" class="form-label font-weight-bold">ISBN:</label>
                            <input type="text" id="isbn" v-model="livro.isbn" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idioma" class="form-label font-weight-bold">Idioma:</label>
                            <input type="text" id="idioma" v-model="livro.idioma" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="disponibilidade" class="form-label font-weight-bold">Disponibilidade:</label>
                            <input type="number" id="disponibilidade" v-model="livro.disponibilidade" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="quantidade" class="form-label font-weight-bold">Quantidade:</label>
                            <input type="number" id="quantidade" v-model="livro.quantidade" required class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="ano" class="form-label font-weight-bold">Ano:</label>
                            <input type="number" id="ano" v-model="livro.ano" required class="form-control">
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
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
                methods: {
                    submitForm() {
                        // Enviar os dados para o servlet usando uma requisição POST
                        axios.post('../livro', this.livro)
                            .then(response => {
                                // Manipular a resposta do servidor
                                console.log(response.data);
                                // Redirecionar para outra página, exibir uma mensagem de sucesso, etc.
                            })
                            .catch(error => {
                                // Tratar erros de requisição
                                console.error(error);
                            });
                    }
                }
            });
        </script>
    </body>
    </html>
