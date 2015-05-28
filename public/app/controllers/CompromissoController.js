function updateActivedPage(scope) {
    window.scopePage = scope.pagina;
}

angular.module('agenda')
  .controller('CompromissoCreateController', function ($scope, $modal, $location, Compromisso, Tipo, Categoria, Contato, Usuario, toastr) {
        $scope.compromisso = {};
        $scope.save = function(){
            console.log($scope.compromisso);
            Compromisso.save($scope.compromisso, function(data){
                toastr.success('Compromisso Salvo com Sucesso');
                $location.path('/agenda');
            }, function(data){
                console.log(data);
                toastr.error(data.data,'Não foi possível Salvar');
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

             Contato.getAll(function(data){
                $scope.contatos = data;
             });

             Usuario.getAll(function(data){
                $scope.usuarios = data;
             })
        };


  }).controller('CompromissoListController', function ($scope, Compromisso, toastr){
          $scope.compromissos = [];
          $scope.init = function(){
            Compromisso.getAll($scope.compromissos, function(data){
              $scope.compromissos = data;
              //console.log($scope.compromissos.length);
            });
            $scope.pagina = 0;
            updateActivedPage(this);
          };

          //botão de páginas
          $scope._pagina = function(val){
          $scope.pagina = val;
              Tipo.getPagina({pagina: $scope.pagina}, $scope.tipo, function(data){
                  $scope.compromissos = data;
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
                 toastr.error(data.data,'Não foi possível Remover');
             });
          };

    }).controller('CompromissoDetailController', function ($scope, $modal, $routeParams, $location, Compromisso, Tipo, Categoria, Contato, Usuario, toastr){

        $scope.open = function (size) {

            $modalInstance = $modal.open({
                  templateUrl: 'modalConfirmacao.html',
                  controller: 'CompromissoDetailController',
                  size: size,
            });
        };

        $scope.cancelModal = function () {
            $modalInstance.dismiss('cancelModal');
        };

        $scope.init = function(){
            $scope.compromisso = Compromisso.get({id:$routeParams.id});
        };


        $scope.init = function(){
            $scope.compromisso = Compromisso.get({id:$routeParams.id});
            $scope.tipos = Tipo.getAll();
            $scope.categorias = Categoria.getAll();
            $scope.contatos = Contato.getAll();
            $scope.usuarios = Usuario.getAll();
        };

        $scope.update = function(){
            Compromisso.update({id:$routeParams.id},$scope.compromisso, function(data){
                toastr.info('Compromisso Atualizado com Sucesso');
                $location.path('/agenda');
            },function(data){
               console.log(data);
               toastr.error(data.data,'Não foi possível Atualizar');
            });

        };

         $scope.cancel = function(){
            $location.path('/agenda');
         };

        $scope.delete = function(){
            Compromisso.delete({id:$routeParams.id}, function(){
                toastr.warning('Compromisso Removido com Sucesso');
                $modalInstance.close();
                $location.path('/agenda');
            }, function(data){
                console.log(data);
                $modalInstance.close();
                toastr.error(data.data,'Não foi possível Remover');
            });
        };

  });