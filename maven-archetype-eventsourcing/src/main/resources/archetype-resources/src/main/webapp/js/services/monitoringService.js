(function(){

    var monitoringService = function($http){

        var service = {};

        service.doSelfTest = function(){
            return $http.get('../services/monitoring/selfTest');
        };

        service.getApplicationStatus = function(){
            return $http.get('../services/monitoring/applicationStatus');
        };

        return service;
    };

    angular.module('exampleModule').factory('monitoringService', monitoringService);
}());