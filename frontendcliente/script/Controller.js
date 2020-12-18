var app = angular.module("app", [] );

app.controller("Controller", function($scope, $http) {
    $scope.usuario = [];

    $scope.addNovoCliente = function(usuario) {
            $http({
                method: 'POST',
                url: "http://localhost:8080/api/cliente",
                data: usuario,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/json; charset=utf-8',
                    'Access-Control-Allow-Origin': '*',
                    'Authorization': window.localStorage.getItem('token')
                }
            }).then(function(resp) {
                alert("Cliente cadastrado com sucesso.");

                delete $scope.cliente;

            }).catch(function(resp) {
                alert("Não foi possível cadastrar" + resp.data.error);
            });
    }

    $scope.criarNovoUsuario = function(novousuario) {
        $http.post("http://localhost:8080/api/usuario", novousuario)
            .then(function(resp) {
                alert('Usuário criado com sucesso.');

                delete $scope.novousuario;
                
            }).catch(function(resp) {
                alert(resp.data.message);
            });
    }

    $scope.autenticarUsuario = function(usuario) {
            $http({
                method: "GET",
                url: "http://localhost:8080/oauth/token?grant_type=password&username="+usuario.login+"&password="+usuario.senha,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8',
                    'Access-Control-Allow-Origin': 'http://localhost:8080',
                    'Authorization': 'Basic Y2xpZW50LWlkOnNlY3JldC1pZA=='
                }
            }).then(function(resp) {
                $scope.token = 'Bearer '+ resp.data.access_token;
                window.localStorage.setItem('token', $scope.token );
                alert("Autenticado com sucesso.");

                $scope.usuarioForm.$setPristine();

                delete $scope.usuario;

                location.pathname = 'C:/Users/Windows/Desktop/cursoprogramadorbr/frontendcliente/cadastroCliente.html';
            }).catch(function(data) {

                alert("Usuário ou senha incorreta.");

            });
    }
    
});