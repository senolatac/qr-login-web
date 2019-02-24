(function () {
    var as = angular.module('myApp.controllers', []);

    as.controller('MainController', function ($q, $scope, $rootScope, $http, $location) {
        var load = function () {
        };

        load();

        $scope.activeWhen = function (value) {
            return value ? 'active' : '';
        };

        $scope.path = function () {
            return $location.url();
        };

        $scope.logout = function () {
            $rootScope.user = null;
            $scope.username = $scope.password = null;
            $scope.$emit('event:logoutRequest');
            $location.url('/login');
        };

    });   

}());