define(['./index', 'cryptoJS'], function (services) {
  'use strict';
  // expand input and show post button on focus
  services.factory('userService', ['$q', '$http', '$firebase', function($q, $http, $firebase) {
    var ref = new Firebase('https://crackling-fire-2064.firebaseio.com/auth'),
        usersDB = $firebase(ref);

    return {
        user: null,
        register: function(user) {
            var deferred = $q.defer(),
                keys = usersDB.$getIndex();

            // first, make sure username is not taken
            for(var i = 0; i < keys.length; i++) {
                if(usersDB[keys[i]].username.toLowerCase() === user.username) {
                    deferred.reject("That username is already taken.");
                    return deferred.promise;
                }
            }

            // create salt, hash password, and create user object
            var salt = CryptoJS.lib.WordArray.random(128/8),
                passKeyHash = CryptoJS.PBKDF2(user.password, salt, { keySize: 512/32, iterations: 1000 }).toString(CryptoJS.enc.Base64),
                newUser = {
                    username: user.username,
                    salt: salt,
                    hash: passKeyHash
                };

            // save new user in database
            usersDB[newUser.username] = newUser;
            usersDB.$save(newUser.username).then(function(ref) {
                deferred.resolve(ref);
            });

            return deferred.promise;
        },
        login: function(user) {
            var deferred = $q.defer(),
                keys = usersDB.$getIndex();

            user.username = user.username.toLowerCase();

            for(var i = 0; i < keys.length; i++) {
                // find user with matching usename
                if(usersDB[keys[i]].username.toLowerCase() === user.username) {
                    var passKeyHash = CryptoJS.PBKDF2(user.password, usersDB[keys[i]].salt, { keySize: 512/32, iterations: 1000 }).toString(CryptoJS.enc.Base64);
                    // hash password and check for match
                    if(passKeyHash === usersDB[keys[i]].hash) {
                        var validUser = angular.copy(usersDB[keys[i]]);
                        delete validUser.salt;
                        delete validUser.hash;

                        this.user = validUser;

                        deferred.resolve(validUser);
                        return deferred.promise;
                    }
                    else {
                        deferred.reject("That password is incorrect.");
                    }
                }
            }

            deferred.reject("That user does not exist.");
            return deferred.promise;
        },
        updateUser: function(user) {
            var deferred = $q.defer();

            return deferred.promise;
        }
    };
  }]);
});