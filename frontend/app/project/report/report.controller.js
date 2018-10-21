
(function(){
    var app = angular.module('app');
    app.controller('ReportController',ReportController);
    ReportController.$inject = ['$scope','$http', 'fileUpload'];

    function ReportController($scope,$http, fileUpload){
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
    }

})();

