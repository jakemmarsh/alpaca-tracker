define(['./index'], function (services) {
  'use strict';
  services.factory('alertService', ['$firebase', '$q',  function($firebase, $q) {
    var ref = new Firebase('https://crackling-fire-2064.firebaseio.com/alerts'),
        alertsDB = $firebase(ref);
    return {
        getAlerts: function() {
            var keys = alertsDB.$getIndex(),
                alerts = [];

            for(var i = 0; i < keys.length; i++) {
                console.log(keys[i]);
                alerts.push(alertsDB[keys[i]]);
            }

            return alerts;
        },
        markAlertAsRead: function(alert) {
            var deferred = $q.defer();

            return deferred.promise;
        }
    }
  }]);
});