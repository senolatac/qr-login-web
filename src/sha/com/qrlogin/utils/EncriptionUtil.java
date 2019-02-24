package sha.com.qrlogin.utils;

import java.util.Random;
import java.util.UUID;

public final class EncriptionUtil {

    private EncriptionUtil() {
    }
    
    public static String createPassword(int uzunluk){
    	return UUID.randomUUID().toString().substring(0,uzunluk);
    }
    
    public static String createNumberPassword(int uzunluk){
    	Random rand = new Random();
    	int upper = (int) Math.pow(10, uzunluk);
    	int lower = (int) Math.pow(10, uzunluk-1);
    	int  n = rand.nextInt(upper) + lower;
    	return ""+n;
    }
    
    public static String createToken(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

