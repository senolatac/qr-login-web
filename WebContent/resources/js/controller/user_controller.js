'use strict';

angular.module('myApp').controller('UserController', ['$scope', '$window', 'UserService', '$translate','$timeout','$location', function($scope, $window, UserService,$translate, $timeout, $location) {
    var self = this;
    self.user={id:null,name:'',password:'',email:'',ssoId:'',rememberMe:false};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.register = submit;
    self.login = authorize;
    
    self.successMessage = '';
    self.errorMessage = '';
    self.submitted = false;


    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(user){
  		var d = new Date();
  		var n = d.getTimezoneOffset();
  		var hrs = -(new Date().getTimezoneOffset() / 60);
  		var t = Intl.DateTimeFormat().resolvedOptions().timeZone;
  		user.timeZone = t;
  		user.timeOffset = n;
        UserService.createUser(user)
            .then(
            function(d) {
            	if(d.status==406){
            		self.errorMessage=$translate.instant('emailExist');
            		self.successMessage='';
            	}else if(d.status==412){
            		self.errorMessage=$translate.instant('captchaNotValid');
            		self.successMessage='';
            	}      	
            	else if(d.status==409){
            		self.errorMessage=$translate.instant('usernameExist');
            		self.successMessage='';
            	}else{
                	self.successMessage = $translate.instant('userCreated');
                    self.errorMessage='';  
                    reset();
            	}


                $timeout(function(){
                	self.submitted=false;
                },1000);
            	
            },
            function(errResponse){
                console.error(errResponse.data.errorMessage);
        		self.errorMessage=$translate.instant('unexpectedError');
        		self.successMessage='';
        		self.submitted=false;
            }
        );
    }
    
    
    function authorizeUser(user){
    	user.ssoId=user.email;
        UserService.authorizeUser(user)
        .then(
        function(d) {
        	if(d.status==404){
        		sessionStorage.setItem("loginErrorCount", self.loginErrorCount+1);
        		self.loginErrorCount=self.loginErrorCount+1;
        		self.errorMessage=$translate.instant('loginError');
        		self.successMessage='';
        	    if(self.loginErrorCount>1){
        	    	self.user.captcha='';
        	    }
        	}else{
//            	self.successMessage =$translate.instant('loginSucced');
                self.errorMessage=''; 
                sessionStorage.setItem("loginErrorCount", 0);
                sessionStorage.setItem("loginUserId", d.data.id);
        	}
            $timeout(function(){
            	self.submitted=false;
            },1000);     	
        },
        function(errResponse){
            console.error(errResponse.data.errorMessage);
    		self.errorMessage=$translate.instant('unexpectedError');
    		self.successMessage='';
    		self.submitted=false;
        }
    );    	
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
    	self.submitted=true;
        if(self.user.id===null){
            //console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
    }
    
    function authorize(){
    	self.submitted=true;
    	authorizeUser(self.user);
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
    
    function remove(id){
        //console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,name:'',password:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    

}]);
