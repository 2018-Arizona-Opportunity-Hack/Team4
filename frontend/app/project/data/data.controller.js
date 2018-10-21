
(function(){
    var app = angular.module('app');
    app.controller('FormController',FormController);
    FormController.$inject = ['$scope','$state','$rootScope','$http','$stateParams', 'fileUpload'];
    console.log("login cont outside");

    function FormController($scope,$state,$rootScope,$http,  $stateParams, fileUpload){
    
        $scope.initialize = function(){
            $scope.configURL = configData.url;
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
        
        $scope.uploadFile = function(){
            var file = $scope.$root.myFile;
            
            console.log('file is ' );
            console.dir(file);
            
            var uploadUrl = "bulk-import";
            fileUpload.uploadFileToUrl(file, uploadUrl, $scope.entityName);
         };
        $scope.submit = function(){
            var entityArray = [];
            var obj1 = {};
            obj1["attributes"] = $scope.objects;
            obj1["entityName"] = $scope.entityName;
            entityArray.push(obj1);
            $http.post(configData.url+"save", entityArray[0])
            .then(function successCallback(response){
                if(response.data != null){                    
                    $scope.message = data;
                }else{
                    //$scope.error = "Unable to fetch posts";
                }

            }, function errorCallback(response){
                console.log("Post Functionality failed");
            });
        }
        /// end of main all fns before this

    }

})();

