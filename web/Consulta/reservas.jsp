<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exibição de Reservas de Livros</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <header class="p-3 mb-4 bg-dark">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/tpPOO-JavaEE-RplfAPP/alugadores.html" class="nav-link px-2 text-white">Alugadores</a></li>
                    <li><a href="/tpPOO-JavaEE-RplfAPP/Consulta/livros.jsp" class="nav-link px-2 text-white">Livros</a></li>
                    <li><a href="/tpPOO-JavaEE-RplfAPP/Consulta/reservas.jsp" class="nav-link px-2 text-secondary">Reservas</a></li>
                </ul>

                <div class="mx-5 text-center">
                    <h3 class="text-white">Reservas de Livros</h3>
                </div>

                <div class="mx-5 text-end">
                    <a href="/tpPOO-JavaEE-RplfAPP/index.html" class="btn btn-outline-light me-2" role="button" aria-disabled="true">Sair</a>
                </div>
            </div>
        </div>
    </header>

    <div id="app" class="container mt-4">
        <div>
            <div v-for="reserva in reservas" :key="reserva.cdReserva" class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">Código da Reserva: {{ reserva.cdReserva }}</h5>
                    <p class="card-text">Código do Livro: {{ reserva.cdLivroReserva }}</p>
                    <p class="card-text">Código do Alugador: {{ reserva.cdAlugadorReserva }}</p>
                    <p class="card-text">Data de Devolução: {{ reserva.devolucao }}</p>
                    <button @click="deleteReserva(reserva.cdReserva)" class="btn btn-danger btn-sm">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                    </button>
                </div>
            </div>
                <div class="d-flex justify-content-between mx-5 mb-4">
        <div class="container">
            <a href="../alugadores.html" class="btn btn-secondary">Voltar ao Menu</a>
        </div>
        <div class="text-center bg-primary">
            <a href="../Cadastro/reservaLivro.jsp" class="btn btn-success"><strong>Cadastrar reservas</strong></a>
        </div>
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
                    axios.get("../reservar")
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
                deleteReserva(cdReserva) {
                    axios.delete(`../reservar`,{ params: { cdReserva } })
                        .then(response => {
                            console.log(response.data);
                            // Lógica adicional após a exclusão da reserva
                            // Atualizar a lista de reservas
                            this.carregarReservas();
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
