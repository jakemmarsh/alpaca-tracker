define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('settingsCtrl', ['$rootScope', '$scope', 'userService', function ($scope, $rootScope, userService) {

        $scope.user = userService.user;
        $scope.changesSaved = false;
        $scope.new = {};
        $scope.boundaries = $scope.user.farmBoundaries ? $scope.user.farmBoundaries : [
            {
                lat: null,
                lng: null
            }
        ];

        $scope.addPointEntry = function(boundaries) {
            boundaries.push({
                lat: null,
                lng: null
            });
        };

        $scope.lastEntryEmpty = function(boundaries) {
            return ((boundaries[boundaries.length-1].lat === null)
                    || (boundaries[boundaries.length-1].lng === null)
                    || (boundaries[boundaries.length-1].lat.length === 0)
                    || (boundaries[boundaries.length-1].lng.length === 0));
        };

        $scope.invalidFarmBoundaries = function(boundaries) {
            // farm boundaries are invalid if there are less than three, or if there are exactly 3 but the last fields are empty
            if(boundaries.length < 3 || (boundaries.length === 3 && ($scope.lastEntryEmpty(boundaries)))) {
                return true;
            }
            return false;
        };

        $scope.saveSettings = function(user) {
            if($scope.new.password) {
                userService.changePassword($scope.new.password);
            }

            // convert all boundaries to integers before storing
            for(var i = 0; i < $scope.boundaries.length; i++) {
                $scope.boundaries[i].lat = parseFloat($scope.boundaries[i].lat);
                $scope.boundaries[i].lng = parseFloat($scope.boundaries[i].lng);
            }

            user.farmBoundaries = $scope.boundaries;

            userService.updateUser(user).then(function (data) {
                $rootScope.user = data;
                $scope.changesSaved = true;
            });
        };

    }]);
});