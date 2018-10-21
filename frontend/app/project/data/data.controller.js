
(function(){
    var app = angular.module('app');
    app.controller('FormController',FormController);
    FormController.$inject = ['$scope','$state','$rootScope','$http'];
    console.log("login cont outside");

    function FormController($scope,$state,$rootScope,$http){
        var data = {

            attributes: [
                {
                    "name": "name",
                    "datatype": "int",
                    "isMandatory": true
                },
                {
                    "name": "age",
                    "datatype": "int",
                    "isMandatory": false
                },
                {
                    "name": "date",
                    "datatype": "date",
                    "isMandatory": true
                },
                {
                    "name": "address",
                    "datatype": "str",
                    "isMandatory": true
                },

            ]
        }
        $scope.objects = data.attributes;
        var fromObject = {
            attributes: [
        ]
        }

        for(var i=0;i<data.attributes.length;i++){
            var obj = {};
            obj[data.attributes[i].name] = ""; fromObject.attributes.push(obj);
        }

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
            console.log($scope.objects);
        }
        /// end of main all fns before this

    }

})();

