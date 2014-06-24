(function() {

    var indexController = function($scope, monitoringService) {
    
    	var doSelfTest = function() {
    		monitoringService.doSelfTest()
    			.success(function(data){
    				$scope.serverStatus = data;			
    			});
    	};
    	
    	doSelfTest();
    };

    angular.module("exampleModule").controller("indexController", indexController);
}());