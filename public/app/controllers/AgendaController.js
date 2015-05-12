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

        $scope.events = [];

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