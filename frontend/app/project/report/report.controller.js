
(function(){
    var app = angular.module('app');
    app.controller('ReportController',ReportController);
    ReportController.$inject = ['$scope','$http', 'fileUpload', '$rootScope'];

    function ReportController($scope,$http, fileUpload, $rootScope){
      $scope.initialize = function(){
        $scope.query = {};
        $scope.options = [{}];
        $scope.initSavedQueries();
        $scope.activeSel;
        $scope.tableData = [];

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
                name: 'Query1',
                columns: ['col1','col2','col3']
            }, {
                id: 2,
                query: 'select * from donor',
                name: 'Donor',
                columns: ['col1','col2','col3']
            },

        ];
      }

      $scope.executeQuery = function(query){
        $scope.savedQueryData =  $scope.getQueryData({query, 'format':'json'});


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
        if ($scope.activeSel == 1) {
            query = "select * from "+$scope.query.entityName+";";
        } else if ($scope.activeSel == 3 && $scope.query.full) {
            let xaxis = $scope.query.cond.select.one;
            let yaxis = $scope.query.cond.select.two;

            if(!xaxis || xaxis.length == 0){
                return;
            }
            if(!yaxis || yaxis.length == 0){
                return;
            }
            query = "select "+xaxis+","+yaxis+" from "+$scope.query.entityName+";";
        }
        return query;
      }

      $scope.getQueryData = function(options){
        return $http.post(configData.url+"export", options)
        .then(function successCallback(response){
            let result = [];
            if(response.data != null){   
                response.data.data.forEach(ele => {
                    result.push(ele);
                });                
                return result;
                console.log(response);
            }else{
                //$scope.error = "Unable to fetch posts";
            }

        }, function errorCallback(response){
            console.log("Error updating views");
            //$scope.error = "Unable to fetch posts";
            
        });
      }

      $scope.showData = async function(){
        let query = $scope.buildQuery();
        $scope.tableData = await $scope.getQueryData({query, 'format':'json'});
        //$scope.tableData = [["1","Test","90","2018-10-10 07:00:00.0"],["3","Test","909","2018-10-10 07:00:00.0"],["4","Test","9","2018-10-10 07:00:00.0"],["5","Test","1","2018-10-10 07:00:00.0"],["6","Test","3","2018-10-10 07:00:00.0"],["7","Test","4","2018-10-10 07:00:00.0"]]
        $scope.$apply();
        setTimeout(function() {
            $('#table_id').DataTable();
          });
      }


      $scope.generateCharts = async function(){
        let query = $scope.buildQuery();
        $scope.tableData = await $scope.getQueryData({query, 'format':'json'});
        
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

