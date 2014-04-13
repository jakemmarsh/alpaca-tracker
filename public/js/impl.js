/**
 * This is where you add new components to the application
 * you don't need to sweat the dependency order (that is what RequireJS is for)
 * but implementations' `define`s placed elsewhere void the warranty
 */
define([
    'controllers/login-ctrl',
    'controllers/register-ctrl',
    'controllers/inner-ctrl',
    'controllers/home-ctrl',
    'controllers/detail-ctrl',
    'controllers/alerts-ctrl',
    'controllers/add-ctrl',
    'controllers/settings-ctrl',
    'services/users',
    'services/alpacas',
    'services/farm',
    'services/alerts'
], function () {});