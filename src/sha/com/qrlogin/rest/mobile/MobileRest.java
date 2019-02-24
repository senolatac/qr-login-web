package sha.com.qrlogin.rest.mobile;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sha.com.qrlogin.model.User;
import sha.com.qrlogin.service.IUserService;
import sha.com.qrlogin.utils.EnumUtil.UserProfileType;

@RestController
@RequestMapping("rest/mobile")
public class MobileRest {
	
	@Autowired
	IUserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	try{
        	User kayitli = null;
        	if(user.getEmail()!=null && !user.getEmail().trim().equals("") && user.getSsoId()!=null && !user.getSsoId().trim().equals("")){
        		kayitli = this.userService.findIfExist(user);
        	}
        	if(kayitli!=null && kayitli.getEmail().equals(user.getEmail())){
        		return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
        	}else if(kayitli!=null && kayitli.getSsoId().equals(user.getSsoId())){
        		return new ResponseEntity<User>(HttpStatus.CONFLICT);
        	}else if(kayitli!=null && kayitli.getSsoId().equals(user.getSsoId())){
        		user.setCreateDate(new Date());
        		user.setUniqueId(UUID.randomUUID().toString());
        		user.setPassword(passwordEncoder.encode(user.getPassword()));
        		user = this.userService.update(user);
        		return new ResponseEntity<User>(user, HttpStatus.OK);
        	}
        	if(user.getSsoId()==null){
        		user.setSsoId(UUID.randomUUID().toString());
        	}
        	
        	user.setActive(true);
        	user.setCreateDate(new Date());
        	user.setUniqueId(UUID.randomUUID().toString());
        	user.setEnumProfileType(UserProfileType.USER);
            user = this.userService.save(user);
     
            return new ResponseEntity<User>(user, HttpStatus.OK);    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/user/update/{token}", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("token") String token) {
    	try{
    		if(user.getId()!=null){
            	User kayitli = null;
            	if(user.getEmail()!=null || user.getSsoId()!=null){
            		kayitli = this.userService.findIfExist(user);
            	}
    	    	if(kayitli!=null && kayitli.getEmail().equals(user.getEmail())){
    	    		return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
    	    	}else if(kayitli!=null && kayitli.getSsoId().equals(user.getSsoId())){
    	    		return new ResponseEntity<User>(HttpStatus.CONFLICT);
    	    	}
    			user=this.userService.update(user);
    			return new ResponseEntity<User>(user, HttpStatus.CREATED);
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.OPTIONS})
    public ResponseEntity<User> authenticate(@RequestBody User user){
    	try{
        	user.setEmail(user.getSsoId());
        	User daoUser = this.userService.findByUser(user);
            if (daoUser == null) {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }else if(passwordEncoder.matches(user.getPassword(), daoUser.getPassword())){
            	return new ResponseEntity<User>(daoUser, HttpStatus.OK);
            }else{
            	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "/user/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("token") String token) {
    	User u = this.userService.findByUniqueId(token);
        if (u == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }
    
    
 
}
