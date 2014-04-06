define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('registerCtrl', ['$scope', 'authService', function ($scope, authService) {
        $scope.registered = false;

        $scope.register = function(user) {
            authService.register(user).then(function (data) {
                $scope.registered = true;
            }, function(errorMessage) {
                $scope.registerError = errorMessage;
            });
        };
    }]);
});