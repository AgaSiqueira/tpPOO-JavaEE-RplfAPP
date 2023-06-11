<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reserva de Livros</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    
    <!-- Incluindo Bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
    
    <style>
        body {
            background-color: #87CEEB;
        }
        
        form {
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
    <div id="app" class="container">
        <h1 class="text-center">Reserva de Livros</h1>

        <div id="form" class="row justify-content-center mx-5 mt-4 pb-4 pt-4">
            <div class="col-md-6">
                <form @submit.prevent="submitForm">
                    <div class="mb-3">
                        <label for="cdLivroReserva" class="form-label font-weight-bold">Código do Livro:</label>
                        <input type="text" id="cdLivroReserva" v-model="cdLivroReserva" required class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="cdAlugadorReserva" class="form-label font-weight-bold">Código do Alugador:</label>
                        <input type="text" id="cdAlugadorReserva" v-model="cdAlugadorReserva" required class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="devolucao" class="form-label font-weight-bold">Data de Devolução:</label>
                        <input type="date" id="devolucao" v-model="devolucao" required class="form-control">
                    </div>

                    <button type="submit" class="btn btn-primary">Reservar</button>
                </form>
            </div>
        </div>
    </div>

    <div class="container mt-4">
        <div class="d-flex justify-content-between mx-5 mb-4">
            <div>
                <a href="../index.jsp"><strong>Voltar ao Menu</strong></a>
            </div>
            <div>
                <a href="../Consulta/reservas.jsp"><strong>Reservas</strong></a>
            </div>
        </div>
    </div>

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

                    axios.post('../reservar', formData)
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
