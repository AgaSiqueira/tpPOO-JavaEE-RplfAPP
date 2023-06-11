<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Livros</title>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
    <div id="app">
        
        <h1>Livros Cadastrados</h1>
        
        <ul>
            <li v-for="livro in livros" :key="livro.idLivro">
                <strong>Id:</strong> {{ livro.idLivro }}<br>
                <strong>Título:</strong> {{ livro.titulo }}<br>
                <strong>Autor:</strong> {{ livro.autor }}<br>
                <strong>Gênero:</strong> {{ livro.genero }}<br>
                <strong>Sinopse:</strong> {{ livro.sinopse }}<br>
                <strong>Editora:</strong> {{ livro.editora }}<br>
                <strong>ISBN:</strong> {{ livro.isbn }}<br>
                <strong>Idioma:</strong> {{ livro.idioma }}<br>
                <strong>Disponibilidade:</strong> {{ livro.disponibilidade }}<br>
                <strong>Quantidade:</strong> {{ livro.quantidade }}<br>
                <strong>Ano:</strong> {{ livro.ano }}<br>
                <button @click="deleteList(livro.idLivro)">Deletar Lista</button>
                <br>
                
                <br>
            </li>
        </ul>
    </div>
<a href="index.jsp">Voltar ao Menu</a>
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
                    axios.get('livro')
                        .then(response => {
                            // Atualizar a lista de livros com os dados recebidos do servidor
                            this.livros = response.data.Livros;
                        })
                        .catch(error => {
                            console.error(error);
                        });
                },
                deleteList(livro) {
                    // Deletar um livro usando uma requisição DELETE com o parâmetro "livro"
                    axios.delete('livro', { params: { livro } })
                        .then(response => {
                            // Atualizar a lista de livros após a exclusão
                            this.livros = response.data.Livros;
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
