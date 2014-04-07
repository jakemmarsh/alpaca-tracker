/**
 * Defines the main routes in the application.
 */
define(['./app'], function (app) {
    'use strict';
    app.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', function ($stateProvider, $locationProvider, $urlRouterProvider) {
        $locationProvider.html5Mode(true);

        $stateProvider
        .state('login', {
            url: '/',
            templateUrl: 'partials/login.html',
            controller: 'loginCtrl',
            title: 'Login',
            resolve: {
                checkLogin: ['$rootScope', '$location', function($rootScope, $location){
                    if($rootScope.user) {
                        $location.path('/home');
                    }
                }]
            }
        })
        .state('register', {
            url: '/register',
            templateUrl: 'partials/register.html',
            controller: 'registerCtrl',
            title: 'Register',
            resolve: {
                checkLogin: ['$rootScope', '$location', function($rootScope, $location){
                    if($rootScope.user) {
                        $location.path('/home');
                    }
                }]
            }
        })
        .state('inner', {
            templateUrl: 'partials/inner.html',
            controller: 'innerCtrl',
            resolve: {
                checkLogin: ['$rootScope', '$location', function($rootScope, $location){
                    if(!$rootScope.user) {
                        $location.path('/login');
                    }
                }]
            }
        })
        .state('inner.home', {
            url: '/home',
            templateUrl: 'partials/home.html',
            controller: 'homeCtrl',
            title: 'Home'
        });

        $urlRouterProvider.otherwise("/");
    }]);
});