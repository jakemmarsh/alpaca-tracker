/**
 * This is where you add new components to the application
 * you don't need to sweat the dependency order (that is what RequireJS is for)
 * but implementations' `define`s placed elsewhere void the warranty
 */
define([
    'controllers/login-ctrl',
    'controllers/inner-ctrl',
    'controllers/home-ctrl',
    'controllers/detail-ctrl',
    'services/auth'
], function () {});