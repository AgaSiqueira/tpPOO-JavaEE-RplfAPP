<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exibição de Reservas de Livros</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <h1>Exibição de Reservas de Livros</h1>

    <div id="app">
        <div>
            <h2>Reservas de Livros:</h2>
            <ul>
                <li v-for="reserva in reservas" :key="reserva.cdReserva">
                    Código da Reserva: {{ reserva.cdReserva }},
                    Código do Livro: {{ reserva.cdLivroReserva }},
                    Código do Alugador: {{ reserva.cdAlugadorReserva }},
                    Data de Devolução: {{ reserva.devolucao }}
                    <button @click="excluirReserva(reserva)">Excluir</button>
                    <a href="'devolucao.jsp?cdLivroReserva=' + reserva.cdLivroReserva + '&cdAlugadorReserva=' + reserva.cdAlugadorReserva + '&devolucao=' + reserva.devolucao">Devolver</a>

                </li>
            </ul>
            <a href="index.jsp">Voltar ao Menu</a>
        </div>
    </div>

    <script>
        new Vue({
            el: "#app",
            data: {
                reservas: []
            },
            created() {
                this.carregarReservas();
            },
            methods: {
                carregarReservas() {
                    axios.get("reservar")
                        .then(response => {
                            console.log(response.data);
                            // Lógica adicional após a resposta do servidor

                            // Atualizar a lista de reservas com os dados recebidos
                            this.reservas = response.data.Reservas;
                        })
                        .catch(error => {
                            console.error(error);
                            // Lógica adicional em caso de erro
                        });
                },
                excluirReserva(reserva) {
                    const params = {
                        alugador: reserva.cdAlugadorReserva,
                        reserva: reserva.cdReserva
                    };

                    axios.delete("reservar", { params })
                        .then(response => {
                            console.log(response.data);
                            // Lógica adicional após a resposta do servidor

                            // Atualizar a lista de reservas com os dados recebidos
                            this.reservas = response.data.Reservas;
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
