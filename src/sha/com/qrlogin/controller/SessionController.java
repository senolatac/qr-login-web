package sha.com.qrlogin.controller;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import sha.com.qrlogin.model.User;

@Scope(value="session", proxyMode =ScopedProxyMode.TARGET_CLASS)
@Service("sessionController")
public class SessionController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3207651922738783593L;
	
	private User sessionUser;
	private String ip;
	private String currentPage;
	
	public SessionController(){
	}
	
	public SessionController(String ip){
		this.ip=ip;
	}
	
	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(User user) {
		this.sessionUser = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
}
