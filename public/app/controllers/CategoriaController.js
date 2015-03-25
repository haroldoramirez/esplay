function updateActivedPage(scope) {
    window.scopePage = scope.pagina;
}

angular.module('agenda')
  .controller('CategoriaCreateController', function ($scope, $modal, $location, Categoria, toastr) {
        $scope.categoria = {};
        $scope.save = function(){
            console.log($scope.categoria);
            Categoria.save($scope.categoria, function(data){
                toastr.success('Categoria Salvo com Sucesso');
                $location.path('/categorias');
            }, function(data){
                console.log(data);
                toastr.error(data.data,'Não foi possível Salvar a Categoria');
            });
        };

        $scope.cancel = function(){
            $location.path('/categorias');
        };

        $scope.init = function(){
            Categoria.getAll(function(data){
                $scope.categorias = data;
            });
        };

  }).controller('CategoriaListController', function ($scope, Categoria, toastr){
        $scope.categorias = [];
        $scope.init = function(){
          Categoria.getAll(function(data){
            $scope.categorias = data;
          });
          $scope.pagina = 0;
          updateActivedPage(this);
        };

        //botão de páginas
        $scope._pagina = function(val){
        $scope.pagina = val;
            Categoria.getPagina({pagina: $scope.pagina}, $scope.categoria, function(data){
                $scope.categorias = data;
            });
            updateActivedPage(this);
        };

        //botão próximo
        $scope.proximo = function(val){
        $scope.pagina = val + 1;
            Categoria.getPagina({pagina: $scope.pagina}, $scope.categoria, function(data){
                if (data.length===0) {
                    $scope.pagina = $scope.pagina - 1;
                }else{
                    $scope.categorias = data;
                };
            });
            updateActivedPage(this);
         }

        //botão anterior
        $scope.anterior = function(val){
        $scope.pagina = val - 1;
            Categoria.getPagina({pagina: $scope.pagina}, $scope.categoria, function(data){
                $scope.categorias = data;
            });
            updateActivedPage(this);
         }

        //deletar opcional
        $scope.delete = function(id){
           Categoria.delete({id:id}, function(){
               toastr.warning('Categoria Removida com Sucesso');
               $scope.init();
           }, function(data){
               toastr.error(data.data,'Não foi possível Remover a Categoria');
           });
        };

  }).controller('CategoriaDetailController', function ($scope, $modal, $routeParams, $location, Categoria, toastr){

        $scope.open = function (size) {

            $modalInstance = $modal.open({
                  templateUrl: 'modalConfirmacao.html',
                  controller: 'CategoriaDetailController',
                  size: size,
            });
        };

        $scope.cancelModal = function () {
            $modalInstance.dismiss('cancelModal');
        };
        $scope.init = function(){
            $scope.categoria = Categoria.get({id:$routeParams.id});
        };

        $scope.update = function(){
            Categoria.update({id:$routeParams.id},$scope.categoria, function(data){
                toastr.info('Categoria Atualizada com Sucesso');
                $location.path('/categorias');
            },function(data){
               console.log(data);
               toastr.error(data.data,'Não foi possível Atualizar a Categoria');
            });

        };

         $scope.cancel = function(){
            $location.path('/categorias');
         };

        $scope.delete = function(){
            Categoria.delete({id:$routeParams.id}, function(){
                toastr.warning('Categoria Removida com Sucesso');
                $modalInstance.close();
                $location.path('/categorias');
            }, function(data){
            console.log(data);
                toastr.error(data.data,'Não foi possível Remover a Categoria');
            });
        };
  });