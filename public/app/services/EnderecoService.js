angular.module('agenda')
  .service('Categoria',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/categorias/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/categorias', isArray: true},
         getPagina: {method: 'GET', url: BaseUrl + '/categoriasPagina/:pagina', isArray: true},
         update: {method: 'PUT', url: BaseUrl + '/categorias/:id', isArray: false}
      });
  }]);