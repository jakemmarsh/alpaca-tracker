define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('alertsCtrl', ['$scope', '$rootScope', 'alertService', function ($scope, $rootScope, alertService) {

        $scope.alerts = $rootScope.alerts;

        var keys = $scope.alerts.$getIndex();
        $scope.displayAlerts = [];
        for(var i = 0; i < keys.length; i++) {
            if($scope.alerts[keys[i]].read === false) {
                var alert = angular.copy($scope.alerts[keys[i]]);
                alert.key = keys[i];
                $scope.displayAlerts.push(alert);
            }
        }

        $scope.alerts.$on("change", function() {
            var keys = $scope.alerts.$getIndex();

            $scope.displayAlerts = [];

            for(var i = 0; i < keys.length; i++) {
                if($scope.alerts[keys[i]].read === false) {
                    var alert = angular.copy($scope.alerts[keys[i]]);
                    alert.key = keys[i];
                    $scope.displayAlerts.push(alert);
                }
            }
        });

        $scope.markAsRead = function(alert) {
            $scope.alerts.$child(alert.key).$update({ read: true });
        };

        $scope.markAllAsRead = function() {
            var keys = $scope.alerts.$getIndex();

            for(var i = 0; i < keys.length; i++) {
                $scope.alerts.$child(keys[i]).$update({ read: true });
            }
        };

    }]);
});