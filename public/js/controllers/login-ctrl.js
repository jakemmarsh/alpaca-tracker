define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('loginCtrl', ['$rootScope', '$scope', '$location', 'authService', function ($scope, $rootScope, $location, authService) {
        $scope.login = function(user) {
            authService.login(user).then(function (data) {
                $scope.loginError = null;
                $rootScope.username = user.username;
                $location.path('/home');
            },
            function () {
                $scope.loginError = "That user does not exist.";
            });
        };
    }]);
});