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
  });