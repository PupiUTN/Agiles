/**
 * Created by gabriellorenzatti on 31/5/17.
 */
var app=angular.module('app',[]);

app.controller('razaController',['$scope','$log','$http',function($scope,$log,$http, $state, raza) {
        $scope.raza =  raza|| {};
         $scope.myData = null;
            $scope.edicion = false;
       //  var HostUrl="http://" + window.location.host + "/"; ;
    $scope.myWelcome = {};

    var url = hostURL + 'api/razas';


        $scope.mostrarNuevaRaza = function () {
            $('#nuevaRaza').toggle();
        };


    $scope.buscarRaza = function () {

        $('#consultarRaza').toggle();
        $scope.edicion = true;
    };



    function generarRazas(jsonArray) {

        for (var i = 0; i < jsonArray.length; i++) {
            var raza = '\
<div class="col s12">\n\
    <div class="card horizontal blue-grey darken-1 white-text hoverable">\n\
        <div class="card-stacked"> \n\
            <div class="card-content"> \n\
            <span class="card-title">' + jsonArray[i].nombre + ' \n\
            <a href="#!"><span id="btnEliminar' + jsonArray[i].id + '" data-target="modalEliminar" onClick="eliminarRaza(' + jsonArray[i].id + ')" class=" new badge btn waves-effect waves-light orange accent-2 black-text" data-badge-caption="Eliminar" ></span>\n\
            </a> \n\
            </span> \n\
        </div> \n\
    </div> \n\
</div>';
            $('#listaRazas').append(raza);
        }
    }


    window.onload = function () {
        $scope.get();
    };

    $scope.save = function ()
        {
            var data = $scope.raza;
            $http.post(url, data)
                .then(function (data, status, headers, config) {

                    alert( "accion realizada con exito");
                    location.reload();
                },
                    function errorCallback(response) {
                    alert( "failure message: " + JSON.stringify({data: data}));
                });

                    location.reload();

        };


                $scope.get = function ()
                {
                    $http({
                        method : "GET",
                        url : url
                    }).then(function mySuccess(response) {
                        $scope.myWelcome = response.data;
                        generarRazas(  $scope.myWelcome );
                    }, function myError(response) {
                        $scope.myWelcome = response.statusText;
                    });
                };

                        var razaid= 1;


                $scope.getxId = function (razaid)
                {  var url = hostURL + "api/razas";

                    $http({
                        method : "GET",
                        url : url,
                        data:{ "id": 1}
                    }).then(function mySuccess(response) {
                        $scope.myWelcome = response.data;
                    }, function myError(response) {
                        $scope.myWelcome = response.statusText;
                    });
                };


                    var btnEliminar;
                    var idElim;



                    eliminarRaza =  function (idEliminar) {
                        var statusConfirm = confirm("¿Desea eliminar la raza ?");
                        if (statusConfirm == true)
                        {
                            $scope.delete(idEliminar)
                            location.reload();
                        }
                        else {
                            var statusConfirm2 = alert("sete culia");
                        }
                    };

                $scope.delete = function (id)
                {
                    var url = hostURL + "api/razas";

                    $.ajax({

                        url: url + '?' + $.param({"id": id}),
                        type: 'DELETE',
                        success: function () {

                            console.log('Se borro la raza con ID: ' + idElim);
                        },
                        error: function () {
                            idElim = 0;
                            alert('La raza no pudo ser eliminada.');
                        }
                    });
                };


            $scope.cancelar = function (){
                $('#nuevaRaza').toggle();
            };
            $scope.cancelar2  = function (){
                $('#consultarRaza').toggle();
            };
}]);