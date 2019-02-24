package sha.com.qrlogin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import sha.com.qrlogin.utils.PageUtil;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//@WebFilter("/SystemRequestFilter")
public class SystemRequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String reqPath = request.getServletPath();
    	response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-requested-with, origin, Content-Type");
        
        if (!PageUtil.isPublicResource(reqPath)) { // Skip JSF resources (CSS/JS/Images/etc)
    		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, post-check=0, pre-check=0"); // HTTP 1.1.
            response.addHeader("Cache-Control", "no-cache");
    		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    		response.setDateHeader("Expires", 0); // Proxies.
        }
        
        if(PageUtil.isUserPage(reqPath) && !isAuthenticated()){
        	response.sendRedirect(request.getContextPath()+PageUtil.BLANK_PAGE);
        	return;
        }
        
        chain.doFilter(request, response);
		
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

}
