function updateActivedPage(scope) {
    window.scopePage = scope.pagina;
}

angular.module('agenda')
  .controller('UsuarioCreateController', function ($scope, $modal, $location, Usuario, toastr) {
        $scope.usuario = {};
        $scope.save = function(){
            console.log($scope.usuario);
            Usuario.save($scope.usuario, function(data){
                toastr.success('Novo usuário criado');
                $location.path('/usuarios');
            }, function(data){
                console.log(data);
                toastr.error(data.data,'Não foi possível Salvar');
            });
        };

        $scope.cancel = function(){
            $location.path('/usuarios');
        };

        $scope.init = function(){
            Usuario.getAll(function(data){

                $scope.usuarios = data;
            });
        };

  }).controller('UsuarioListController', function ($scope, Usuario, toastr){
        $scope.usuarios = [];
        $scope.init = function(){
          Usuario.getAll(function(data){
            $scope.usuarios = data;
          }, function(data){
            toastr.error(data.data);
          });
          $scope.pagina = 0;
          updateActivedPage(this);
        };

        //botão de páginas
        $scope._pagina = function(val){
        $scope.pagina = val;
            Usuario.getPagina({pagina: $scope.pagina}, $scope.usuario, function(data){
                $scope.usuario = data;
            });
            updateActivedPage(this);
        };

        //botão próximo
        $scope.proximo = function(val){
        $scope.pagina = val + 1;
            Usuario.getPagina({pagina: $scope.pagina}, $scope.usuario, function(data){
                if (data.length===0) {
                    $scope.pagina = $scope.pagina - 1;
                }else{
                    $scope.usuario = data;
                };
            });
            updateActivedPage(this);
         }

        //botão anterior
        $scope.anterior = function(val){
        $scope.pagina = val - 1;
            Usuario.getPagina({pagina: $scope.pagina}, $scope.usuario, function(data){
                $scope.usuario = data;
            });
            updateActivedPage(this);
         }

        //deletar opcional
        $scope.delete = function(id){
           Usuario.delete({id:id}, function(){
               toastr.success('Usuario Removido com Sucesso');
               $scope.init();
           }, function(data){
               $modalInstance.close();
               toastr.error(data.data,'Não foi possível Remover');
           });
        };

  }).controller('UsuarioDetailController', function ($scope, $modal, $routeParams, $location, Usuario, toastr){

        $scope.open = function (size) {

            $modalInstance = $modal.open({
                  templateUrl: 'modalConfirmacao.html',
                  controller: 'UsuarioDetailController',
                  size: size,
            });
        };

        $scope.cancelModal = function () {
            $modalInstance.dismiss('cancelModal');
        };

        $scope.init = function(){
            $scope.usuario = Usuario.get({id:$routeParams.id});
        };

        $scope.update = function(){
            Usuario.update({id:$routeParams.id},$scope.usuario, function(data){
                toastr.info('Usuario Atualizado com Sucesso');
                $location.path('/usuarios');
            },function(data){
               console.log(data);
               toastr.error(data.data,'Não foi possível Atualizar');
            });

        };

         $scope.cancel = function(){
            $location.path('/usuarios');
         };

        $scope.delete = function(){
            Usuario.delete({id:$routeParams.id}, function(){
                toastr.warning('Usuario Removido com Sucesso');
                $modalInstance.close();
                $location.path('/usuarios');
            }, function(data){
                console.log(data);
                $modalInstance.close();
                toastr.error(data.data,'Não foi possível Remover');
            });
        };

  });