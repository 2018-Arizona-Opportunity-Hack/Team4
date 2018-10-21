
(function(){
    var app = angular.module('app');
    app.controller('ProjectController',ProjectController);
    ProjectController.$inject = ['$scope','$state','$rootScope','$http'];
    console.log("login cont outside");

    function ProjectController($scope,$state,$rootScope,$http){
      $scope.initialize = function(){
        console.log("logged");
        $scope.entity = {}
        $scope.entity.entityName = "";
        $scope.entity.attributes = [
        
          {}
        ];


      }

      $scope.addNewAttribute = function(){
        var temp = {
          };
        $scope.entity.attributes.push(temp);
      }

      $scope.removeAttribute = function(index){
        //$scope.entity.attributes
      }

      $scope.submit = function(){
        console.log($scope.entity);
      }
    /// end of main all fns before this
      
    }

})();

