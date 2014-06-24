(function(){

    var monitoringService = function($http){

        var service = {};

        service.doSelfTest = function(){
            return $http.get('../services/monitoring/selfTest');
        };

        return service;
    };

    angular.module('exampleModule').factory('monitoringService', monitoringService);
}());