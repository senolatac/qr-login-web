package sha.com.qrlogin.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Scope(value="application", proxyMode =ScopedProxyMode.TARGET_CLASS)
@Service
public class ApplicationController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150948086096843325L;
	
	private Map<String, String> settingsMap;
	
    @PostConstruct
    public void init() {
    }

	public Map<String, String> getSettingsMap() {
		return settingsMap;
	}

	public void setSettingsMap(Map<String, String> settingsMap) {
		this.settingsMap = settingsMap;
	}
    
    

    

}
