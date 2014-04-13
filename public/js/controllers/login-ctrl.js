define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('loginCtrl', ['$rootScope', '$scope', '$location', 'userService', function ($scope, $rootScope, $location, userService) {
        $scope.login = function(user) {
            userService.login(user).then(function (data) {
                $scope.loginError = null;
                $location.path('/home');
            },
            function (errorMessage) {
                $scope.loginError = errorMessage;
            });
        };
    }]);
});