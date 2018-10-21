angular.module('app')
	.directive('navBar',function(){
		return{
			restrict: 'A',
			templateUrl : 'app/navbar/navbar.view.html'
		}
	})
	.directive('fileModel', ['$parse', function ($parse) {
		return {
		   restrict: 'A',
		   link: function(scope, element, attrs) {
			  var model = $parse(attrs.fileModel);
			  var modelSetter = model.assign;
			  
			  element.bind('change', function(){
				 scope.$apply(function(){
					modelSetter(scope.$root, element[0].files[0]);
				 });
			  });
		   }
		};
	 }])
	 .service('fileUpload', ['$http', function ($http) {
		this.uploadFileToUrl = function(file, uploadUrl){
		   var fd = new FormData();
		   fd.append('data', file);
		
		   $http.post(configData.url+  uploadUrl, fd, {
			  transformRequest: angular.identity,
			  headers: {'Content-Type': undefined}
		   })
		
		   .success(function(){
		   })
		
		   .error(function(){
		   });
		}
	 }]);