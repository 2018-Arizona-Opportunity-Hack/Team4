
(function(){
    var app = angular.module('app');
    app.controller('FormController',FormController);
    FormController.$inject = ['$scope','$state','$rootScope','$http','$stateParams'];
    console.log("login cont outside");

    function FormController($scope,$state,$rootScope,$http,  $stateParams){
    
        $scope.initialize = function(){
            $scope.isStateParam = !!$stateParams.entity; 
            $scope.entityName = $stateParams.entity;
            if(!$scope.entityName){
                return;
            }
            $scope.getEntityColumns();
        }
        $scope.getEntityColumns = function () {
            $http.get(configData.url+"getTableColumns?tableName="+ $scope.entityName)
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
            var entityArray = [];
            var obj1 = {};
            obj1["attribute"] = $scope.objects;
            obj1["entityName"] = $scope.entityName;
            entityArray.push(obj1);
            var res = $http.post(configData.url+"save", entityArray[0]);
            console.log(entityArray[0]);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
		});
		res.error(function(data, status, headers, config) {
			alert( "Post Functionality failed");
		});
        }
        /// end of main all fns before this

    }

})();

