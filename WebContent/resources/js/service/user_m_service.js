'use strict';

angular.module('myApp').factory('UserMService', ['$http', '$q','$location', function($http, $q, $location){
	
	var REST_SERVICE_URI = 'rest/userm/';
	var REST_SERVICE_URI_PHONE = 'rest/phone/';
	var REST_SERVICE_URI_USER = 'rest/user/';
	
    var factory = {
    		fetchAllUsers: fetchAllUsers,
    		fetchUser: fetchUser,
    		fetchAllDevices: fetchAllDevices,
            createUser: createUser,
            deleteUser: deleteUser,
            listCountry: listCountry,
            examplePhone: examplePhone,
            authorizeUser: authorizeUser,
            loginUser: loginUser
        };

        return factory;
        
        function examplePhone(regionCode){
            var deferred = $q.defer();
            $http.get(REST_SERVICE_URI_PHONE+"example/"+regionCode)
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;        	
        }
        
        function listCountry(){
            var deferred = $q.defer();
            $http.get(REST_SERVICE_URI_PHONE+"list")
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;        	
        }        
        
        function fetchAllUsers(filter) {
            var deferred = $q.defer();
            $http.post(REST_SERVICE_URI+"list", filter)
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }
        
        function fetchAllDevices() {
            var deferred = $q.defer();
            $http.get(REST_SERVICE_URI+"devices")
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }
        
        function fetchUser(id){
            var deferred = $q.defer();
            $http.get(REST_SERVICE_URI+id)
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;        	
        }
        
        function createUser(user) {
            var deferred = $q.defer();
            $http.post(REST_SERVICE_URI+"create/", user)
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }  
        
        function deleteUser(user) {
            var deferred = $q.defer();
            $http.post(REST_SERVICE_URI+"delete", user.id)
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function(errResponse){
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        } 
        
        function loginUser(user,remember){
        	var deferred = $q.defer();
            $http.post(REST_SERVICE_URI+"exchange", user)
            .then(
            function (response) {
            	if(response.status==200){
            		authorizeUser(response.data,remember);
            	}
                deferred.resolve(response);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;    	
        }
        
        function authorizeUser(user,remember) {
        	var deferred = $q.defer();
            var postData = "ssoId="+user.ssoId+"&password="+user.password+"&rememberMe="+remember;

            $http({
                method: 'POST',
                url: REST_SERVICE_URI_USER+"authenticate/",
                data: postData,
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "X-Login-Ajax-call": 'true'
                }
            })
            .then(function(response) {
            	if(response.status == 200){
            		sessionStorage.setItem("loginUserId", user.id);
            		window.location.replace('/home');
            		return;
            	}else{
            		deferred.resolve(response);
            	}
            });
            return deferred.promise;
        }
}]);