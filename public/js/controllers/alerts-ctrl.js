define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('alertsCtrl', ['$scope', '$rootScope', 'alertService', function ($scope, $rootScope, alertService) {

        $scope.alerts = $rootScope.alerts;

        $scope.markAsRead = function(alert) {
            for(var i = 0; i < $scope.alerts.length; i++) {
                if($scope.alerts[i] === alert) {
                    $scope.alerts.splice(i, 1);
                }
            }
        };

    }]);
});