'use strict';
var langEnable=false;
var favoriteLang="tr_TR";
var App = angular.module('myApp', ["ngRoute","ngCookies",'myApp.controllers', 'services', 'pascalprecht.translate', 'spring-security-csrf-token-interceptor','ngTagsInput', 'ui.bootstrap','bootstrap3-typeahead']);

App.config(function($routeProvider) {
    $routeProvider
    .when("/",  {templateUrl: 'views/public/login.jsp',
        publicAccess: true})
    .when("/login", {
        templateUrl : "views/public/login.jsp",
        publicAccess: true
    })        
    .when("/register", {
        templateUrl : "views/public/register.jsp",
        publicAccess: true
    });
});


App.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['Accept'] = 'application/json, text/javascript';
    $httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
    $httpProvider.defaults.headers.common['X-CSRF-TOKEN'] = $('meta[name="_csrf"]').attr('content');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.defaults.xsrfCookieName = '_csrf';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-Token';
}]);
App.run(function run( $http, $cookies ){
});

App.config(['$translateProvider', '$translatePartialLoaderProvider', function ($translateProvider, $translatePartialLoaderProvider) {
	  var $cookies;
	  angular.injector(['ngCookies']).invoke(['$cookies', function(_$cookies_) {
	    $cookies = _$cookies_;
	  }]);
}]);


App.directive('fileModel', ['$parse', function ($parse) {
 return {
 restrict: 'A',
 link: function(scope, element, attrs) {
 var model = $parse(attrs.fileModel);
 var modelSetter = model.assign;
element.bind('change', function(){
 scope.$apply(function(){
 modelSetter(scope, element[0].files[0]);
 });
 });
 }
 };
 }]);

var compareTo = function() {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
};
 
App.directive("compareTo", compareTo);

App.directive('input', inputTypeFileDirective);

	function inputTypeFileDirective () {
		return {
			restrict: 'E',
			require: '?ngModel',
			link: function (scope, element, attrs, ngModel) {
				if (attrs.type !== 'file' || !angular.isDefined(ngModel)) {
					return;
				}

				element.on('change', updateModelWithFile);
				scope.$on('$destroy', function () {
					element.off('change', updateModelWithFile);
				});

				if (attrs.maxsize) {
					var maxsize = parseInt(attrs.maxsize);
					ngModel.$validators.maxsize = function(modelValue, viewValue) {
						var value = modelValue || viewValue;
						if (!angular.isArray(value)) {
							value = [value];
						}
						for (var i = value.length - 1; i >= 0; i--) {
							if (value[i] && value[i].size && value[i].size > maxsize) {
								console.log(value[i].size);
								return false;
							}
						}
						return true;
					};
				}

				if (attrs.accept) {
					var accept = attrs.accept.split(',');
					ngModel.$validators.accept = function(modelValue, viewValue) {
						var value = modelValue || viewValue;
						if (!angular.isArray(value)) {
							value = [value];
						}
						for (var i = value.length - 1; i >= 0; i--) {
							if (value[i] && accept.indexOf(value[i].type) === -1) {
								return false;
							}
						}
						return true;
					};
				}

				function updateModelWithFile (event) {
					var files = event.target.files;
					if (!angular.isDefined(attrs.multiple)) {
						files = files[0];
					} else {
						files = Array.prototype.slice.apply(files);
					}
					ngModel.$setViewValue(files, event);
				}
			}
		};
	}
