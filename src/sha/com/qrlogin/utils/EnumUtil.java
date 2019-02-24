package sha.com.qrlogin.utils;

public class EnumUtil {
	
	public enum UserProfileType{
	    USER("USER","ROLE_USER"),
	    ADMIN("ADMIN","ROLE_ADMIN");
	     
	    String userProfileType;
	    String role;
	     
	    private UserProfileType(String userProfileType,String role){
	        this.userProfileType = userProfileType;
	        this.role = role;
	    }
	     
	    public String getUserProfileType(){
	        return userProfileType;
	    }
	    
	    public String getRole(){
	    	return role;
	    }
	     
	}

}
