define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('addCtrl', ['$rootScope', '$scope', 'alpacaService', function ($scope, $rootScope, alpacaService) {
        $scope.alpacas = alpacaService;
        $scope.alpacaAdded = false;

        $scope.addAlpaca = function(alpaca) {
            var keys = $scope.alpacas.$getIndex(),
                maxKey = 0;

            // get maximum key to know what ID to store new alpaca under
            for(var i = 0; i < keys.length; i++) {
                if(keys[i] > maxKey) {
                    maxKey = parseInt(keys[i]);
                }
            }
            maxKey += 1;

            alpaca.trackerID = parseInt(alpaca.trackerID);

            $scope.alpacas[maxKey] = alpaca;

            $scope.alpacas.$save(maxKey).then(function (ref) {
                $scope.addedAlpaca = ref;
                $scope.alpacaAdded = true;
            });
        };

    }]);
});