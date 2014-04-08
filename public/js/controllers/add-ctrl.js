define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('addCtrl', ['$rootScope', '$scope', 'alpacaService', function ($scope, $rootScope, alpacaService) {
        $scope.alpacas = alpacaService;
        $scope.alpacaAdded = false;

        $scope.addAlpaca = function(alpaca) {
            alpaca.trackerID = parseInt(alpaca.trackerID);

            $scope.alpacas.$add(alpaca).then(function (ref) {
                $scope.addedAlpaca = ref;
                $scope.alpacaAdded = true;
            });
        };

    }]);
});