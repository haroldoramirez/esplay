angular.module('agenda')
    .controller('HomeController', function ($scope, $modal, $log) {

      $scope.open = function (size) {

        var modalInstance = $modal.open({
          templateUrl: 'modalHome.html',
          controller: 'ModalController',
          size: size,
          resolve: {
            items: function () {
              return $scope.items;
            }
          }
        });

        modalInstance.result.then(function (selectedItem) {
          $scope.selected = selectedItem;
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
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