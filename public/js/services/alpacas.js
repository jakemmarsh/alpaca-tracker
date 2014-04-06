define(['./index'], function (services) {
  'use strict';
  // expand input and show post button on focus
  services.service('alpacaService', ['$firebase',  function($firebase) {
    var ref = new Firebase('https://crackling-fire-2064.firebaseio.com/alpacas');
    return $firebase(ref);
  }]);
});