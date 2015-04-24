function updateActivedPage(scope) {
    window.scopePage = scope.pagina;
}

angular.module('agenda')
  .controller('TipoCreateController', function ($scope, $modal, $location, Tipo, toastr) {
        $scope.tipo = {};
        $scope.save = function(){
            console.log($scope.tipo);
            Tipo.save($scope.tipo, function(data){
                toastr.success('Tipo Salvo com Sucesso');
                $location.path('/tipos');
            }, function(data){
                console.log(data);
                toastr.error(data.data,'Não foi possível Salvar o Tipo');
            });
        };

        $scope.cancel = function(){
            $location.path('/tipos');
        };

        $scope.init = function(){
            Tipo.getAll(function(data){

                $scope.tipos = data;
            });
        };

  }).controller('TipoListController', function ($scope, Tipo, toastr){
        $scope.tipos = [];
        $scope.init = function(){
          Tipo.getAll(function(data){
            $scope.tipos = data;
          });
          $scope.pagina = 0;
          updateActivedPage(this);
        };

        //botão de páginas
        $scope._pagina = function(val){
        $scope.pagina = val;
            Tipo.getPagina({pagina: $scope.pagina}, $scope.tipo, function(data){
                $scope.tipos = data;
            });
            updateActivedPage(this);
        };

        //botão próximo
        $scope.proximo = function(val){
        $scope.pagina = val + 1;
            Tipo.getPagina({pagina: $scope.pagina}, $scope.tipo, function(data){
                if (data.length===0) {
                    $scope.pagina = $scope.pagina - 1;
                }else{
                    $scope.tipos = data;
                };
            });
            updateActivedPage(this);
         }

        //botão anterior
        $scope.anterior = function(val){
        $scope.pagina = val - 1;
            Tipo.getPagina({pagina: $scope.pagina}, $scope.tipo, function(data){
                $scope.tipos = data;
            });
            updateActivedPage(this);
         }

        //deletar opcional
        $scope.delete = function(id){
           Tipo.delete({id:id}, function(){
               toastr.success('Tipo Removido com Sucesso');
               $scope.init();
           }, function(data){
               toastr.error(data.data,'Não foi possível Remover o Tipo');
           });
        };

  }).controller('TipoDetailController', function ($scope, $modal, $routeParams, $location, Tipo, toastr){

        $scope.open = function (size) {

            $modalInstance = $modal.open({
                  templateUrl: 'modalConfirmacao.html',
                  controller: 'TipoDetailController',
                   size: size,
            });
        };

        $scope.cancelModal = function () {
            $modalInstance.dismiss('cancelModal');
        };

        $scope.init = function(){
            $scope.tipo = Tipo.get({id:$routeParams.id});
        };

        $scope.update = function(){
            Tipo.update({id:$routeParams.id},$scope.tipo, function(data){
                toastr.info('Tipo Atualizado com Sucesso');
                $location.path('/tipos');
            },function(data){
               console.log(data);
               toastr.error(data.data,'Não foi possível Atualizar o Tipo');
            });

        };

         $scope.cancel = function(){
            $location.path('/tipos');
         };

        $scope.delete = function(){
            Tipo.delete({id:$routeParams.id}, function(){
                toastr.warning('Tipo Removido com Sucesso');
                $modalInstance.close();
                $location.path('/tipos');
            }, function(data){
                console.log(data);
                $modalInstance.close();
                toastr.error(data.data,'Não foi possível Remover o Tipo');
            });
        };

  });