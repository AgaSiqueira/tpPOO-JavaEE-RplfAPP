<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reserva de Livro</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
</head>
<body>
    <div id="app">
        <h1>Reserva de Livro</h1>
        <form @submit.prevent="submitForm">
            <div>
                <label for="cdLivroReserva">Código do Livro:</label>
                <input type="text" id="cdLivroReserva" v-model="cdLivroReserva" required>
            </div>
            <div>
                <label for="cdAlugadorReserva">Código do Alugador:</label>
                <input type="text" id="cdAlugadorReserva" v-model="cdAlugadorReserva" required>
            </div>
            <button type="submit">Reservar</button>
        </form>
    </div>

    <script>
        new Vue({
            el: '#app',
            data: {
                cdLivroReserva: '',
                cdAlugadorReserva: ''
            },
            methods: {
                submitForm() {
                    const formData = new FormData();
                    formData.append('cdLivroReserva', this.cdLivroReserva);
                    formData.append('cdAlugadorReserva', this.cdAlugadorReserva);
                    
                    fetch('../reservar', {
                        method: 'POST',
                        body: formData
                    })
                    .then(response => response.json())
                    .then(data => {
                        // Processar a resposta do servidor aqui
                        console.log(data);
                    })
                    .catch(error => {
                        // Lidar com erros de requisição aqui
                        console.error(error);
                    });
                }
            }
        });
    </script>
</body>
</html>
