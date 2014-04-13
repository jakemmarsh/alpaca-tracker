define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('innerCtrl', ['$scope', '$rootScope', '$location', 'alertService', function ($scope, $rootScope, $location, alertService) {

        $rootScope.alerts = alertService;

        alertService.$on("change", function() {
            var keys = alertService.$getIndex();
            $rootScope.numAlerts = 0;

            for(var i = 0; i < keys.length; i++) {
                if(alertService[keys[i]].read === false) {
                    $rootScope.numAlerts += 1;
                }
            }
        });

        $scope.logout = function() {
            $rootScope.user = null;
            $location.path('/');
        };

    }]);
});