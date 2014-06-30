(function () {

    var indexController = function ($scope, monitoringService) {

        var doSelfTest = function () {
            monitoringService.doSelfTest()
                .success(function (data) {
                    $scope.serverStatus = data;
                });
        };

        var getApplicationStatus = function () {
            monitoringService.getApplicationStatus()
                .success(function (data) {
                    $scope.applicationStatus = data;
                });
        };

        doSelfTest();
        getApplicationStatus();
    };

    angular.module("exampleModule").controller("indexController", indexController);
}());