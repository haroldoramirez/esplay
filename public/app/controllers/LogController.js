angular.module('agenda')
 .controller('LogListController', function ($scope, Log, toastr){
         $scope.logs = [];
         $scope.init = function(){
           Log.getAll(function(data){
             $scope.logs = data;
           }, function(data){
             toastr.info(data.data);
           });
         };
   });