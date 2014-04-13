define(['./index'], function (services) {
  'use strict';
  // expand input and show post button on focus
  services.factory('farmService', ['$firebase', '$q',  function($firebase, $q) {
    var ref = new Firebase('https://crackling-fire-2064.firebaseio.com/farm'),
        farmDB = $firebase(ref);
    return {
        getBoundaries: function() {
            var keys = farmDB.$child('boundaries').$getIndex(),
                boundaries = [];

            for(var i = 0; i < keys.length; i++) {
                boundaries.push(farmDB.$child('boundaries')[keys[i]]);
            }

            return boundaries;
        },
        updateFarm: function(updatedFarm) {
            var deferred = $q.defer();

            farmDB.$update(updatedFarm).then(function() {
                deferred.resolve();
            });

            return deferred.promise;
        }
    }
  }]);
});