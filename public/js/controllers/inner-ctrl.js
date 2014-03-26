define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('innerCtrl', ['$scope', '$rootScope', '$location', function ($scope, $rootScope, $location) {

        $scope.logout = function() {
            $rootScope.username = null;
            $location.path('/');
        };

    }]);
});