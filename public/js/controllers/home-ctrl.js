define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('homeCtrl', ['$scope', function ($scope, $rootScope, $location) {
    	$scope.test = "value";
    }]);
});