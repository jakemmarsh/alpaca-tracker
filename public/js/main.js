/*
 * configure RequireJS
 * prefer named modules to long paths, especially for version mgt
 * or 3rd party libraries
 */
require.config({

    paths: {
        'lib': './lib',
        'async': './lib/async',
        'domReady': './lib/requirejs/domReady',
        'angular': './lib/angular/angular.min',
        'angular-sanitize' : './lib/angular/angular-sanitize.min',
        'angular-ui-bootstrap' : './lib/angular/ui-bootstrap-0.6.0.min',
        'angular-ui-router' : './lib/angular/angular-ui-router.min',
        'angular-ui-map' : './lib/angular/ui-map',
        'angular-ui-event' : './lib/angular/event',
        'bootstrap-js' : './lib/bootstrap.min',
        'jquery' : './lib/jquery-2.0.3.min',
        'jquery-ui' : './lib/jquery-ui'
    },

    shim: {
        'angular': {
            exports: 'angular',
            deps: ['bootstrap-js']
        },
        'angular-sanitize': {
            deps: ['angular']
        },
        'angular-ui-bootstrap': {
            deps: ['angular']
        },
        'angular-ui-router': {
            deps: ['angular']
        },
        'angular-switch-toggle': {
            deps: ['angular', 'jquery']
        },
        'angular-ui-map': {
            deps: ['angular', 'angular-ui-event', 'async!http://maps.google.com/maps/api/js?sensor=false']
        },
        'angular-ui-event': {
            deps: ['angular']
        },
        'bootstrap-js': {
            deps: ['jquery']
        },
        'jquery-ui': {
            deps: ['jquery']
        }
    }
});

require(['./bootstrap'], function () {
    //nothing to do here...see bootstrap.js
});