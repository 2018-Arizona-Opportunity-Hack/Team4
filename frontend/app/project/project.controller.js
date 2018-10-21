
(function(){
    var app = angular.module('app');
    app.controller('ProjectController',ProjectController);
    ProjectController.$inject = ['$scope','$http', 'fileUpload'];
    console.log("login cont outside");

    function ProjectController($scope,$http, fileUpload){
      $scope.initialize = function(){
        console.log("logged");
        $scope.entity = {}
        $scope.entity.entityName = "";
        $scope.entity.attributes = [
        
          {}
        ];
        $scope.primary = ['Integer', 'String', 'Date'];
        $scope.related = ['Donor', 'Events'];
        $scope.dataTypes = $scope.primary;

        $http.get(configData.url+"/"+$scope.userData)
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                    $scope.getUserPostAnalytics();
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
        
        var uploadUrl = "/fileUpload";
        fileUpload.uploadFileToUrl(file, uploadUrl);
     };
      $scope.addNewAttribute = function(){
        var temp = {
          };
        $scope.entity.attributes.push(temp);
      }

      $scope.removeAttribute = function(index){
        //$scope.entity.attributes
      }

      $scope.submit = function(){
        $http.post(configData.url + '', $scope.entity)
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                    $scope.getUserPostAnalytics();
                }else{
                    //$scope.error = "Unable to fetch posts";
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                //$scope.error = "Unable to fetch posts";
                
            });
      }
      $scope.updateOptions = function(tab) {
        if(tab == 1) {
          $scope.dataTypes.splice(3); 
        } else if (tab == 2) {
          $scope.dataTypes = $scope.dataTypes.concat($scope.related);
        }
      }
    /// end of main all fns before this
      
    }

})();

