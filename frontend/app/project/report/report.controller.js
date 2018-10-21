
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
        let query = "";
        let select = "";
        let xaxis = $scope.query.cond.select.one;
        let yaxis = $scope.query.cond.select.two;

        if(!xaxis || xaxis.length == 0){
            return;
        }
        if(!yaxis || yaxis.length == 0){
            return;
        }

        if($scope.query.full){
            query = "select "+xaxis+","+yaxis+" from "+$scope.query.entityName+";";
        }
        $http.post(configData.url+"exportCsv", {'query':query})
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                }else{
                    //$scope.error = "Unable to fetch posts";
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                //$scope.error = "Unable to fetch posts";
                
            });
      }

    }

})();

