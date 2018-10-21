(function(){
	'use strict';
	var app = angular.module('app',['ui.router', 'ngCookies','ngAnimate']);
	
	var config,run;
	config.$inject = ['$stateProvider', '$urlRouterProvider']
	
	app.config(config);

	function config($stateProvider, $urlRouterProvider){
		console.log("config init");
		$stateProvider
			.state('home',{
				url:'/',
				controller: 'ProjectController',
				templateUrl: 'app/project/main.view.html',
			})
            .state('formData',{
                url:'/formData',
                controller: 'FormController',
                templateUrl: 'app/formData/form.view.html',
            })
		    
		 $urlRouterProvider.otherwise('/');
	}


})();

