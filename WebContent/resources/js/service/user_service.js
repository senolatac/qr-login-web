'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q','$location', function($http, $q, $location){

    var REST_SERVICE_URI = 'rest/user/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser,
        authorizeUser: loginUser
    };

    return factory;

    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"list/")
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
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
            	if(response.status==201){
            		authorizeUser(response.data,user.rememberMe);
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
    
    function loginUser(user){
    	var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"login/", user)
        .then(
        function (response) {
        	if(response.status==200){
        		authorizeUser(response.data,user.rememberMe);
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
    	if(!user.ssoId && user.uniqueId){
    		user.ssoId = user.uniqueId;
    	}
        var postData = "ssoId="+user.ssoId+"&password="+user.password+"&rememberMe="+remember;

        $http({
            method: 'POST',
            url: REST_SERVICE_URI+"authenticate/",
            data: postData,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "X-Login-Ajax-call": 'true'
            }
        })
        .then(function(response) {
        	if(response.status == 200){
        		window.location.replace('/home');
        	}
        });
    }


    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+"update/"+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+"delete/"+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
