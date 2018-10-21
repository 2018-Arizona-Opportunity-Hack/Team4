
(function(){
    var app = angular.module('app');
    app.controller('FormController',FormController);
    FormController.$inject = ['$scope','$state','$rootScope','$http','$stateParams'];
    console.log("login cont outside");

    function FormController($scope,$state,$rootScope,$http,  $stateParams){
    
        $scope.initialize = function(){
            if(!$stateParams.entity){
                return;
            }
            $http.get(configData.url+"getTableColumns?tableName="+ $stateParams.entity)
            .then(function successCallback(response){
                if(response.data != null){   
                   $scope.objects = response.data;               
                    console.log(response);
                }else{
                    //$scope.error = "Unable to fetch posts";
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                //$scope.error = "Unable to fetch posts";
                
            });
        }

        $scope.submit = function(){
            console.log($scope.objects);
        }
        /// end of main all fns before this

    }

})();

