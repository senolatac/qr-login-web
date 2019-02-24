package sha.com.qrlogin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sha.com.qrlogin.utils.PageUtil;

 
public class CORSFilter implements Filter {
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type, Origin");
        final HttpServletRequest request = (HttpServletRequest) req;
        String reqPath = request.getServletPath();
        if (!PageUtil.isPublicResource(reqPath)) { // Skip JSF resources (CSS/JS/Images/etc)
    		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, post-check=0, pre-check=0"); // HTTP 1.1.
            response.addHeader("Cache-Control", "no-cache");
    		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    		response.setDateHeader("Expires", 0); // Proxies.
        }
        chain.doFilter(req, res);
    }
 
    public void init(FilterConfig filterConfig) {}
 
    public void destroy() {}
 
}
