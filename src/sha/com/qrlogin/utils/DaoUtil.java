package sha.com.qrlogin.utils;

import java.util.List;

public class DaoUtil {
	
    @SuppressWarnings("rawtypes")
    public static Object firstOrNull(final List sonucListesi) {
        if((sonucListesi != null) && (!sonucListesi.isEmpty())) {
            return sonucListesi.iterator().next();
        }
        return null;
    }


}
