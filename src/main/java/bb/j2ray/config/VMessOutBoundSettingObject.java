package bb.j2ray.config;

import java.util.ArrayList;
import java.util.List;

public class VMessOutBoundSettingObject extends OutBoundSettingObject{
    public List<VMessServerObject> vnext;

    public VMessOutBoundSettingObject() {
        auth=null;
    }

    public void addServerObject(VMessServerObject serverObject){
        if(vnext==null){
            vnext=new ArrayList<>();
        }
        vnext.add(serverObject);

    }
}
