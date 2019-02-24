'use strict';

angular.module('myApp').controller('UserMController', ['$scope', 'UserMService', '$translate','$timeout','$location','$uibModal','$window', function($scope, UserMService,$translate, $timeout, $location,$uibModal,$window) {
    var self = this;
    self.user={id:null,name:'',email:'',ssoId:'',uniqueId:'',active:true,confirm:false,createDate:null,enumProfileType:null};
    self.filterUser={row:'10',text:null,startDate:null,endDate:null};
    self.users=[];
    
    self.successMessage = '';
    self.errorMessage = '';
    self.submitted = false;
    self.editIndex=-1;
    self.orderByField = 'name';
    self.reverseSort = false;
    
    fetchAllUsers();
    init();
    
    function init(){
    	var lang = $window.navigator.language || $window.navigator.userLanguage;
    	self.browserCode = lang.toUpperCase();
    }
    
    function fetchAllUsers(){
    	UserMService.fetchAllUsers(self.filterUser)
            .then(
            function(d) { 
            	console.log(d.data);
            	self.users = d.data;
            },
            function(errResponse){
        		self.errorMessage=$translate.instant('unexpectedError');
        		self.successMessage='';
        		self.submitted=false;
            }
        );
    }
    
}]);   