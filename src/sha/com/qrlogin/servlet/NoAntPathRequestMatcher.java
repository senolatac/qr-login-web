package sha.com.qrlogin.servlet;

import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class NoAntPathRequestMatcher implements RequestMatcher {
  private final AndRequestMatcher andRequestMatcher;

  public NoAntPathRequestMatcher(String[] patterns) {
	  List<RequestMatcher> requestMatchers = new ArrayList<RequestMatcher>();
	  for(String s:patterns){
		  requestMatchers.add(new NegatedRequestMatcher(new AntPathRequestMatcher(s)));
	  }
     andRequestMatcher = new AndRequestMatcher(requestMatchers);
  }

  @Override
  public boolean matches(HttpServletRequest request) {
    return andRequestMatcher.matches(request);
  }
}
