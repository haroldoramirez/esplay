angular.module('agenda')
.service('Agenda',['$resource', 'BaseUrl',
     function($resource, BaseUrl){
        return $resource(BaseUrl + '/agenda/:id', {}, {
           getAll: {method: 'GET', url: BaseUrl + '/agenda', isArray: true},
        });
  }]).service('Categoria',['$resource', 'BaseUrl',
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
  }]).service('Tipo',['$resource', 'BaseUrl',
     function($resource, BaseUrl){
        return $resource(BaseUrl + '/tipos/:id', {}, {
           getAll: {method: 'GET', url: BaseUrl + '/tipos', isArray: true},
           update: {method: 'PUT', url: BaseUrl + '/tipos/:id', isArray: false}
        });
  }])
  .service('Compromisso',['$resource', 'BaseUrl',
     function($resource, BaseUrl){
        return $resource(BaseUrl + '/compromissos/:id', {}, {
           update: {method: 'PUT', url: BaseUrl + '/compromissos/:id', isArray: false}
        });
  }]);