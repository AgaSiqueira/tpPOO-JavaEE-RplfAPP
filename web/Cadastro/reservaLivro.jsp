<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reserva de Livro</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
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
                <h3 class=" text-white">Reserva de Livro</h3>
            </div>

            <div class="mx-5 text-end">
                <a href="/tpPOO-JavaEE-RplfAPP/index.html" class="btn btn-outline-light me-2" role="button" aria-disabled="true">Sair</a>
            </div>
        </div>
    </div>
</header>

<div id="app" class="container">
    <div id="form" class="row justify-content-center mx-5 mt-4 pb-4 pt-4">
        <div class="text-center">
            <form @submit.prevent="submitForm">
                <div>
                    <label for="cdLivroReserva">Código do Livro:</label><br>
                    <input type="text" id="cdLivroReserva" v-model="cdLivroReserva" required>
                </div>
                <br>
                <br>
                <div>
                    <label for="cdAlugadorReserva">Código do Alugador:</label><br>
                    <input type="text" id="cdAlugadorReserva" v-model="cdAlugadorReserva" required>
                </div>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">Reservar</button>
            </form>
        </div>
    </div>

    <div class="d-flex justify-content-between mx-5 mb-4">
        <div class="container">
            <a href="../alugadores.html" class="btn btn-secondary">Voltar ao Menu</a>
        </div>
        <div class="text-center bg-primary">
            <a href="../Consulta/reservas.jsp" class="btn btn-success"><strong>Reservas cadastradas</strong></a>
        </div>
    </div>
</div>

    <script>
        new Vue({
            el: '#app',
            data: {
                cdLivroReserva: '',
                cdAlugadorReserva: '',
                reservas: []
            },
            methods: {
                submitForm() {
                    const requestData = {
                        cdLivroReserva: this.cdLivroReserva,
                        cdAlugadorReserva: this.cdAlugadorReserva
                    };
                    
                    fetch('../reservar', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(requestData)
                    })
                    .then(response => response.json())
                    .then(data => {
                        this.reservas = data.Reservas;
                    })
                    .catch(error => {
                        console.error(error);
                    });
                }
            },
            mounted() {
                fetch('../reservar')
                    .then(response => response.json())
                    .then(data => {
                        this.reservas = data.Reservas;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            }
        });
    </script>
</body>
</html>
