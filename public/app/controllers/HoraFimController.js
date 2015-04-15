angular.module('agenda').controller('HoraFimController', function ($scope, $log) {
  $scope.now = function() {
     $scope.horaFim = new Date();
  };
  $scope.horaFim = new Date();

  $scope.hstep = 1;
  $scope.mstep = 1;

  $scope.options = {
    hstep: [1, 2, 3],
    mstep: [1, 5, 10, 15, 25, 30]
  };

  $scope.ismeridian = true;
  $scope.toggleMode = function() {
    $scope.ismeridian = ! $scope.ismeridian;
  };

  $scope.update = function() {
    var d = new Date();
    d.setHours( 14 );
    d.setMinutes( 0 );
    $scope.mytime = d;
  };

  $scope.changed = function () {
    $log.log('Hora alterada: ' + $scope.mytime);
  };

  $scope.clear = function() {
    $scope.mytime = null;
  };
});