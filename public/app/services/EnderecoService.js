angular.module('agenda')
  .service('Categoria',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/categorias/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/categorias', isArray: true},
         update: {method: 'PUT', url: BaseUrl + '/categorias/:id', isArray: false}
      });
  }]).service('Contato',['$resource', 'BaseUrl',
     function($resource, BaseUrl){
       return $resource(BaseUrl + '/contatos/:id', {}, {
          getAll: {method: 'GET', url: BaseUrl + '/contatos', isArray: true},
          update: {method: 'PUT', url: BaseUrl + '/contatos/:id', isArray: false}
       });
  }]);