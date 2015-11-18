angular.module('MiracleGuideApp.controllers', []).

    controller("IndexCtrl", function ($scope, AllAreas) {
        console.log("IndexCtrl called");

        AllAreas.query(function (data) {
            $scope.areaList = data;
        });
    })

    .controller("AreaCtrl", function ($scope, $routeParams, Area) {
        console.log("AreaCtrl called");
        console.log("Id from scope[ " + $routeParams.id + "]");
        Area.get({id: $routeParams.id}, function (data) {
            $scope.area = data;
        });
    })

    .controller("ManageAreaCtrl", function ($scope, AreaManager) {
        console.log("ManageAreaCtrl called");
        console.log("Id from scope[ " + $routeParams.id + "]");
        AreaManager.query(function (data) {
            $scope.areaList = data;
        });
    });


