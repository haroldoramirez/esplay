function updateActivedPage(scope) {
    window.scopePage = scope.pagina;
}

angular.module('agenda')
  .controller('CompromissoCreateController', function ($scope, $modal, $location, Compromisso, Tipo, Categoria, toastr) {
        $scope.compromisso = {};
        $scope.save = function(){
            console.log($scope.compromisso);
            Compromisso.save($scope.compromisso, function(data){
                toastr.success(data.data,'Compromisso Salvo com Sucesso');
                $location.path('/agenda');
            }, function(data){
                console.log(data);
                toastr.error(data.data,'Não foi possível Salvar o Compromisso');
            });
        };

        $scope.cancel = function(){
            $location.path('/agenda');
        };

        $scope.init = function(){
             Tipo.getAll(function(data){
                $scope.tipos = data;
             });

             Categoria.getAll(function(data){
                $scope.categorias = data;
              });
        };


  }).controller('CompromissoDetailController', function ($scope, $modal, $routeParams, $location, Compromisso, toastr){

        $scope.init = function(){
            $scope.compromisso = Compromisso.get({id:$routeParams.id});
        };

        $scope.update = function(){
            Compromisso.update({id:$routeParams.id},$scope.compromisso, function(data){
                toastr.success(data.data,'Compromisso Atualizado com Sucesso');
                $location.path('/agenda');
            },function(data){
               console.log(data);
               toastr.error(data.data,'Não foi possível Atualizar o Compromisso');
            });

        };

         $scope.cancel = function(){
            $location.path('/agenda');
         };

        $scope.delete = function(){
            Compromisso.delete({id:$routeParams.id}, function(){
                toastr.success('Compromisso Removido com Sucesso');
                $location.path('/agenda');
            }, function(data){
            console.log(data);
                toastr.error(data.data,'Não foi possível Remover o Compromisso');
            });
        };

  });