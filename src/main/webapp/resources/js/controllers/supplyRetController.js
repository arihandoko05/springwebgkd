/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';

var sampleApp = angular.module('sampleApp', []).config(
		[ '$httpProvider', function($httpProvider) {
			// For Access-Control-Allow-Origin and Set-Cookie header
			$httpProvider.defaults.withCredentials = true;
		} ]);

sampleApp.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.xsrfCookieName = 'csrftoken';
	$httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken';
} ]);

var SupplyRetController = function($scope, $http, $window, $timeout) {
	$scope.successAlert = false;
	$scope.failAlert = false;
	$scope.qtyRetur = 0;
	$scope.qtyAmbil = 0;

	// Function to find Barang based on No Barcode
	$scope.loadBar = function(noBarcode) {
		var actionUrl = "supplyret/fi";
		$http.get(actionUrl, {
			params : {
				'noReg' : noBarcode,
				'kdGudang' : $scope.selectedWhs.kode
			}
		}).then(
				function(response) {
					$scope.tagLpb = response.data;
					
					if (response.data.noReg == null) {
						console.info('No Data Found');
						$scope.clearComponent();
						$scope.failAlert = true;
						$scope.setMessage('No Barcode ' + noBarcode
								+ ' cannot be found or already scanned today !');
						$timeout(function() {
							$scope.failAlert = false;
						}, 5000);
					} else {
						if($scope.tagLpb.qtySupply == 0){
							console.info('Data Belum pernah diambil');
							$scope.clearComponent();
							$scope.failAlert = true;
							$scope.setMessage('No Barcode ' + noBarcode
									+ ' on this item not been supplied before !');
							$timeout(function() {
								$scope.failAlert = false;
							}, 5000);
						} else {
							$scope.getQtySupply();
							$scope.successAlert = true;
							$scope.saveData($scope.tagLpb);
							$scope.clearComponent();
							$scope.setMessage('No Barcode ' + noBarcode + ' success to Add into Table');
							$timeout(function() {
								$scope.successAlert = false;
							}, 5000);
						}
					}
				}, function(errResponse) {
					console.error('Error while fetching Barcode');
				});

	};

	// Function to Save ScanData
	$scope.saveData = function(taglpb) {
		var actionUrl = "supplyret/pushe?kdGudang="+$scope.selectedWhs.kode+"&qtyRetur="+$scope.qtyRetur
		
		var resp = $http({
			method : 'POST',
			url : actionUrl,
			data : taglpb
		});
		resp.then(function(response) {
			if (response != null) {
				$scope.refreshDatalist();
				console.info('Success post Taglpb');
			}
		}, function(errResponse) {
			console.error('Error while Post TagLpb');
		});

	};
	

	//Fungsi untuk set list data gudang di cbb
	$scope.loadGudang = function() {
		var actionUrl = "whs/whslist.json";
		var res = $http.get(actionUrl);

		res.then(function(response) {
			$scope.whsList = response.data;
			$scope.selectedWhs = response.data[0];

			if (response.data[0] == null) {
				console.info('No Data Found');
			} else {
				$timeout(function() {
					$scope.refreshDatalist();
				}, 1000);
				console.info('Data Found');
			}
		}, function(errResponse) {
			console.error('Error while fetching Gudang');
		});

	};
	
	//untuk me-load data di table berdasar kd gudang
	$scope.refreshDatalist = function() {
		var actionUrl = "supplyret/supplyretlist.json";
		var res = $http.get(actionUrl, {
			params : {
				'kdGudang' : $scope.selectedWhs.kode
			}
		});

		res.then(function(response) {
			$scope.supplyList = response.data;

			if (response.data[0] == 0) {
				console.info('No Data Found');
			} else {
				console.info('Data Found');
			}
		}, function(errResponse) {
			console.error('Error while fetching Barcode');
		});

	};
	
	// Function to Update ScanData
	$scope.updateData = function() {
		var actionUrl = "supplyret/upd?noReg="+$scope.tagLpb.noReg+"&kdGudang="+$scope.selectedWhs.kode+"&qtyAmbil="+$scope.qtyAmbil;
		var resp = $http({
			method : 'POST',
			url : actionUrl,
			data : $scope.qtyRetur
		});
		resp.then(function(response) {
			if (response != null) {
				$scope.refreshDatalist();
				console.info('Success post Taglpb');
			}
		}, function(errResponse) {
			console.error('Error while Post TagLpb');
		});

	};
	
	// Function to Remove ScanData
	$scope.removeData = function() {
		var actionUrl = "supplyret/rmv";
		var res = $http.get(actionUrl, {
			params : {
				'noReg' : $scope.tagLpb.noReg,
				'kdGudang' : $scope.selectedWhs.kode
			}
		});

		res.then(function(response) {
			$scope.setMessage(response.data[0]);

			if (response.data[1] == "success") {
				$scope.successAlert = true;
				$scope.refreshDatalist();
				$scope.clearComponent1();
				$timeout(function() {
					$scope.successAlert = false;
				}, 5000);
			} else {
				$timeout(function() {
					$scope.failAlert = true;
					$scope.failAlert = false;
				}, 5000);
			}
		}, function(errResponse) {
			console.error('Error while fetching Barcode');
		});
	};
	
	$scope.getQtySupply = function() {
		var actionUrl = "supplyret/getQtySupply";
		var res = $http.get(actionUrl, {
			params : {
				'noReg' : $scope.tagLpb.noReg
			}
		});

		res.then(function(response) {
			$scope.qtyAmbil = response.data;

			if (response.data[0] == 0) {
				console.info('No Data Found');
			} else {
				console.info('Data Found');
			}
		}, function(errResponse) {
			console.error('Error while fetching Barcode');
		});
	};
	
	$scope.loadGudang();

	$scope.setMessage = function(message) {
		$scope.msg = message;
	};
	
	$scope.setQtyRetur = function(qty) {
		$scope.qtyRetur = qty;
	};

	$scope.clearComponent = function() {
		$scope.noBarcode = "";
	};
	
	
	/* Start */
	$scope.clock = "loading clock..."; // initialise the time variable
	$scope.tickInterval = 1000 // ms

	var tick = function() {
		$scope.clock = Date.now() // get the current time
		$timeout(tick, $scope.tickInterval); // reset the timer
	}

	// Start the timer
	$timeout(tick, $scope.tickInterval);
	/* End */
	
	$scope.getNamaNpk = function(npk) {
		var actionUrl = "findnpk?npk="+npk;
		var res3 = $http.get(actionUrl);
		
		res3.then(function(response) {
			$scope.namaNpk = response.data;
		}, function(errResponse) {
			console.error('Error while fetching Gudang');
		});
		return $scope.namaNpk;
	};
};
