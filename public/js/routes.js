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
        .state('register', {
            url: '/register',
            templateUrl: 'partials/register.html',
            controller: 'registerCtrl',
            title: 'Register'
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
        })
        .state('inner.alerts', {
            url: '/alerts',
            templateUrl: 'partials/alerts.html',
            controller: 'alertsCtrl',
            title: 'Alerts'
        })
        .state('inner.add', {
            url: '/add',
            templateUrl: 'partials/add.html',
            controller: 'addCtrl',
            title: 'Add New Alpaca'
        })
        .state('inner.settings', {
            url: '/settings',
            templateUrl: 'partials/settings.html',
            controller: 'settingsCtrl',
            title: 'Settings'
        });

        $urlRouterProvider.otherwise("/");
    }]);
});