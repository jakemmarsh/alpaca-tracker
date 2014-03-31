define(['./index'], function (services) {
  'use strict';
  // expand input and show post button on focus
  services.service('authService', ['$q', '$http', function($q, $http) {
    return {
        apiPath: '/api/v1/auth/',
        users: [
            {
                username: 'jacob.marsh'
            },
            {
                username: 'clayton.peterson'
            },
            {
                username: 'sylvia.allain'
            },
            {
                username: 'jonathan.cole'
            }
        ],
        login: function(user) {
            var deferred = $q.defer();

            for(var i = 0; i < this.users.length; i++) {
                if(this.users[i].username === user.username) {
                    deferred.resolve(this.users[i]);
                    return deferred.promise;
                }
            }

            deferred.reject();
            return deferred.promise;
        }
    };
  }]);
});