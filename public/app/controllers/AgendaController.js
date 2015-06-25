angular.module('agenda')
    .controller('AgendaListController', function ($scope, $modal, $location, Agenda, Compromisso, toastr, moment){
//        $scope.agenda = [];
//        $scope.init = function(){
//          Agenda.getAll(function(data){
//            $scope.agenda = data;
//          });
//        };

    }).controller("AgendaController", function($scope, Compromisso, $modal){
      var currentYear = moment().year();
      var currentMonth = moment().month();
      $scope.calendarView='month';
      $scope.calendarDay = new Date();
      $scope.changeView=function(type){
        $scope.calendarView=type;
      }

//      $scope.events = [{
//                           title: 'Entrega do trabalho TCC', // O título do evento
//                           type: 'important', // Este é o tipo do evento (determinado pela cor). Pode ser important, warning, info, inverse, success or special
//                           startsAt: new Date(2015,5,26,4,45), // Data Início - Um javascript date object para quando o evento iniciar
//                           endsAt: new Date(2015,5,26,5,55), // Data termino - Um javascript date object para quando o evento terminar
//                           editable: true, // If edit-event-html is set and this field is explicitly set to false then dont make it editable
//                           deletable: true, // If delete-event-html is set and this field is explicitly set to false then dont make it deleteable
//                           incrementsBadgeTotal: true //If set to false then will not count towards the badge total amount on the month and year view
//                         },
//                         {
//                            title: 'Prova de AFO',
//                            type: 'important',
//                            startsAt: new Date(2015,5,27,4,45),
//                            endsAt: new Date(2015,5,27,5,55),
//                            editable: true,
//                            deletable: true,
//                            incrementsBadgeTotal: true
//                         }];

      $scope.events =[];
      $scope.init = function(){
        Compromisso.getAll(function(data){
            //$scope.events = data;
            angular.forEach(data, function(compromisso){
                $scope.events.push({
                    title: compromisso.titulo,
                    type: compromisso.tipo.nome,
                    startsAt: new Date(compromisso.dataInicio),
                    endsAt: new Date(compromisso.dataFim)
                });
            })
            console.log(data);
        });
      };

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

                $scope.open = function (size) {

                      $modalInstance = $modal.open({
                          templateUrl: 'modalCompromisso.html',
                          controller: 'AgendaController',
                          size: size,
                      });
                };

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