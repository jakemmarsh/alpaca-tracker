define(['./index'], function (services) {
  'use strict';
  services.factory('alertService', ['$firebase', '$q',  function($firebase, $q) {
    var ref = new Firebase('https://crackling-fire-2064.firebaseio.com/alerts');

    return $firebase(ref);
  }]);
});