angular.module('agenda')
    .controller('AgendaListController', function ($scope, $modal, $location, Agenda, toastr, moment){
        $scope.agenda = [];
        $scope.init = function(){
          Agenda.getAll(function(data){
            $scope.agenda = data;
          });
        };

          $scope.open = function (size) {

                $modalInstance = $modal.open({
                    templateUrl: 'modalCompromisso.html',
                    controller: 'AgendaController',
                    size: size,
                });
          };

    }).controller("AgendaController", function($scope){
      var currentYear = moment().year();
      var currentMonth = moment().month();
      $scope.calendarView='month';
      $scope.calendarDay = new Date();
      $scope.changeView=function(type){
        $scope.calendarView=type;
      }

        $scope.events = [
            {
                title: 'Estudar Para Administração financeira', // The title of the event
                type: 'important', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,14,19,55), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,15,22,30), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Prova Administração financeira', // The title of the event
                type: 'warning', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,15,19,00), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,16,22,30), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Aniversário da Baby', // The title of the event
                type: 'info', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,18,19,55), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,19,22,30), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Estudar', // The title of the event
                type: 'inverse', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,1,08,00), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,30,23,30), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Jogar Futebol', // The title of the event
                type: 'success', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,22,22,00), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,27,23,30), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Jogar Voley', // The title of the event
                type: 'special', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,22,22,00), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,27,23,30), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Aula de Engenharia de Software', // The title of the event
                type: 'info', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,4,3,31), // A javascript date object for when the event starts
                ends_at: new Date(2015,4,3,15), // A javascript date object for when the event ends
                editable: true, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Casamento', // The title of the event
                type: 'info', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,1,20), // A javascript date object for when the event starts
                ends_at: new Date(2015,3,10,15), // A javascript date object for when the event ends
                editable: false, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Retirar Celular da Assistencia', // The title of the event
                type: 'info', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,3,1,31), // A javascript date object for when the event starts
                ends_at: new Date(2015,4,10,15), // A javascript date object for when the event ends
                editable: false, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Formatura de Final de Ano', // The title of the event
                type: 'info', // The type of the event (determines its color). Can be important, warning, info, inverse, success or special
                starts_at: new Date(2015,4,1,1), // A javascript date object for when the event starts
                ends_at: new Date(2015,4,10,15), // A javascript date object for when the event ends
                editable: false, // If calendar-edit-event-html is set and this field is explicitly set to false then dont make it editable
                deletable: false // If calendar-delete-event-html is set and this field is explicitly set to false then dont make it deleteable
            },
            {
                title: 'Event 1',
                type: 'warning',
                starts_at: new Date(2015,5,1,1), // A javascript date object for when the event starts
                ends_at: new Date(2015,5,10,15), // A javascript date object for when the event ends
            },
            {
                title: 'Event 2',
                type: 'info',
                starts_at: new Date(2015,6,1,1), // A javascript date object for when the event starts
                ends_at: new Date(2015,6,10,15), // A javascript date object for when the event ends
            },
            {
                title: 'This is a really long event title',
                type: 'important',
                starts_at: new Date(2015,7,25,6,30),
                ends_at: new Date(2015,7,25,6,50)
            },
            {
                title: 'Evento 5',
                type: 'warning',
                starts_at: new Date(2015,7,25,6,11),
                ends_at: new Date(2015,7,25,6,50)
            },
            {
                title: 'Aniversário do Haroldo',
                type: 'special',
                starts_at: new Date(2015,11,2,8,00),
                ends_at: new Date(2015,11,2,23,00)
            },
            {
                title: 'Natal',
                type: 'special',
                starts_at: new Date(2015,11,25,8,00),
                ends_at: new Date(2015,11,25,23,00)
            },
{
                title: 'Ano Novo',
                type: 'special',
                starts_at: new Date(2015,11,31,8,00),
                ends_at: new Date(2015,11,31,23,00)
            }
        ];

            function showModal(action, event) {
              $modal.open({
                templateUrl: 'modalCompromisso.html',
                controller: function($scope, $modalInstance) {
                  $scope.$modalInstance = $modalInstance;
                  $scope.action = action;
                  $scope.event = event;
                }
              });
            }

    $scope.eventClicked = function(event) {
      showModal('Clicked', event);
    };

    $scope.eventEdited = function(event) {
      showModal('Edited', event);
    };

    $scope.eventDeleted = function(event) {
      showModal('Deleted', event);
    };

    $scope.setCalendarToToday = function() {
      $scope.calendarDay = new Date();
    };

    $scope.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();

      event[field] = !event[field];
    };

  });