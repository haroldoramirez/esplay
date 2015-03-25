angular.module('agenda')
    .controller('HomeController', function ($scope, $modal, $log) {

      $scope.open = function (size) {

        var modalInstance = $modal.open({
          templateUrl: 'modalHome.html',
          controller: 'ModalController',
        });
      };

    }).controller('ModalController', function ($scope, $modalInstance) {

        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });