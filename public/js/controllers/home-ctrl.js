define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('homeCtrl', ['$scope', '$rootScope', '$location', 'alpacaService', function ($scope, $rootScope, $location, alpacaService) {
        var alpacaMarkers = [];

        $scope.alpacas = alpacaService;

        $scope.seedAlpacas = function() {
            var names = ["Ted", "Chris", "Fred"];

            for(var i = 0; i < names.length; i++) {
                $scope.alpacas.$add(
                    {
                        name: names[i],
                        lat: 44.89+(i*0.01),
                        lng: -68.67+(i*0.01)
                    }
                );
            }

            $scope.placeAlpacas($scope.alpacas);
        };

        $scope.clearAlpacas = function() {
            var markersLength = alpacaMarkers.length;
            $scope.alpacas.$remove();
            $scope.focusedAlpaca = null;

            // remove markers from map
            while(markersLength--) {
                alpacaMarkers[markersLength].marker.setMap(null);
                alpacaMarkers.splice(markersLength, 1);
            }
        };

        $scope.mapOptions = {
            center: new google.maps.LatLng(44.89, -68.67),
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDefaultUI: false,
            disableDoubleClickZoom: false,
            draggable: true,
            scrollwheel: true,
            panControl: true,
            streetViewControl: false
        };

        $scope.placeAlpacas = function(alpacas) {
            var keys = alpacas.$getIndex();

            for(var i = 0; i < keys.length; i++) {
                var alpaca = alpacas[keys[i]],
                    marker = new google.maps.Marker({
                        title: alpaca.name,
                        position: new google.maps.LatLng(alpaca.lat, alpaca.lng),
                        icon: 'http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png'
                    }),
                    contentString = '<h5>' + alpaca.name + '</h5>',
                    infowindow = new google.maps.InfoWindow({
                        content: contentString
                    });

                // focus on corresponding alpaca when marker is clicked
                (function (_marker) {
                    google.maps.event.addListener(_marker, 'click', function() {
                        $scope.focusAlpaca(_marker.title);
                    });
                })(marker);

                alpacaMarkers.push({
                    marker: marker,
                    infowindow: infowindow
                });

                marker.setMap($scope.alpacaMap);
            }
        };

        $scope.mapLoaded = function() {
            $scope.seedAlpacas();
            $scope.$watch('alpacas', function() {
                $scope.placeAlpacas($scope.alpacas);
            });
        };

        $scope.focusAlpaca = function(alpacaName) {
            alpacaName = alpacaName.toLowerCase();

            var keys = $scope.alpacas.$getIndex();

            // focus on corresponding alpaca
            for(var i = 0; i < keys.length; i++) {
                if($scope.alpacas[keys[i]].name.toLowerCase() === alpacaName) {
                    $scope.focusedAlpaca = $scope.alpacas[keys[i]];
                    break;
                }
            }

            // change color of corresponding map marker and open its infowindow
            for(var j = 0; j < alpacaMarkers.length; j++) {
                if(alpacaMarkers[j].marker.title.toLowerCase() === alpacaName) {
                    alpacaMarkers[j].marker.setIcon('http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png');
                    alpacaMarkers[j].infowindow.open($scope.alpacaMap, alpacaMarkers[j].marker);
                }
                else {
                    alpacaMarkers[j].marker.setIcon('http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png');
                    alpacaMarkers[j].infowindow.close();
                }
            }
        };

        $scope.isFocusedAlpaca = function(alpaca) {
            if($scope.focusedAlpaca) {
                return alpaca.name.toLowerCase() === $scope.focusedAlpaca.name.toLowerCase();
            }
            return false;
        };

    }]);
});