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
            title: 'Login'
        })
        .state('inner', {
            templateUrl: 'partials/inner.html',
            controller: 'innerCtrl'
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