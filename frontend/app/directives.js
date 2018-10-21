angular.module('app')
	.directive('navBar',function(){
		return{
			restrict: 'A',
			templateUrl : 'app/navbar/navbar.view.html'
		}
	})
