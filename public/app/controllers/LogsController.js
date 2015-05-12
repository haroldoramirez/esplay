angular.module('agenda')
    .controller('LogsController', function ($scope, $modal, $log) {

      $scope.open = function (size) {

        var modalInstance = $modal.open({
          templateUrl: 'modalLogs.html',
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
    }).controller('LogListController', function ($scope, Log, toastr){
        $scope.logs = [];
        $scope.init = function(){
            Log.getAll(function(data){
                  $scope.logs = data;
            });
        };

    });