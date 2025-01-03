package j2ray.support.config;

import java.util.ArrayList;
import java.util.List;

public class SniffingObject {
    public Boolean enabled;
    public Boolean metadataOnly;
    public List<String> destOverride;

    public void addDestOverride(String... names){

        if(destOverride==null){
            destOverride=new ArrayList<>();
        }
        for(String n:names){
            if(destOverride.contains(n)){
                return;
            }
            destOverride.add(n);
        }
    }
}
