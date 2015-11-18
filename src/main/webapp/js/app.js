var app = angular.module('MiracleGuideApp', [
    'MiracleGuideApp.controllers', 'ngResource', 'ngRoute'
]);

app.factory("AllAreas", function ($resource) {
    return $resource("/api/areas");
}).factory("Area", function ($resource) {
    return $resource("/api/area/:id");
});


app.config(function ($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl: 'pages/home.html',
            controller: 'IndexCtrl'
        })

        // route for the about page
        .when('/area/:id', {
            templateUrl: 'pages/area.html',
            controller: 'AreaCtrl'
        })
        .when('/addJson/', {
            templateUrl: 'pages/manageAreas.html',
            controller: 'ManageAreaCtrl'
        });

// route for the contact page
//        .when('/contact', {
//            templateUrl : 'pages/contact.html',
//            controller  : 'contactController'
//        });
})
;