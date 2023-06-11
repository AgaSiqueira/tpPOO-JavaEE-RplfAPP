<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Livro</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
    <div id="app">
        
        <h1>Cadastro de Livro</h1>
        
        <form @submit="submitForm">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" v-model="livro.titulo" required><br><br>
            
            <label for="autor">Autor:</label>
            <input type="text" id="autor" v-model="livro.autor" required><br><br>
            
            <label for="genero">Gênero:</label>
            <input type="text" id="genero" v-model="livro.genero" required><br><br>
            
            <label for="sinopse">Sinopse:</label>
            <textarea id="sinopse" v-model="livro.sinopse" required></textarea><br><br>
            
            <label for="editora">Editora:</label>
            <input type="text" id="editora" v-model="livro.editora" required><br><br>
            
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" v-model="livro.isbn" required><br><br>
            
            <label for="idioma">Idioma:</label>
            <input type="text" id="idioma" v-model="livro.idioma" required><br><br>
            
            <label for="disponibilidade">Disponibilidade:</label>
            <input type="number" id="disponibilidade" v-model="livro.disponibilidade" required><br><br>
            
            <label for="quantidade">Quantidade:</label>
            <input type="number" id="quantidade" v-model="livro.quantidade" required><br><br>
            
            <label for="ano">Ano:</label>
            <input type="number" id="ano" v-model="livro.ano" required><br><br>
            
            <button type="submit">Enviar</button>

        </form>
        
            <a href="../Consulta/livros.jsp">Livros cadastrados</a>
            
    </div>
    
    <a href="../index.jsp">Voltar ao Menu</a>
    
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
