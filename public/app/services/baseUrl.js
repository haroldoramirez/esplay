angular.module('agenda')
  .factory('BaseUrl', function($location) {
     return 'http://' + $location.host() + ':9000' ;
   });