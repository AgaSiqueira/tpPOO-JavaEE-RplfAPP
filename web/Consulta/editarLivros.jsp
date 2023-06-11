<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Livro</title>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
    <div id="app">
        <h1>Editar Livro</h1>
        
        <form @submit.prevent="updateLivro">
            <label for="livroId">ID do Livro:</label>
            <input type="text" id="livroId" v-model="livro.idLivro" readonly>
            <br><br>
            
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" v-model="livro.titulo">
            <br><br>
            
            <label for="autor">Autor:</label>
            <input type="text" id="autor" v-model="livro.autor">
            <br><br>
            
            <label for="genero">Gênero:</label>
            <input type="text" id="genero" v-model="livro.genero">
            <br><br>
            
            <label for="sinopse">Sinopse:</label>
            <textarea id="sinopse" v-model="livro.sinopse"></textarea>
            <br><br>
            
            <label for="editora">Editora:</label>
            <input type="text" id="editora" v-model="livro.editora">
            <br><br>
            
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" v-model="livro.isbn">
            <br><br>
            
            <label for="idioma">Idioma:</label>
            <input type="text" id="idioma" v-model="livro.idioma">
            <br><br>
            
            <label for="disponibilidade">Disponibilidade:</label>
            <input type="number" id="disponibilidade" v-model="livro.disponibilidade">
            <br><br>
            
            <label for="quantidade">Quantidade:</label>
            <input type="number" id="quantidade" v-model="livro.quantidade">
            <br><br>
            
            <label for="ano">Ano:</label>
            <input type="number" id="ano" v-model="livro.ano">
            <br><br>
            
            <button type="submit">Atualizar Livro</button>
        </form>
    </div>
    
    <a href="../index.jsp">Voltar ao Menu</a>
    
    <script>
        new Vue({
            el: '#app',
            data: {
                livro: {
                    idLivro: 0,
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
            mounted() {
                this.loadLivro();
            },
            methods: {
                loadLivro() {
                    // Obter o ID do livro a ser editado a partir da URL
                    const urlParams = new URLSearchParams(window.location.search);
                    const idLivro = urlParams.get('idLivro');

                    // Carregar os dados do livro usando uma requisição GET
                    axios.get(`../livro?idLivro=${idLivro}`)
                        .then(response => {
                            // Atualizar o objeto livro com os dados recebidos do servidor
                            this.livro = response.data.Livros[0];
                        })
                        .catch(error => {
                            console.error(error);
                        });
                },
                updateLivro() {
                    // Atualizar o livro usando uma requisição PUT
                    axios.put('../livro', this.livro)
                        .then(response => {
                            // Redirecionar de volta para a página de livros após a atualização
                            window.location.href = '../livros.jsp';
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
