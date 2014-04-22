define(['./index'], function (controllers) {
    'use strict';
    controllers.controller('homeCtrl', ['$scope', '$rootScope', '$location', 'alpacaService', 'userService', 'farmService', 'alertService', function ($scope, $rootScope, $location, alpacaService, userService, farmService, alertService) {
        var alpacaMarkers = [],
            alpacasPlaced = false,
            boundariesDrawn = false,
            placeAlpacas = function(alpacas) {
                var keys = alpacas.$getIndex();

                for(var i = 0; i < keys.length; i++) {
                    var alpaca = alpacas[keys[i]],
                        marker = new google.maps.Marker({
                            title: alpaca.name,
                            position: new google.maps.LatLng(alpaca.lat, alpaca.lng),
                            icon: '../../img/alpaca.png'
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
            },
            drawFarmBoundaries = function(boundaries) {
                var pathCoords = [],
                    farmPolygon;

                for(var i = 0; i < boundaries.length; i++) {
                    pathCoords.push(new google.maps.LatLng(boundaries[i].lat, boundaries[i].lng));
                }

                farmPolygon = new google.maps.Polygon({
                    paths: pathCoords,
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.65,
                    strokeWeight: 4,
                    fillColor: '#FF0000',
                    fillOpacity: 0
                });

                farmPolygon.setMap($scope.alpacaMap);
            },
            updateMarkers = function(alpacas) {
                var keys = alpacas.$getIndex();

                for(var i = 0; i < keys.length; i++) {
                    for(var j = 0; j < alpacaMarkers.length; j++) {
                        if(alpacaMarkers[j].marker.title.toLowerCase() === alpacas[keys[i]].name.toLowerCase()) {
                            alpacaMarkers[j].marker.setPosition(new google.maps.LatLng(alpacas[keys[i]].lat, alpacas[keys[i]].lng));
                        }
                    }
                }
            };

        $rootScope.user = userService.user;

        $scope.alpacas = alpacaService;

        alpacaService.$on("change", function() {
            $scope.alpacas = alpacaService;
            if($scope.focusedAlpaca) {
                $scope.focusAlpaca($scope.focusedAlpaca.name);
            }
            updateMarkers($scope.alpacas);
        });

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

        $scope.seedAlpacas = function() {
            var names = ["Ted", "Chris", "Fred"];

            for(var i = 0; i < names.length; i++) {
                $scope.alpacas[i] = {
                    name: names[i],
                    trackerID: i,
                    lat: 44.89+(i*0.01),
                    lng: -68.67+(i*0.01),
                    trackerBatteryLife: 100,
                    heartRate: parseFloat(75.0),
                    pitch: parseFloat(0.0),
                    roll: parseFloat(0.0),
                    altitude: parseFloat(8.0),
                    temperature: parseFloat(98.0),
                    hasFix: true,
                    numSatellites: 1,
                    signalQuality: 4
                };
                $scope.alpacas.$save(i);
            }

            placeAlpacas($scope.alpacas);
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

        $scope.mapLoaded = function() {
            // place alpacas on map as they are added
            $scope.$watch('alpacas', function() {
                if(!alpacasPlaced) {
                    placeAlpacas($scope.alpacas);
                    alpacasPlaced = true;
                }
            });

            // draw boundary polygon if coordinates exist
            if(farmService.getBoundaries().length > 0 && !boundariesDrawn) {
                drawFarmBoundaries(farmService.getBoundaries());
                boundariesDrawn = true;
            }
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
                    alpacaMarkers[j].marker.setIcon('../../img/alpaca-focused.png');
                    alpacaMarkers[j].infowindow.open($scope.alpacaMap, alpacaMarkers[j].marker);
                }
                else {
                    alpacaMarkers[j].marker.setIcon('../../img/alpaca.png');
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