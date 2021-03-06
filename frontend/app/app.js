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
				templateUrl: 'app/project/project.view.html',
			})
		    .state('data',{
				url:'/data/:entity',
				controller: 'FormController',
				templateUrl: 'app/project/data/data_entry.template.html',
			})
			.state('table',{
				url:'/table',
				templateUrl: 'app/project/report/data_table.template.html',
			})
		 $urlRouterProvider.otherwise('/');
	}


})();

