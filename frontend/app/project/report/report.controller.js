
(function(){
    var app = angular.module('app');
    app.controller('ReportController',ReportController);
    ReportController.$inject = ['$scope','$http', 'fileUpload', '$rootScope'];

    function ReportController($scope,$http, fileUpload, $rootScope){
      $scope.initialize = function(){
        $scope.query = "";
        $scope.options = [{}];
        $scope.initSavedQueries();
        
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

      $scope.initSavedQueries = function(){
        $scope.savedQueries = [
            {
                id: 1,
                query: 'select * from dharmesh1',
                name: 'Query1'
            }, {
                id: 2,
                query: 'select * from donor',
                name: 'Donor'
            },

        ];
      }

      $scope.executeQuery = function(query){

      }

      $scope.getRelatedColumns = function(){
        
        $http.get(configData.url+"getRelatedFieldColumns?tableName="+$scope.query.entityName)
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                    $scope.selEntityCols = response.data;
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                
            });
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

      $scope.buildQuery = function(){
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
        return query;
      }

      $scope.getQueryData = function(query){
            // api call
      }

      $scope.showData = function(){
        let query = $scope.buildQuery();
      }


      $scope.generateCharts = function(){
        let query = $scope.buildQuery();
        $http.post(configData.url+"exportCsv", {'query':query})
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                
            });



      }

    }

})();

