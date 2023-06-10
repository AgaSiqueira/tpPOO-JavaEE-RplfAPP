<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reserva de Livros</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <h1>Reserva de Livros</h1>

    <div id="app">
        <form @submit.prevent="submitForm">
            <div>
                <label for="cdLivroReserva">Código do Livro:</label>
                <input type="text" id="cdLivroReserva" v-model="cdLivroReserva" required>
            </div>
            <br>
            <div>
                <label for="cdAlugadorReserva">Código do Alugador:</label>
                <input type="text" id="cdAlugadorReserva" v-model="cdAlugadorReserva" required>
            </div>
            <br>
            <div>
                <label for="devolucao">Data de Devolução:</label>
                <input type="date" id="devolucao" v-model="devolucao" required>
            </div>
            <br>
            <button type="submit">Reservar</button>

        </form>
        
                    <a href="reservas.jsp">Reservas</a>        
    </div>
<a href="index.jsp">Voltar ao Menu</a>
    <script>
        new Vue({
            el: "#app",
            data: {
                cdLivroReserva: "",
                cdAlugadorReserva: "",
                devolucao: ""
            },
            methods: {
                submitForm() {
                    const formData = {
                        cdLivroReserva: this.cdLivroReserva,
                        cdAlugadorReserva: this.cdAlugadorReserva,
                        devolucao: this.devolucao
                    };

                    axios.post("reservar", formData)
                        .then(response => {
                            console.log(response.data);
                            // Lógica adicional após a resposta do servidor

                            // Limpar os campos de entrada
                            this.cdLivroReserva = "";
                            this.cdAlugadorReserva = "";
                            this.devolucao = "";
                        })
                        .catch(error => {
                            console.error(error);
                            // Lógica adicional em caso de erro
                        });
                }
            }
        });
    </script>
</body>
</html>
