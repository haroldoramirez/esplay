angular
    .module
        ('agenda',
            [
             'ngRoute',
             'ngResource',
             'angular-loading-bar',
             'ngAnimate',
             'toastr',
             'mwl.calendar',
             'ui.bootstrap',
             'ui.utils.masks',
             'ui.select',
             'ngSanitize',
             'angular-virtual-keyboard'
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
             })
               .when('/usuarios', {
                  templateUrl: '/assets/app/views/usuarios/list.html',
                  controller: 'UsuarioListController'
             })
               .when('/usuarios/detalhe/:id', {
                  templateUrl: '/assets/app/views/usuarios/detail.html',
                  controller: 'UsuarioDetailController'
             })
               .when('/usuarios/novo', {
                  templateUrl: '/assets/app/views/usuarios/create.html',
                  controller: 'UsuarioCreateController'
             })
               .when('/logs', {
                  templateUrl: '/assets/app/views/logs.html',
                  controller: 'LogListController'
             })
               .when('/manual', {
                  templateUrl: '/assets/app/views/manual.html',
                  controller: 'ManualController'
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
   }).config(['VKI_CONFIG', function(VKI_CONFIG) {
     			VKI_CONFIG.layout['PT-BR'] = {
     				'name': "Portuguese (Brazil)", 'keys': [
     				[["'", '"'], ["1", "!", "\u00b9"], ["2", "@", "\u00b2"], ["3", "#", "\u00b3"], ["4", "$", "\u00a3"], ["5", "%", "\u00a2"], ["6", "\u00a8", "\u00ac"], ["7", "&"], ["8", "*"], ["9", "("], ["0", ")"], ["-", "_"], ["=", "+", "\u00a7"], ["Bksp", "Bksp"]],
     				[["Tab", "Tab"], ["q", "Q", "/"], ["w", "W", "?"], ["e", "E", "\u20ac"], ["r", "R"], ["t", "T"], ["y", "Y"], ["u", "U"], ["i", "I"], ["o", "O"], ["p", "P"], ["\u00b4", "`"], ["[", "{", "\u00aa"], ["Enter", "Enter"]],
     				[["Caps", "Caps"], ["a", "A"], ["s", "S"], ["d", "D"], ["f", "F"], ["g", "G"], ["h", "H"], ["j", "J"], ["k", "K"], ["l", "L"], ["\u00e7", "\u00c7"], ["~", "^"], ["]", "}", "\u00ba"], ["/", "?"]],
     				[["Shift", "Shift"], ["\\", "|"], ["z", "Z"], ["x", "X"], ["c", "C", "\u20a2"], ["v", "V"], ["b", "B"], ["n", "N"], ["m", "M"], [",", "<"], [".", ">"], [":", ":"], ["Shift", "Shift"]],
     				[[" ", " ", " ", " "], ["AltGr", "AltGr"]]
     			], 'lang': ["pt-BR"] };
     			VKI_CONFIG.layout.Numerico = {
     				'name': "Numerico", 'keys': [
     				[["1", '1'], ["2", "2"], ["3", "3"], ["Bksp", "Bksp"]],
     				[["4", "4"], ["5", "5"], ["6", '6'], ["Enter", "Enter"]],
     				[["7", "7"], ["8", "8"], ["9", "9"], []],
     				[["0", "0"], ["-"], ["+"], [","]]
     			], 'lang': ["pt-BR-num"] };
     			/* CHANGE TEXT LANGUAGE */
     			VKI_CONFIG.VKI_i18n = {
     			 	'00': "Exibir teclado numérico",
     			 	'01': "Exibir teclado virtual",
     			 	'02': "Selecionar layout do teclado",
     			 	'03': "Teclas mortas",
     			 	'04': "Ligado",
     			 	'05': "Desligado",
     			 	'06': "Fechar teclado",
     			 	'07': "Limpar",
     			 	'08': "Limpar campo",
     			 	'09': "Versão",
     			 	'10': "Diminuir tamanho do teclado",
     			 	'11': "Aumentar tamanho do teclado"
     			 };
     			 VKI_CONFIG.relative = false;
    }]);