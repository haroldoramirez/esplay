angular.module('agenda')
    .controller('HomeController', function ($scope, $location, $modal, $log) {

         $scope.popoverInformacoes = {
           "title": "Informações",
           "content": "Home"
         };
    });