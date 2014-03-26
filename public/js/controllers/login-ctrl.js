define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('loginCtrl', ['$rootScope', '$scope', '$location', function ($scope, $rootScope, $location) {
        $scope.login = function(user) {
            $rootScope.username = user.username;
            $location.path('/home');
        };
    }]);
});