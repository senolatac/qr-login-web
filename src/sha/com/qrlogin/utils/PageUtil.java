package sha.com.qrlogin.utils;

public class PageUtil {
	
	private static final String CSS_RESOURCE_PATH = "/resources/css";
	private static final String IMAGES_RESOURCE_PATH = "/resources/images";
	private static final String FAVICON_ICO = "/favicon.ico";
	private static final String IMAGE = "/image";
	private static final String REST = "/rest";
	public static final String LOGOUT = "/logout";
	public static final String PAGE_NOT_FOUND = "/404";
	public static final String ACCESS_DENIED = "/403";

	public static final String BLANK_PAGE = "/";
	public static final String INDEX_PAGE = "/index";
	public static final String LOGIN_PAGE = "/login";
	
	//user pages
	public static final String HOME_PAGE="/home";
	
	//admin pages
	public static final String ADMIN_PATH ="/admin/**,/views/admin/**";
	
	public static boolean isPublicResource(String reqPage){
		if(reqPage.startsWith(IMAGES_RESOURCE_PATH)
				|| reqPage.startsWith(IMAGE)
				|| reqPage.startsWith(LOGOUT)
				|| reqPage.startsWith(CSS_RESOURCE_PATH)
				|| reqPage.startsWith(FAVICON_ICO)
				|| reqPage.startsWith(PAGE_NOT_FOUND)
				|| reqPage.startsWith(REST)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isUserPage(String reqPage){
		if(reqPage.equals(HOME_PAGE)){
			return true;
		}else{
			return false;
		}	
	}

}
