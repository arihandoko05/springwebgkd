
//Define an angular module for our app
var sampleApp = angular.module('sampleApp', []);
 
//Define Routing for app
//Uri /AddNewOrder -> template add_order.html and Controller AddOrderController
//Uri /ShowOrders -> template show_orders.html and Controller AddOrderController
sampleApp.config(['$routeProvider',
  function($routeProvider) {
	$routeProvider.when('/supply', {
		templateUrl: 'supply/list',
        controller: 'SupplyController'
    }).otherwise({redirectTo: '',
    	controller: 'SupplyController'});
}]);
 
 
var SupplyController = function($scope, $http, $window) {
	
	$scope.loadBar = function(noBarcode) {
		$scope.FormData = 'This is Show orders screen';
        $window.alert(noBarcode);
	};
};
 
 