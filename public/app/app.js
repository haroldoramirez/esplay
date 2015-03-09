angular
    .module
        ('agenda',
            ['ngRoute',
             'ngResource',
             'toastr',
             'mgcrea.ngStrap',
             'ngAnimate'
            ]
        )
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/assets/app/views/home.html',
                controller: 'HomeController'
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
  //Configuração do datepicker
   }).config(function($datepickerProvider) {
       angular.extend($datepickerProvider.defaults, {
         iconLeft: 'glyphicon glyphicon-triangle-left',
         iconRight: 'glyphicon glyphicon-triangle-right',
         placement: 'bottom'
       });
  //Configuração do popover
  }).config(function($popoverProvider) {
       angular.extend($popoverProvider.defaults, {
         animation: 'am-flip-x',
         trigger: 'focus',
         placement: 'auto'
       });
  });