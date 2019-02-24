package sha.com.qrlogin.rest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sha.com.qrlogin.controller.SessionController;
import sha.com.qrlogin.model.User;
import sha.com.qrlogin.service.IUserService;
import sha.com.qrlogin.utils.EnumUtil.UserProfileType;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("rest/user")
public class UserRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRest.class);
	
	@Autowired
	IUserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @RequestMapping(value = "/list/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = this.userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id, HttpServletRequest request) {
        User user = this.userService.find(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<User> authenticate(@RequestBody User user, HttpServletRequest request){
    	User daoUser = this.userService.findByUser(user);
        if (daoUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }else if(daoUser.getUniqueId().equals(user.getUniqueId())){
        	return new ResponseEntity<User>(daoUser, HttpStatus.OK);
        }else if(passwordEncoder.matches(user.getPassword(), daoUser.getPassword())){
        	return new ResponseEntity<User>(daoUser, HttpStatus.OK);
        }else{
        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    
    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	User kayitli = this.userService.findIfExist(user);
    	if(kayitli!=null && kayitli.getEmail().equals(user.getEmail())){
    		return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
    	}else if(kayitli!=null && kayitli.getSsoId().equals(user.getSsoId())){
    		return new ResponseEntity<User>(HttpStatus.CONFLICT);
    	}
    	user.setActive(true);
    	user.setCreateDate(new Date());
    	user.setUniqueId(UUID.randomUUID().toString());
    	user.setEnumProfileType(UserProfileType.USER);
        user = this.userService.save(user);
 
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody User user, HttpServletRequest request) {
    	try{
    		if(user.getId()!=null){
    			SessionController s = (SessionController) request.getSession().getAttribute("visitor");
    	    	User kayitli = this.userService.findIfExist(user);
    	    	if(kayitli!=null && kayitli.getEmail().equals(user.getEmail())){
    	    		return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
    	    	}else if(kayitli!=null && kayitli.getSsoId().equals(user.getSsoId())){
    	    		return new ResponseEntity<User>(HttpStatus.CONFLICT);
    	    	}
    			user=this.userService.update(user);
    			s.setSessionUser(user);
    			return new ResponseEntity<User>(user, HttpStatus.CREATED);
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User user = userService.find(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUser(user);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
