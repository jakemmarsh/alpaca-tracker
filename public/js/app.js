define([
    'angular',
    'angular-fire',
    'angular-sanitize',
    'angular-ui-bootstrap',
    'angular-ui-router',
    'angular-ui-map',
    './controllers/index',
    './directives/index',
    './filters/index',
    './services/index'
], function (ng) {
    'use strict';

    return ng.module('app', [
        'firebase',
        'app.services',
        'app.controllers',
        'app.filters',
        'app.directives',
        'ngSanitize',
        'ui.bootstrap',
        'ui.router',
        'ui.map'
    ]);
});