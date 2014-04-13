define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('innerCtrl', ['$scope', '$rootScope', '$location', 'alertService', function ($scope, $rootScope, $location, alertService) {

        $rootScope.alerts = alertService.getAlerts();
        console.log($rootScope.alerts);

        $scope.logout = function() {
            $rootScope.user = null;
            $location.path('/');
        };

    }]);
});