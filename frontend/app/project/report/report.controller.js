
(function(){
    var app = angular.module('app');
    app.controller('ReportController',ReportController);
    ReportController.$inject = ['$scope','$http', 'fileUpload', '$rootScope'];

    function ReportController($scope,$http, fileUpload, $rootScope){
      $scope.initialize = function(){
        $scope.query = "";
        $scope.options = [{}];

        // $http.get(configData.url+"/")
        //     .then(function successCallback(response){
        //         if(response.data != null){                    
        //             console.log(response);
        //         }else{
        //             //$scope.error = "Unable to fetch posts";
        //         }

        //     }, function errorCallback(response){
        //         console.log("Error updating views");
        //         //$scope.error = "Unable to fetch posts";
                
        //     });


      }

      $scope.setSelEntityIndex = function(){
          let count = 0;
          $rootScope.allEntityDetails.forEach(e => {
              if(e.entityName == $scope.query.entityName){
                $scope.query.selEntityIndex = count;
                
              }
              count++;
          });
      }

      $scope.generateChart = function(){
          
      }

    }

})();

