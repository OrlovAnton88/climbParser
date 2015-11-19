angular.module('MiracleGuideApp.controllers', ['ngResource']).

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

    .controller("AddAreaCtrl", function ($scope, $resource) {
        console.log("AddAreaCtrl called");


        $scope.submit = function() {
            console.log("form submited");
            var User = $resource('/api/manage/area');

            User.save({url:$scope.url}, function(response) {
//            $scope.message = response.message;
                console.log(response.message)
            });
        };

//        console.log("Id from scope[ " + $routeParams.id + "]");
//        AreaManager.query(function (data) {
//            $scope.areaList = data;
//        });
    });


