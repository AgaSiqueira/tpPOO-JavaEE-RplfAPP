<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Devolução de Livros</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <h1>Devolução de Livros</h1>

    <div id="app">
        <form @submit.prevent="submitForm">
            <div>
                <label for="cdLivroDevolucao">Código do Livro:</label>
                <input type="text" id="cdLivroDevolucao" v-model="cdLivroDevolucao" required>
            </div>
            <br>
            <div>
                <label for="cdAlugadorDevolucao">Código do Alugador:</label>
                <input type="text" id="cdAlugadorDevolucao" v-model="cdAlugadorDevolucao" required>
            </div>
            <br>
            <div>
                <label for="dataDevolucao">Data de Devolução:</label>
                <input type="date" id="dataDevolucao" v-model="dataDevolucao" required>
            </div>
            <br>
            <button type="submit">Devolver</button>
        </form>
        
        <p>{{ message }}</p>
    </div>
    
    <a href="reservas.jsp">Reservas</a>        
    <a href="index.jsp">Voltar ao Menu</a>

    <script>
        new Vue({
            el: "#app",
            data: {
                cdLivroDevolucao: "",
                cdAlugadorDevolucao: "",
                dataDevolucao: "",
                message: ""
            },
            created() {
                // Recuperar os dados da reserva da URL
                const urlParams = new URLSearchParams(window.location.search);
                this.cdLivroDevolucao = urlParams.get("cdLivroReserva");
                this.cdAlugadorDevolucao = urlParams.get("cdAlugadorReserva");
                this.dataDevolucao = urlParams.get("devolucao");
            },
            methods: {
                submitForm() {
                    // Verificar se os campos são preenchidos
                    if (!this.cdLivroDevolucao || !this.cdAlugadorDevolucao || !this.dataDevolucao) {
                        this.message = "Por favor, preencha todos os campos.";
                        return;
                    }
                    
                    // Verificar a existência da reserva
                    if (!this.reservaExistente()) {
                        this.message = "A reserva informada não existe.";
                        return;
                    }
                    
                    // Verificar a disponibilidade do livro
                    if (!this.livroDisponivel()) {
                        this.message = "O livro informado não está disponível.";
                        return;
                    }

                    const formData = {
                        cdLivroDevolucao: this.cdLivroDevolucao,
                        cdAlugadorDevolucao: this.cdAlugadorDevolucao,
                        dataDevolucao: this.dataDevolucao
                    };

                    axios.post("devolver", formData)
                        .then(response => {
                            console.log(response.data);
                            // Lógica adicional após a resposta do servidor

                            // Limpar os campos de entrada
                            this.cdLivroDevolucao = "";
                            this.cdAlugadorDevolucao = "";
                            this.dataDevolucao = "";
                            this.message = "Devolução registrada com sucesso.";
                        })
                        .catch(error => {
                            console.error(error);
                            // Lógica adicional em caso de erro
                            this.message = "Ocorreu um erro ao registrar a devolução.";
                        });
                },
                reservaExistente() {
                    // Verificar se a reserva existe fazendo uma requisição GET para o servidor
                    const url = `verificarReserva?cdLivro=${this.cdLivroDevolucao}&cdAlugador=${this.cdAlugadorDevolucao}&dataDevolucao=${this.dataDevolucao}`;
                    return axios.get(url)
                        .then(response => {
                            return response.data.reservaExistente;
                        })
                        .catch(error => {
                            console.error(error);
                            return false;
                        });
                },
                livroDisponivel() {
                    // Verificar a disponibilidade do livro fazendo uma requisição GET para o servidor
                    const url = `verificarDisponibilidade?cdLivro=${this.cdLivroDevolucao}`;
                    return axios.get(url)
                        .then(response => {
                            return response.data.livroDisponivel;
                        })
                        .catch(error => {
                            console.error(error);
                            return false;
                        });
                }
            }
        });
    </script>
</body>
</html>
