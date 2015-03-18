function updateActivedPage(scope) {
    window.scopePage = scope.pagina;
}

angular.module('agenda')
  .controller('ContatoCreateController', function ($scope, $modal, $location, Contato, toastr) {
        $scope.contato = {};
        $scope.save = function(){
            console.log($scope.contato);
            Contato.save($scope.contato, function(data){
                toastr.success(data.data,'Contato Salvo com Sucesso');
                $location.path('/contatos');
            }, function(data){
                console.log(data);
                toastr.error(data.data,'Não foi possível Salvar o Contato');
            });
        };

        $scope.cancel = function(){
            $location.path('/contatos');
        };

        $scope.init = function(){
            Contato.getAll(function(data){
                $scope.contatos = data;
            });
        };

  }).controller('ContatoListController', function ($scope, Contato, toastr){
        $scope.contatos = [];
        $scope.init = function(){
          Contato.getAll(function(data){
            $scope.contatos = data;
          });
          $scope.pagina = 0;
          updateActivedPage(this);
        };

        //botão de páginas
        $scope._pagina = function(val){
        $scope.pagina = val;
            Contato.getPagina({pagina: $scope.pagina}, $scope.contato, function(data){
                $scope.contatos = data;
            });
            updateActivedPage(this);
        };

        //botão próximo
        $scope.proximo = function(val){
        $scope.pagina = val + 1;
            Contato.getPagina({pagina: $scope.pagina}, $scope.contato, function(data){
                if (data.length===0) {
                    $scope.pagina = $scope.pagina - 1;
                }else{
                    $scope.contatos = data;
                };
            });
            updateActivedPage(this);
         }

        //botão anterior
        $scope.anterior = function(val){
        $scope.pagina = val - 1;
            Contato.getPagina({pagina: $scope.pagina}, $scope.contato, function(data){
                $scope.contatos = data;
            });
            updateActivedPage(this);
         }

        //deletar opcional
        $scope.delete = function(id){
           Contato.delete({id:id}, function(){
               toastr.success('Contato Removido com Sucesso');
               $scope.init();
           }, function(data){
               toastr.error(data.data,'Não foi possível Remover o Contato');
           });
        };

  }).controller('ContatoDetailController', function ($scope, $modal, $routeParams, $location, Contato, toastr){

        $scope.init = function(){
            $scope.contato = Contato.get({id:$routeParams.id});
        };

        $scope.update = function(){
            Contato.update({id:$routeParams.id},$scope.contato, function(data){
                toastr.success(data.data,'Contato Atualizado com Sucesso');
                $location.path('/contatos');
            },function(data){
               console.log(data);
               toastr.error(data.data,'Não foi possível Atualizar o Contato');
            });

        };

         $scope.cancel = function(){
            $location.path('/contatos');
         };

        $scope.delete = function(){
            Contato.delete({id:$routeParams.id}, function(){
                toastr.success('Contato Removido com Sucesso');
                $location.path('/contatos');
            }, function(data){
            console.log(data);
                toastr.error(data.data,'Não foi possível Remover o Contato');
            });
        };

  });