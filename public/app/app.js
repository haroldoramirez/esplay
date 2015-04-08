angular
    .module
        ('agenda',
            [
             'ngRoute',
             'ngResource',
             'ngAnimate',
             'toastr',
             'mwl.calendar',
             'ui.bootstrap',
             'ui.utils.masks'
            ]
        )
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/assets/app/views/home.html',
                controller: 'HomeController'
            })
             .when('/agenda', {
                templateUrl: '/assets/app/views/agenda/agenda.html',
                controller: 'AgendaListController'
            })
            .when('/ajuda', {
                templateUrl: '/assets/app/views/help.html',
                controller: 'HelpController'
            })
            .when('/categorias', {
                templateUrl: '/assets/app/views/categorias/list.html',
                controller: 'CategoriaListController'
            })
            .when('/categorias/novo', {
                templateUrl: '/assets/app/views/categorias/create.html',
                controller: 'CategoriaCreateController'
            })
            .when('/categorias/detalhe/:id', {
                templateUrl: '/assets/app/views/categorias/detail.html',
                controller: 'CategoriaDetailController'
            })
            .when('/contatos', {
                templateUrl: '/assets/app/views/contatos/list.html',
                controller: 'ContatoListController'
            })
            .when('/contatos/novo', {
                templateUrl: '/assets/app/views/contatos/create.html',
                controller: 'ContatoCreateController'
            })
            .when('/contatos/detalhe/:id', {
                templateUrl: '/assets/app/views/contatos/detail.html',
                controller: 'ContatoDetailController'
            })
             .when('/tipos', {
                templateUrl: '/assets/app/views/tipos/list.html',
                controller: 'TipoListController'
            })
             .when('/tipos/novo', {
                templateUrl: '/assets/app/views/tipos/create.html',
                controller: 'TipoCreateController'
             })
             .when('/tipos/detalhe/:id', {
                 templateUrl: '/assets/app/views/tipos/detail.html',
                 controller: 'TipoDetailController'
             })
             .when('/compromissos/novo', {
                 templateUrl: '/assets/app/views/compromissos/create.html',
                 controller: 'CompromissoCreateController'
             })
             .when('/compromissos/detalhe/:id', {
                 templateUrl: '/assets/app/views/compromissos/detail.html',
                 controller: 'CompromissoDetailController'
             })
              .when('/compromissos', {
                 templateUrl: '/assets/app/views/compromissos/list.html',
                 controller: 'CompromissoListController'
             });
   //Configuração das notificações
   }).config(function(toastrConfig) {
        angular.extend(toastrConfig, {
          allowHtml: true,
          closeButton: true,
          closeHtml: '<button>&times;</button>',
          containerId: 'toast-container',
          extendedTimeOut: 6000,
          iconClasses: {
            error: 'toast-error',
            info: 'toast-info',
            success: 'toast-success',
            warning: 'toast-warning'
          },
          messageClass: 'toast-message',
          //positionClass: 'toast-bottom-full-width',
          //positionClass: 'toast-top-full-width',
          positionClass: 'toast-bottom-right',
          tapToDismiss: true,
          timeOut: 6000,
          titleClass: 'toast-title',
          toastClass: 'toast'
        })
   });