define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('loginCtrl', ['$rootScope', '$scope', '$location', 'authService', function ($scope, $rootScope, $location, authService) {
        $scope.login = function(user) {
            authService.login(user).then(function (data) {
                $scope.loginError = null;
                $rootScope.user = data;
                $location.path('/home');
            },
            function (errorMessage) {
                $scope.loginError = errorMessage;
            });
        };
    }]);
});