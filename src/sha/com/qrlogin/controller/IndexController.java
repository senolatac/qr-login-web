package sha.com.qrlogin.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sha.com.qrlogin.service.IUserService;
import sha.com.qrlogin.utils.EnumUtil.UserProfileType;

@Controller
@SessionAttributes("visitor")
@RequestMapping
public class IndexController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	SessionController sessionController;
	

  	@RequestMapping(value="/", method = RequestMethod.GET)
    public String getIndexPage() {
    	if(isAuthenticated()){
    		return "redirect:/home";
    	}
        return "index";
    }
  	
  	@RequestMapping(value="/home", method = RequestMethod.GET)
    public String getHomePage(HttpServletRequest request) {
  		SessionController s = (SessionController) request.getSession().getAttribute("visitor");
  		if(s!=null &&s.getSessionUser()==null){
  			s.setSessionUser(this.userService.findBySso(this.getPrincipal()));
  		}
  		return determineTargetUrl(SecurityContextHolder.getContext().getAuthentication(),"console");
    }
  	
  	@RequestMapping(value="/404", method = RequestMethod.GET)
    public String get404Page() {
        return "error/404";
    }
  	
  	@RequestMapping(value="/403", method = RequestMethod.GET)
    public String get403Page() {
        return "error/403";
    }
  	
  	@RequestMapping(value="/500", method = RequestMethod.GET)
    public String get500Page() {
        return "error/500";
    }
  	
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout,HttpServletRequest request) {
    	if(isAuthenticated()){
    		return "redirect:/home";
    	}
        return "index";
    }
    
    @ModelAttribute("visitor")
    public SessionController getVisitor (HttpServletRequest request) {
        return new SessionController(request.getRemoteAddr());
    }
    
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    
    private boolean isAuthenticated() {
        Authentication authentication =
                        SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    
	protected String determineTargetUrl(Authentication authentication,String postPath) {
		if(authentication==null || authentication instanceof AnonymousAuthenticationToken){
			return "/";
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals(UserProfileType.USER.getRole())) {
				return "user/"+postPath;
			} else if (grantedAuthority.getAuthority().equals(UserProfileType.ADMIN.getRole())) {
				return "admin/"+postPath;
			}
		}
		return "403";
	}

}
