package j2ray.command.support;

import java.util.HashMap;
import java.util.Map;

public class I18nText {
    private static final Map<String,String> map=new HashMap<>();
    public static String getText(String key){
        String s=map.get(key);
        if(s==null){
            return key;
        }
        return s;
    }
}
