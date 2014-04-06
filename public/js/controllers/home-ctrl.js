define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('homeCtrl', ['$scope', '$rootScope', '$location', '$firebase', function ($scope, $rootScope, $location, $firebase) {
        var alpacaRef = new Firebase('https://crackling-fire-2064.firebaseio.com/');
        $scope.testAlpacas = $firebase(alpacaRef);
        console.log($scope.testAlpacas);
        var alpacaMarkers = [];

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
            for(var i = 0; i < alpacas.length; i++) {
                var marker = new google.maps.Marker({
                    title: alpacas[i].name,
                    position: new google.maps.LatLng(44.89+(i*0.02), -68.67+(i*0.02)),
                    icon: 'http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png'
                }),
                contentString = '<h5>' + alpacas[i].name + '</h5>',
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

        $scope.alpacas = [
            {
                name: 'Alpaca One'
            },
            {
                name: 'Alpaca Two'
            },
            {
                name: 'Alpaca Three'
            }
        ];

        $scope.focusAlpaca = function(alpacaName) {
            // focus on corresponding alpaca
            for(var i = 0; i < $scope.alpacas.length; i++) {
                if($scope.alpacas[i].name === alpacaName) {
                    $scope.focusedAlpaca = $scope.alpacas[i];
                    break;
                }
            }

            // change color of corresponding map marker and open its infowindow
            for(var j = 0; j < alpacaMarkers.length; j++) {
                if(alpacaMarkers[j].marker.title === alpacaName) {
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
                return alpaca.name === $scope.focusedAlpaca.name;
            }
            return false;
        };

    }]);
});