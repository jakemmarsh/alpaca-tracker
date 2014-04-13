define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('registerCtrl', ['$scope', 'userService', function ($scope, userService) {
        $scope.registered = false;

        $scope.register = function(user) {
            userService.register(user).then(function (data) {
                $scope.registered = true;
            }, function(errorMessage) {
                $scope.registerError = errorMessage;
            });
        };
    }]);
});