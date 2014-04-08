/**
 * bootstraps angular onto the window.document node
 * NOTE: the ng-app attribute should not be on the index.html when using ng.bootstrap
 */
define([
    'require',
    'angular',
    'app',
    'routes',
    'impl'
], function (require, ng, app) {
    'use strict';

    app.run(['$rootScope', '$location', function ($rootScope, $location) {

        // change page title based on state
        $rootScope.$on('$stateChangeSuccess', function(event, toState) {
            if(toState.title) {
                $rootScope.pageTitle = toState.title;
            }
        });

        $rootScope.isActivePage = function(currentPath) {
            if ($location.path().substr(0, currentPath.length) === currentPath) {
              return true;
            }
            return false;
        };

    }]);

    require(['domReady!'], function (document) {
        return ng.bootstrap(document, ['app']);
    });
});